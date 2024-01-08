package com.example.sssmarketappclone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sssmarketappclone.databinding.ItemListLayoutBinding

class ItemListAdapter(private val itemlist:ArrayList<Items>):RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {
    inner class ViewHolder(binding:ItemListLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val img = binding.itemImage
        val title = binding.tvTitle
        val addr = binding.tvAddress
        val price = binding.tvPrice
        val chat = binding.tvChatCount
        val like = binding.tvLikedCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListAdapter.ViewHolder {
        val binding = ItemListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListAdapter.ViewHolder, position: Int) {
        with(holder) {
            img.setImageResource(itemlist[position].image)
            title.text = itemlist[position].title
            addr.text = itemlist[position].address
            price.text = itemlist[position].price.toString()
            chat.text = itemlist[position].chat.toString()
            like.text = itemlist[position].like.toString()
        }
//        seller.text = itemlist[position].seller
    }

    override fun getItemCount(): Int = itemlist.size
}