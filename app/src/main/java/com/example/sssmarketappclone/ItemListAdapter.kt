package com.example.sssmarketappclone

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sssmarketappclone.databinding.ItemListLayoutBinding

class ItemListAdapter(private val itemlist:ArrayList<Items>):RecyclerView.Adapter<ItemListAdapter.ViewHolder>(){
    inner class ViewHolder(binding:ItemListLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val img = binding.itemImage
        val title = binding.tvTitle
        val addr = binding.tvAddress
        val price = binding.tvPrice
        val like = binding.tvLikedCount
        val chat = binding.tvChatCount
        var seller = ""
        var detail = ""

        val item = binding.itemLayout
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
            like.text = itemlist[position].like.toString()
            item.setOnClickListener {
                itemClicked?.onClick(it, position)
            }
            item.setOnLongClickListener {
                itemLongClicked?.onLongClick(it,position)
                true
            }
            chat.text = itemlist[position].chat.toString()
            seller = itemlist[position].seller
            detail = itemlist[position].detail
        }
    }

    override fun getItemCount(): Int = itemlist.size

    var itemClicked: ItemClick? = null
    var itemLongClicked: ItemClick? = null
}