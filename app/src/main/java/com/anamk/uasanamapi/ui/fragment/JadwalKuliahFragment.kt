package com.anamk.uasanamapi.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anamk.uasanamapi.R
import com.anamk.uasanamapi.adapter.JadwalPribadiAdapter
import com.anamk.uasanamapi.model.DataItemModel
import com.anamk.uasanamapi.rest.ApiService
import com.anamk.uasanamapi.rest.RetroConfig
import kotlinx.android.synthetic.main.fragment_jadwal_kuliah.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 * Generate by anam
 */
class JadwalKuliahFragment : Fragment() {
    private var items: ArrayList<DataItemModel> = arrayListOf()
    private lateinit var rv: RecyclerView
    private lateinit var mAdapter: JadwalPribadiAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_jadwal_kuliah, container, false)
        rv = view.rv
        val apiService: ApiService = RetroConfig.provideApi()
        apiService.getJadwalKuliah()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                items.clear()
                items = it.data as ArrayList<DataItemModel>
                mAdapter = JadwalPribadiAdapter(items, activity)
                rv.visibility = View.VISIBLE
                rv.layoutManager = LinearLayoutManager(activity)
                rv.adapter = mAdapter

            }, {
                error("Error" + it.message)
            })

        return view
    }

    private fun getDatas() {

    }


}
