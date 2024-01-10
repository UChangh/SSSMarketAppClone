package com.example.sssmarketappclone

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemBox(
    var image:Int,
    var title:String,
    var address:String,
    var price:Int,
    var like:Int,
    var seller:String,
    var details:String
) : Parcelable