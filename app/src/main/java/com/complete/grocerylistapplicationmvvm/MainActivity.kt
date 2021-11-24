package com.complete.grocerylistapplicationmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.complete.grocerylistapplicationmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),GroceryRVA.GroceryItemClickInterface {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var list : List<GroceryItems>
    private lateinit var adapter : GroceryRVA
    private lateinit var viewmodel :GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList<GroceryItems>()
        adapter = GroceryRVA(list,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        val groceryRepo = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepo)
        viewmodel = ViewModelProvider(this,factory).get(GroceryViewModel::class.java)
        viewmodel.getAll().observe(this,{
            adapter.list =
        })
    }

    override fun onItemClick(groceryItems: GroceryItems) {

    }
}