package com.complete.grocerylistapplicationmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.complete.grocerylistapplicationmvvm.databinding.ActivityMainBinding
import com.complete.grocerylistapplicationmvvm.databinding.RecyclerItemBinding

class GroceryRVA(var list : List<GroceryItems>, val groceryItemClickInterface: GroceryItemClickInterface) :
    RecyclerView.Adapter<GroceryRVA.GroceryViewHolder>() {
    interface GroceryItemClickInterface {
        fun onItemClick(groceryItems: GroceryItems)

    }

    inner class GroceryViewHolder(itemView : RecyclerItemBinding ) : RecyclerView.ViewHolder(itemView.root) {
        val binding: RecyclerItemBinding = itemView

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GroceryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.binding.itemName.text = list.get(position).itemName
        holder.binding.quantity.text = list.get(position).itemQuantity.toString()
        holder.binding.rate.text = "Rs."+list.get(position).itemPrice.toString()+" each"
        val total :Int = (list.get(position).itemPrice).toInt() * (list.get(position).itemQuantity).toInt()
        holder.binding.amt.text = "Rs."+total
        holder.binding.delete.setOnClickListener{
            groceryItemClickInterface.onItemClick(list.get(position))
        }

    }

    override fun getItemCount(): Int {
        return list.size;
    }
}
