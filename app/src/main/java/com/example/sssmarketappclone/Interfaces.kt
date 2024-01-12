package com.example.sssmarketappclone

import android.view.View

interface ItemClick {
    fun onClick(v: View, n: Int)
}

interface ItemLongClick {
    fun onLongClick(v: View, n: Int)
}

interface Like {
    fun liked(n:Int):Int
}