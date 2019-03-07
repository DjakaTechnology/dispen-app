package com.lutungkamarsung.dispen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.adapter.AdapterRVSubClass
import com.lutungkamarsung.dispen.adapter.AdapterRVSubClassAbsent
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.PermissionModel
import com.lutungkamarsung.dispen.model.SubClass
import kotlinx.android.synthetic.main.activity_sub_class_absent.*
import kotlinx.android.synthetic.main.nav_main.*
import kotlinx.coroutines.*

class SubClassAbsentActivity : AppCompatActivity() {
    var dataJob: Job? = null
    var data:ArrayList<PermissionModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_class_absent)

        prepareData()
        prepareNav()
    }

    private fun prepareNav() {
        tv_nav_title.text = intent.extras!!.getString(SharedKey.App.TITLE)
    }

    private fun prepareData() {
        dataJob = CoroutineScope(Dispatchers.IO).launch {
            val request = Request.getAbsentSubClass(baseContext, intent.extras!!.getInt(SharedKey.App.ID))

            withContext(Dispatchers.Main){
                if(request.size > 0){
                    data = request
                    prepareRV()
                }
            }
        }
    }

    private fun prepareRV() {
        rv_sub_class.adapter = AdapterRVSubClassAbsent(data, this)
        rv_sub_class.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    fun back(v: View){
        onBackPressed()
    }
}
