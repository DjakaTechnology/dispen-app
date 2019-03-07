package com.lutungkamarsung.dispen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.adapter.AdapterRVClasses
import com.lutungkamarsung.dispen.adapter.AdapterRVSubClass
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.SubClass
import kotlinx.android.synthetic.main.activity_sub_class.*
import kotlinx.android.synthetic.main.nav_main.*
import kotlinx.coroutines.*

class SubClassActivity : AppCompatActivity() {
    var dataJob: Job? = null
    var data:ArrayList<SubClass> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_class)

        prepareData()
        prepareNav()
    }

    private fun prepareNav() {
        tv_nav_title.text = "Kelas " + intent.extras!!.getString(SharedKey.App.TITLE)
    }

    private fun prepareData() {
        dataJob = CoroutineScope(Dispatchers.IO).launch {
            val request = Request.getSubClasses(baseContext, intent.extras!!.getInt(SharedKey.App.ID))

            withContext(Dispatchers.Main){
                if(request.size > 0){
                    data = request
                    prepareRV()
                }
            }
        }
    }

    private fun prepareRV() {
        rv_sub_class.adapter = AdapterRVSubClass(data, intent.extras!!.getString(SharedKey.App.TITLE)!!, this)
        rv_sub_class.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    fun back(v: View){
        onBackPressed()
    }
}
