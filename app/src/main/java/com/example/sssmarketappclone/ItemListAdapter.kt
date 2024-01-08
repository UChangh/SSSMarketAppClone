package com.example.sssmarketappclone

import android.icu.text.DecimalFormat
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
        var seller = ""
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
            price.text = DecimalFormat("#,###").format(itemlist[position].price)
            chat.text = itemlist[position].chat.toString()
            like.text = itemlist[position].like.toString()
        }
        holder.seller = itemlist[position].seller   // 아무튼 seller 가 존재함
    }

    override fun getItemCount(): Int = itemlist.size
}