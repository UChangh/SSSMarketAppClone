package com.example.sssmarketappclone

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sssmarketappclone.ItemDatas.itemDataLists
import com.example.sssmarketappclone.databinding.FragmentItemListsBinding
import kotlin.random.Random


private const val ARG_PARAM1 = "param1"

class ItemListsFragment : Fragment() {
    private var param1: String? = null

    private lateinit var mainActivity: MainActivity

    private var _binding:FragmentItemListsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = context as MainActivity

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
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

        val itemlist = mainActivity.itemDataLists()
        val adapter = ItemListAdapter(itemlist)

        binding.btnGoTop.setOnClickListener{
            binding.recyclerView.scrollToPosition(0)
        }

        binding.recyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(mainActivity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(mainActivity, LinearLayout.VERTICAL))
        }

        adapter.itemClicked = object : ItemClick {
            override fun onClick(v: View, n: Int) {
                val items = Items(
                    itemlist[n].image,
                    itemlist[n].title,
                    itemlist[n].address,
                    itemlist[n].price,
                    itemlist[n].like,
                    itemlist[n].seller,
                    itemlist[n].detail,
                    itemlist[n].chat,
                    false
                )
                JustRandom.randomObject = Random.nextInt(1000)
                val fragment = ItemDetailFragment.newInstance(items, n)
                setFragment(fragment)
            }
        }

        adapter.itemLongClicked = object : ItemLongClick {
            override fun onLongClick(v: View, n: Int) {
                val dialog = AlertDialog.Builder(mainActivity)
                    .setTitle("상품 삭제")
                    .setMessage("상품을 정말로 삭제하시겠습니까?")
                    .setIcon(R.drawable.chat_resize_01)

                dialog.setPositiveButton("확인") { dialog, _ ->
                    itemlist.remove(itemlist[n])
                    adapter.notifyItemRemoved(n)
                }
                dialog.setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
                dialog.show()
            }
        }

        binding.imageButton.setOnClickListener{
            notification()
        }

        spinner()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ItemListsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }

        @JvmStatic
        fun likePressed(p1:Boolean) =
            ItemListsFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_PARAM1, p1)
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

    private fun notification() {
        val manager = mainActivity.getSystemService(Activity.NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelID = "myChannel16"
            val channelName = "My Channel 16"
            val channel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Ch.16"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE) // type notification 대신
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(false)  // 진동 Off(진동싫어)
            }
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(mainActivity, channelID)
        } else {
            builder = NotificationCompat.Builder(mainActivity)
        }

        builder.run {
            setSmallIcon(R.drawable.notify_resize_01)
            setWhen(System.currentTimeMillis())
            setContentTitle(resources.getString(R.string.app_name))
            setContentText("새 알림 도착")
            setStyle(
                NotificationCompat.BigTextStyle().bigText("새로운 알림이 도착했습니다!!!")
            )
        }
        manager.notify(16,builder.build())
    }

    fun setFragment(f:Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frameItemLists,f)
            .addToBackStack("")
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}