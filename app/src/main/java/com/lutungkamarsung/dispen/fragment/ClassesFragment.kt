package com.lutungkamarsung.dispen.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.adapter.AdapterRVClasses
import com.lutungkamarsung.dispen.adapter.AdapterRVHistory
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.model.Classes
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.fragment_classes.view.*
import kotlinx.coroutines.*

class ClassesFragment : Fragment() {
    var data:ArrayList<Classes> = ArrayList()
    var dataJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_classes, container, false)

        prepareRV(v)
        prepareData()

        return v
    }

    private fun prepareData() {
        dataJob = CoroutineScope(Dispatchers.IO).launch {
            val request = Request.getClasses(context!!)

            withContext(Dispatchers.Main){
                if(request.size > 0){
                    data = request
                    prepareRV(view!!)
                }
            }
        }
    }

    private fun prepareRV(view: View) {
        view.rv_classes.adapter = AdapterRVClasses(data, context!!)
        view.rv_classes.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }


}
