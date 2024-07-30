package com.example.projekatmacke.cats.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekatmacke.cats.CatsRepo
import com.example.projekatmacke.cats.api.model.CatsApiModel
import com.example.projekatmacke.cats.lista.model.CatUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class CatListViewModel(
    private val repository: CatsRepo = CatsRepo,

) : ViewModel(){
    private val _state = MutableStateFlow(CatListContract.CatListState())
    val state = _state.asStateFlow()
    private fun setState(reducer: CatListContract.CatListState.() -> CatListContract.CatListState) = _state.update(reducer)

    private val events = MutableSharedFlow<CatListContract.CatListUiEvent>()
    fun setEvent(event: CatListContract.CatListUiEvent) = viewModelScope.launch { events.emit(event) }

    init {
        fetchAllUsers()
        observeSearchQuery()
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            events
                .filterIsInstance<CatListContract.CatListUiEvent.SearchQueryChanged>()
                .debounce(0.5.seconds)
                .collect { event ->
                    if(event.query == ""){
                        setState  {copy(isSearchMode = false)}
                    }
                    else{
                        setState  {copy(isSearchMode = true)}
                        setState { copy(filtredCats = cats.filter { it.name.contains((event.query)) }) }
                    }
                }
        }
    }


    private fun fetchAllUsers() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val cats = withContext(Dispatchers.IO) {
                    repository.fetchAllCats().map { it.asCatsUiModel() }
                }
                setState { copy(cats = cats) }
            } catch (error: Exception) {
                // TODO Handle error
                println("Eror sa vise macaka")
            } finally {
                setState { copy(loading = false) }
            }
        }
    }
    private fun limitdescription(description: String): String {
        return if (description.length <= 250) {
            description
        } else {
            description.substring(0, 250)
        }
    }
    private fun random(tmp:String):List<String>{
        val words = tmp.split(", ")
        if(words.size <= 3)
            return words

        val pickedWords = mutableListOf<String>()
        val randomIndxes = mutableSetOf<Int>()

        while (randomIndxes.size < 3){
            val idx = Random.nextInt(words.size)
            if(!randomIndxes.contains(idx)){
                randomIndxes.add(idx)
                pickedWords.add(words[idx])
            }
        }

        return pickedWords

    }
    private fun CatsApiModel.asCatsUiModel() = CatUiModel(
        id = this.id,
        name = this.name,
        alt_names = this.alt_names,
        description = limitdescription(this.description),
        temperament = random(this.temperament)
    )
}