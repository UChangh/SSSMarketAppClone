package com.example.sssmarketappclone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sssmarketappclone.databinding.ItemDetailLayoutBinding

class ItemDetailAdapter(private val itemlist:ItemBox):RecyclerView.Adapter<ItemDetailAdapter.Holder>() {
    inner class Holder(binding: ItemDetailLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageView
        val user = binding.tvUserName
        val addr = binding.tvUserAddress
        val name = binding.tvItemName
        val detail = binding.tvDetails

        val funny = binding.ivFunnyIcon
        val percent = binding.tvPercent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDetailAdapter.Holder {
        val binding = ItemDetailLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: ItemDetailAdapter.Holder, position: Int) {
        holder.apply {
            image.setImageResource(itemlist.image)
            user.text = itemlist.seller
            addr.text = itemlist.address
            name.text = itemlist.title
            detail.text = itemlist.details
            percent.text = "${JustRandom.randomObject.toFloat()/10} `C"
            funny.setImageResource(JustRandom().randomfunc(JustRandom.randomObject))
        }
    }

    override fun getItemCount(): Int = 1
}