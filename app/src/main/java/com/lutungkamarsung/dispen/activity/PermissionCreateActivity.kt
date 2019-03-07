package com.lutungkamarsung.dispen.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.activity_permission_create.*
import kotlinx.android.synthetic.main.nav_main.*
import kotlinx.coroutines.*

class PermissionCreateActivity : AppCompatActivity() {
    var days:Int = 1
    var submitJob:Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_create)

        prepareNav()
        prepareBtnDays()
    }

    private fun prepareNav() {
        tv_nav_title.text = "Izin"
    }

    private fun prepareBtnDays(){
        btn_day_1.setOnClickListener { changeDays(1) }
        btn_day_2.setOnClickListener { changeDays(2) }
        btn_day_3.setOnClickListener { changeDays(3) }
    }

    private fun changeDays(days: Int){
        changeColor(days)
        this.days = days
    }

    private fun changeColor(day:Int) {
        val btnDays = arrayOf(btn_day_1, btn_day_2, btn_day_3)
        for (i in 0 until btnDays.size){
            if(i + 1 == day) {
                btnDays[i].setTextColor(Color.WHITE)
                btnDays[i].background.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP)
            } else{
                btnDays[i].setTextColor(ContextCompat.getColor(this, R.color.black_main))
                btnDays[i].background.colorFilter = null
            }
        }
    }

    fun submit(v:View){
        submitJob = CoroutineScope(Dispatchers.IO).launch {
            val result = Request.submitPermission(baseContext, PermissionModel(days = days, title = et_title.text.toString(), description = et_desc.text.toString()))

            withContext(Dispatchers.Main){
                if(result.result == true) {
                    onSucceed()
                }
            }
        }
    }

    private fun onSucceed() {
        val i = Intent(this, SuccessActivity::class.java)
        i.putExtra(SharedKey.Result.MESSAGE, "Surat berhasil dibuat. Tunggu konfirmasi dari orang tuamu yaa \uD83D\uDE01")
        startActivity(i)
        finish()
    }


    fun back(v: View){
        onBackPressed()
    }

    override fun onDestroy() {
        submitJob?.cancel()
        super.onDestroy()
    }
}
