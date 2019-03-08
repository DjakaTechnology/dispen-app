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
import com.lutungkamarsung.dispen.adapter.AdapterRVChildPermission
import com.lutungkamarsung.dispen.adapter.AdapterRVDispen
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.fragment_home_parent.view.*
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class HomeParentFragment : Fragment() {
    var dataJob: Job? = null
    var setterJob: Job? = null
    var childJob:Job? = null
    var data:ArrayList<PermissionModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v =  inflater.inflate(R.layout.fragment_home_parent, container, false)

        prepareRV(v)
        prepareTV(v)
        prepareData()

        return v
    }

    private fun prepareTV(v:View){
        v.tv_greeting.text = "Selamat Pagi, " + SharedKey.getUserModel(context!!)!!.userDetail!!.name
        v.tv_school.text = ""

        getChildData(v)
    }

    private fun getChildData(v:View){
        childJob = CoroutineScope(Dispatchers.IO).launch{
            val request = Request.child(context!!)

            withContext(Dispatchers.Main){
                if(request != null){
                    v.tv_school.text = request.subClass!!.classes!!.school!!.name
                }
            }
        }
    }

    private fun prepareData() {
        dataJob = CoroutineScope(Dispatchers.IO).launch {
            val request = Request.getPermissionMyChild(context!!)

            withContext(Dispatchers.Main){
                data = request
                prepareRV(view!!)
            }
        }
    }

    private fun prepareRV(view: View) {
        view.rv_permission.adapter = AdapterRVChildPermission(data, context!!, this)
        view.rv_permission.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
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
