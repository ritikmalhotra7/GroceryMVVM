package com.complete.grocerylistapplicationmvvm

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface GroceryDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(item : GroceryItems)
    @Delete
    suspend fun remove(item: GroceryItems)
    @Query("select * from grocery_items")
    fun getAllItems() : LiveData<List<GroceryItems>>
}