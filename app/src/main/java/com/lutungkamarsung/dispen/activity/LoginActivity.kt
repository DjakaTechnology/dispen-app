package com.lutungkamarsung.dispen.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.UserModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*
import android.content.DialogInterface.BUTTON_NEUTRAL
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging


class LoginActivity : AppCompatActivity() {
    var pref: SharedPreferences? = null
    var model: UserModel? = null
    var job: Job? = null
    var dialog:ProgressDialog? = null

    var token:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lutungkamarsung.dispen.R.layout.activity_login)

        prepareFirebase()
    }

    private fun prepareFirebase() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                token = task.result?.token!!
            })
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
    }

    fun doLogin(v: View){
        dialog = ProgressDialog.show(this, "",
            "Memasukkan anda...", true)

        job = CoroutineScope(Dispatchers.IO).launch{
            val request = Request.login(baseContext, et_email.text.toString(), et_password.text.toString(), token)

            withContext(Dispatchers.Main){
                dialog!!.dismiss()
                if(request != null) {
                    model = request
                    putSharedPreferences(Gson().toJson(request))
                }else
                    showAlert("Email atau Password Salah", "Email atau Password anda salah silahkan coba lagi")
            }
        }

    }

    private fun putSharedPreferences(json: String) {
        val editor = getSharedPreferences(SharedKey.Session.SESSION, Context.MODE_PRIVATE).edit()

        editor.putString(SharedKey.Session.USER, json)
        editor.putString(SharedKey.Session.TOKEN, model!!.token)
        editor.putString(SharedKey.Session.EMAIL, et_email.text.toString())
        editor.putString(SharedKey.Session.PASSWROD, et_password.text.toString())
        editor.apply()

        startMainActivity()
    }

    private fun startMainActivity() {
        val i = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(i)
    }

    fun showAlert(title:String, message:String) {
        val alertDialog = android.app.AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }

}
