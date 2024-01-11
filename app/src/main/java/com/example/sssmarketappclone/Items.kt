package com.example.sssmarketappclone

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
    var image: Int,
    var title: String,
    var address: String,
    var price: Int,
    var like: Int,
    var seller: String,
    var detail: String,
    var chat: Int,
    var isLike: Boolean
) : Parcelable