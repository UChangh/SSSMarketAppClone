package com.example.sssmarketappclone

import android.content.Context
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sssmarketappclone.databinding.FragmentItemDetailBinding


private const val PARAM_ITEM = "itemData"


class ItemDetailFragment : Fragment() {
    private var param1: Items? = null

    private var listener: Like? = null

    private var _binding:FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = context as MainActivity

        arguments?.let {
            param1 = it.getParcelable(PARAM_ITEM)   // ????
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Like) listener = context
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
        fun newInstance(param1: Items) =
            ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAM_ITEM,param1)
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
        listener = null
    }

    fun toast(s:String) {
        Toast.makeText(mainActivity,s,Toast.LENGTH_SHORT).show()
    }
}