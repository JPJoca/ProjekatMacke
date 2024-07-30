package com.example.projekatmacke.cats.lista

import com.example.projekatmacke.cats.lista.model.CatUiModel

interface CatListContract {

    data class CatListState(
        val loading: Boolean = false,
        val querry: String = "",
        val isSearchMode: Boolean = false,
        val cats: List<CatUiModel> = emptyList(),
        val filtredCats: List<CatUiModel> = emptyList(),

    )
    sealed class CatListUiEvent {
        data class SearchQueryChanged(val query: String) : CatListUiEvent()
    }

}