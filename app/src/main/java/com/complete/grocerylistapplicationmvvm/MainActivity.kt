package com.complete.grocerylistapplicationmvvm

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.complete.grocerylistapplicationmvvm.databinding.ActivityMainBinding
import com.complete.grocerylistapplicationmvvm.databinding.GroceryDialogBinding

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
            adapter.list = it
            adapter.notifyDataSetChanged()
        })
        binding.fab.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        val dialog = Dialog(this)
        val b = GroceryDialogBinding.inflate(layoutInflater)
        dialog.setContentView(b.root)
        b.cancelbutton.setOnClickListener {
            dialog.dismiss()
        }
        b.addbutton.setOnClickListener {
            val name = b.nameinput.text.toString().trim()
            val quantity = b.quantityinput.text.toString().trim()
            val rate = b.priceinput.text.toString().trim()

            val qty = quantity.toInt()
            val price = rate.toInt()

            if(name.isNotEmpty() && quantity.isNotEmpty() && rate.isNotEmpty()){
                val items = GroceryItems(name,qty,price)
                viewmodel.insert(items)
                Toast.makeText(this, "Item Inserted",Toast.LENGTH_SHORT).show()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(this,"Enter Properly",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    override fun onItemClick(groceryItems: GroceryItems) {
        viewmodel.delete(groceryItems)
        adapter.notifyDataSetChanged()
        Toast.makeText(this,"Deleted!",Toast.LENGTH_SHORT).show()
    }
}