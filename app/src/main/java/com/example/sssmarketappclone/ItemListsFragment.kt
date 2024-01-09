package com.example.sssmarketappclone

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sssmarketappclone.ItemDatas.itemDataLists
import com.example.sssmarketappclone.databinding.FragmentItemListsBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ItemListsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var listener: Like? = null

    private lateinit var mainActivity: MainActivity

    private var _binding:FragmentItemListsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = context as MainActivity

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Like)
            listener = context

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            adapter = ItemListAdapter(mainActivity.itemDataLists())
            layoutManager = LinearLayoutManager(mainActivity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(mainActivity, LinearLayout.VERTICAL))
        }

        spinner()
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemListsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun spinner() {
        val spinner = binding.spinner
        val spinnerDatas = resources.getStringArray(R.array.myLocation)
        val spinnerAdapter = ArrayAdapter(mainActivity,android.R.layout.simple_spinner_item, spinnerDatas)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        listener = null
    }
}