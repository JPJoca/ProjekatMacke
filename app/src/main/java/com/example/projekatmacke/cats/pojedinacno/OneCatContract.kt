package com.example.projekatmacke.cats.pojedinacno

import com.example.projekatmacke.cats.pojedinacno.model.OneCatUiModel

interface OneCatContract {

    data class OneCatState(
        val loading: Boolean = false,
        val cat: OneCatUiModel? = null,
        val imageUrl: String? = null,
        )
//    sealed class OneCAtUiEvent {
//        data class SearchQueryChanged(val query: String) : CatListUiEvent()
//        data object ClearSearch : CatListUiEvent()
//        data object CloseSearchMode : CatListUiEvent()
//        data class RemoveUser(val userId: Int) : CatListUiEvent()
//        data object Dummy : CatListUiEvent()
//    }


}