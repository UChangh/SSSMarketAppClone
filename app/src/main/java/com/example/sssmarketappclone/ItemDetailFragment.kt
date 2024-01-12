package com.example.sssmarketappclone

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sssmarketappclone.ItemDatas.itemDataLists
import com.example.sssmarketappclone.databinding.FragmentItemDetailBinding


private const val PARAM_ITEM = "itemData"
private const val ITEM_POS = "itemPosition"

class ItemDetailFragment : Fragment() {
    private var param1: Items? = null
    private var paramPos:Int = 0

    private var _binding:FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = context as MainActivity

        arguments?.let {
            param1 = it.getParcelable(PARAM_ITEM)   // Bundle로 묶인 Items를 해체하는 곳
            paramPos = it.getInt(ITEM_POS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            ivLike.setOnClickListener {
                when(param1!!.isLike) {
                    false -> {
                        param1!!.isLike = true
                        ivLike.setImageResource(R.drawable.heart_filled_resize_01)
                        param1!!.like += 1
                    }
                    true -> {
                        param1!!.isLike = false
                        ivLike.setImageResource(R.drawable.heart_empty_resize_01)
                        param1!!.like -= 1
                    }
                }
                ItemListAdapter(mainActivity.itemDataLists()).notifyItemChanged(paramPos)
            }

            tvMoney.text = "${DecimalFormat("#,###").format(param1?.price)}원"

            btnChat.setOnClickListener {
                toast("이 기능은 아직 사용하실 수 없습니다 ^^")
            }

            detailRV.apply {
                adapter = ItemDetailAdapter(param1!!)
                layoutManager = LinearLayoutManager(mainActivity)
            }

            imageButton2.apply {
                bringToFront()
                setOnClickListener {
                    val fragment = ItemListsFragment.newInstance("")
                    setFragment(fragment)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Items,position: Int) =
            ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAM_ITEM,param1)
                    putInt(ITEM_POS, position)
                }
            }
    }

    private fun setFragment(f:Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frameItemLists, f)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun toast(s:String) {
        Toast.makeText(mainActivity,s,Toast.LENGTH_SHORT).show()
    }
}