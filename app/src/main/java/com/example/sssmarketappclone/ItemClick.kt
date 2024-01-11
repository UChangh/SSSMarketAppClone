package com.example.sssmarketappclone

import android.view.View

interface ItemClick {
    fun onClick(v: View, n: Int)
    fun onLongClick(v: View, n: Int)
}