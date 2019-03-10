package com.lutungkamarsung.dispen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.key.SharedKey
import kotlinx.android.synthetic.main.activity_success.*

class SuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        try {
            tv_message.text = intent.extras!!.getString(SharedKey.Result.MESSAGE)
        }catch (e:Exception){

        }
    }

    fun back(){
        finish()
    }
}
