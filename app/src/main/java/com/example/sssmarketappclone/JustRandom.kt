package com.example.sssmarketappclone

class JustRandom {
    companion object{
        var randomObject:Int = 0
    }
    fun randomfunc(r:Int):Int {
        return when {
            r/10 >= 90 -> R.drawable.face_super_good
            r/10 in 75 until 90 -> R.drawable.face_very_good
            r/10 in 65 until 75 -> R.drawable.face_good
            r/10 in 45 until 65 -> R.drawable.face_normal
            r/10 in 30 until 45 -> R.drawable.face_bad
            r/10 in 15 until 30-> R.drawable.face_very_bad
            else -> R.drawable.face_super_bad
        }
    }
}