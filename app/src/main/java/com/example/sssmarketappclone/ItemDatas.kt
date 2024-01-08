package com.example.sssmarketappclone

import android.content.Context

object ItemDatas {
    private val dataset = ArrayList<Items>()

    fun Context.itemDataLists():ArrayList<Items> {
        dataset.clear()

        val listImage = images()
        val listTitle = resources.getStringArray(R.array.itemTitle)
        val listAddress = resources.getStringArray(R.array.itemLocation)
        val listPrice = resources.getStringArray(R.array.itemPrice)
        val listLike = resources.getStringArray(R.array.itemLiked)
        val listChat = resources.getStringArray(R.array.itemChat)
        val listSeller = resources.getStringArray(R.array.itemSeller)

        for(i in listTitle.indices) {
            dataset.add(
                Items(
                    listImage[i],
                    listTitle[i],
                    listAddress[i],
                    listPrice[i].toInt(),
                    listLike[i].toInt(),
                    listChat[i].toInt(),
                    listSeller[i]
                )
            )
        }
        return dataset
    }

    private fun images():List<Int> {
        return listOf(
            R.drawable.sample1,
            R.drawable.sample2,
            R.drawable.sample3,
            R.drawable.sample4,
            R.drawable.sample5,
            R.drawable.sample6,
            R.drawable.sample7,
            R.drawable.sample8,
            R.drawable.sample9,
            R.drawable.sample10
        )
    }
}