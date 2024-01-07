package com.example.sssmarketappclone

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sssmarketappclone.databinding.ItemListLayoutBinding

class ItemListAdapter(private val itemlist:ArrayList<Items>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ViewHolder(binding:ItemListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}