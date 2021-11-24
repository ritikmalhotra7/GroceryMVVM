package com.complete.grocerylistapplicationmvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {
    
    fun insert(items: GroceryItems) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(item : GroceryItems) = GlobalScope.launch {
        repository.remove(item);
    }
    fun getAll() = repository.getAll()
}