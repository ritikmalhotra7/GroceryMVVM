package com.complete.grocerylistapplicationmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroceryViewModelFactory (private val repositery:GroceryRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GroceryViewModel(repositery) as T
    }

}