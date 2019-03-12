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
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.Classes
import com.lutungkamarsung.dispen.model.PermissionModel
import com.lutungkamarsung.dispen.model.UserModel
import kotlinx.android.synthetic.main.fragment_classes.view.*
import kotlinx.coroutines.*

class ClassesFragment : Fragment() {
    var data:ArrayList<Classes> = ArrayList()
    var dataJob: Job? = null
    var userModel:UserModel = UserModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.fragment_classes, container, false)

        preapreUserModel()
        prepareRV(v)
        prepareData()
        prepareTV(v)

        return v
    }

    private fun preapreUserModel() {
        userModel = SharedKey.getUserModel(context!!)!!
    }

    private fun prepareTV(v:View){
        v.tv_greeting.text = "Selamat Pagi, ${userModel.userDetail!!.name}"
        v.tv_school.text = userModel.userDetail!!.subClass!!.classes!!.school!!.name
    }

    private fun prepareData() {
        dataJob = CoroutineScope(Dispatchers.IO).launch {
            val request = Request.getClasses(context!!)

            withContext(Dispatchers.Main){
                data = request
                if(view != null)
                    prepareRV(view!!)
            }
        }
    }

    private fun prepareRV(view: View) {
        view.rv_classes.run {
            adapter = AdapterRVClasses(data, context!!)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }
}
