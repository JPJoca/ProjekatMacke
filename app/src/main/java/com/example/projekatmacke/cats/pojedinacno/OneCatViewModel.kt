package com.example.projekatmacke.cats.pojedinacno

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekatmacke.cats.CatsRepo
import com.example.projekatmacke.cats.api.model.CatsApiModel
import com.example.projekatmacke.cats.lista.CatListContract
import com.example.projekatmacke.cats.pojedinacno.model.ImageUiModel
import com.example.projekatmacke.cats.pojedinacno.model.OneCatUiModel
import com.example.projekatmacke.slike.ImageRepo
import com.example.projekatmacke.slike.api.Model.ImageApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OneCatViewModel (
    private val repository: CatsRepo = CatsRepo,
    private val repo: ImageRepo = ImageRepo,
    private val catId: String
) : ViewModel(){
    private val _state = MutableStateFlow(OneCatContract.OneCatState())
    val state = _state.asStateFlow()
    private fun setState(reducer: OneCatContract.OneCatState.() -> OneCatContract.OneCatState) = _state.update(reducer)

    private val events = MutableSharedFlow<CatListContract.CatListUiEvent>()
    fun setEvent(event: CatListContract.CatListUiEvent) = viewModelScope.launch { events.emit(event) }

    init {
        fetchCat()

    }

    private fun fetchCat() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val cat = withContext(Dispatchers.IO) {
                    repository.fetchOneCat(catId).asOneCatUiModel()
                }

                println(cat)
                setState { copy(cat = cat) }
                fetchImage()
            } catch (error: Exception) {
                // TODO Handle error
                println("Eror sa jednom mackom")
            } finally {
                setState { copy(loading = false) }
            }
        }
    }
    private fun fetchImage() {

        viewModelScope.launch {
            setState { copy(loading = true) }
            println(state.value.cat)
            try{
                val image = withContext(Dispatchers.IO) {
                    //    repo.fetchImage(cat.reference_image_id).asImageUiModel()
                    repo.fetchImage(imageId  = state.value.cat?.reference_image_id)
                }
                print(image)
                setState { copy(imageUrl =  image.url) }
            } catch (error: Exception) {
            // TODO Handle error
            println("Eror sa slikom")
        } finally {
            setState { copy(loading = false) }
        }
        }
    }





    private fun CatsApiModel.asOneCatUiModel() = OneCatUiModel(
        id = this.id,
        name = this.name,
        alt_names = this.alt_names,
        description = this.description,
        temperament = this.temperament,
        origin = this.origin,
        life_span = this.life_span,
        wikipedia_url = this.wikipedia_url,
        weight = this.weight,
        adaptability = this.adaptability,
        energy_level = this.energy_level,
        intelligence =  this.intelligence,
        stranger_friendly =  this.stranger_friendly,
        dog_friendly =  this.dog_friendly,
        rare = this.rare,
        reference_image_id = this.reference_image_id
    )
    private fun ImageApiModel.asImageUiModel() = ImageUiModel(
        url = this.url

    )
}

