package com.lutungkamarsung.dispen.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.activity_dispensation_create.*
import kotlinx.android.synthetic.main.nav_main.*
import kotlinx.coroutines.*

class DispensationCreateActivity : AppCompatActivity() {
    var submitJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispensation_create)

        prepareNav()
    }

    private fun prepareNav() {
        tv_nav_title.text = "Dispensasi"
    }

    fun submit(v:View){
        submitJob = CoroutineScope(Dispatchers.IO).launch {
            val request = Request.submitDispentation(baseContext,
                PermissionModel(title = et_title.text.toString(),
                                startHour = Integer.parseInt(sp_begin.selectedItem.toString()),
                                endHour = Integer.parseInt(sp_end.selectedItem.toString()),
                                description = et_desc.text.toString()))
            withContext(Dispatchers.Main){
                if(request!!.result!!)
                    onSucceed()
            }
        }
    }

    private fun onSucceed() {
        val i = Intent(this, SuccessActivity::class.java)
        i.putExtra(SharedKey.Result.MESSAGE, "Surat berhasil dibuat. Tunggu konfirmasi dari wali kelas yaa \uD83D\uDE01")
        startActivity(i)
        finish()
    }

    fun back(v: View){
        onBackPressed()
    }
}
