package com.complete.grocerylistapplicationmvvm

class GroceryRepository(private val db :GroceryDatabase) {

    suspend fun insert(items : GroceryItems) = db.getGroceryDao().insert(items)
    suspend fun remove(items : GroceryItems ) = db.getGroceryDao().remove(items)

    fun getAll() = db.getGroceryDao().getAllItems()

}