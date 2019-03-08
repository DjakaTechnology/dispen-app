package com.lutungkamarsung.dispen.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.adapter.AdapterRVHistory
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.coroutines.*

class HistoryFragment : Fragment() {
    var data:ArrayList<PermissionModel> = ArrayList()
    var dataJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_history, container, false)

        prepareRV(v)
        prepareData()

        return v
    }

    private fun prepareData() {
        val typeId = SharedKey.getUserModel(context!!)!!.levelId
        dataJob = CoroutineScope(Dispatchers.IO).launch {
            val request = if(typeId == 1)Request.getPermissionMine(context!!) else Request.getPermissionMyChildHistory(context!!)

            withContext(Dispatchers.Main){
                if(request.size > 0){
                    data = request
                    prepareRV(view!!)
                }
            }
        }
    }

    private fun prepareRV(view: View) {
        view.rv_history.adapter = AdapterRVHistory(data, context!!)
        view.rv_history.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }


}
