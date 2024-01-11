package com.example.sssmarketappclone

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.sssmarketappclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = ItemListsFragment.newInstance("")
        setFragment(fragment)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun setFragment(f: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frameItemLists, f)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val dialog = AlertDialog.Builder(this@MainActivity)
                .setTitle("종료")
                .setMessage("정말 종료하시겠습니까?")
                .setIcon(R.drawable.bell_resize_01)

            val listener = DialogInterface.OnClickListener { _, which ->
                if(which == DialogInterface.BUTTON_POSITIVE) finish()
            }

            dialog.setPositiveButton("확인",listener)
            dialog.setNegativeButton("취소",listener)
            dialog.show()
        }
    }
}