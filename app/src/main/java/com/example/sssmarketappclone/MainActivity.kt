package com.example.sssmarketappclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.sssmarketappclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Like {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = ItemListsFragment.newInstance("","")
        setFragment(fragment)
    }

    private fun setFragment(f: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frameItemLists, f)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }

    override fun liked() {

    }
}