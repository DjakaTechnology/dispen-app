package com.lutungkamarsung.dispen.fragment


import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.adapter.AdapterRVDispen
import com.lutungkamarsung.dispen.adapter.AdapterRVHistory
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.fragment_dispen.view.*
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class DispenFragment : Fragment() {
    private var dataJob: Job? = null
    private var setterJob:Job? = null
    private var data:ArrayList<PermissionModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.fragment_dispen, container, false)

        prepareRV(v)
        prepareData()

        return v
    }

    private fun prepareData() {
        dataJob = CoroutineScope(Dispatchers.IO).launch {
            val request = Request.getDispenSubClass(context!!)

            withContext(Dispatchers.Main){
                data = request
                prepareRV(view!!)
            }
        }
    }

    private fun prepareRV(view: View) {
        view.rv_dispen.adapter = AdapterRVDispen(data, context!!, this)
        view.rv_dispen.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }


    fun acceptPermission(id:Int){
        val dialog = ProgressDialog.show(context, "",
            "Tunggu Sebentar...", true)
        setterJob = CoroutineScope(Dispatchers.IO).launch {
            val requestBody = Request.acceptPermission(context!!, id)

            withContext(Dispatchers.Main){
                dialog.dismiss()
                Toast.makeText(context!!, "Sukses", Toast.LENGTH_SHORT).show()
                if(requestBody.result!!)
                    prepareData()
            }
        }
    }

}
