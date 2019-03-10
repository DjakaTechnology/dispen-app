package com.lutungkamarsung.dispen.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.UserModel
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {
    var model:UserModel = UserModel()
    var job: Job? = null
    var token:String = ""
    var email:String = ""
    var password:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        doAnimation()
        checkIsSignedIn()
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

    private fun checkIsSignedIn() {
        if(SharedKey.getUserModel(this) != null)
            doLogin(null)
        else
            Handler().postDelayed({
                moveToLogin()
            },2800)
    }

    private fun moveToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun moveToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun doLogin(v: View?){
        prepareFirebase()

        val prefs = getSharedPreferences(SharedKey.Session.SESSION, Context.MODE_PRIVATE)
        email = prefs.getString(SharedKey.Session.EMAIL, "")!!
        password = prefs.getString(SharedKey.Session.PASSWROD, "")!!

        job = CoroutineScope(Dispatchers.IO).launch{
            val request = Request.login(baseContext, email, password, token)

            withContext(Dispatchers.Main){
                if(request != null) {
                    model = request
                    putSharedPreferences(Gson().toJson(request))
                }else
                    moveToLogin()
            }
        }

    }

    private fun putSharedPreferences(json: String) {
        val editor = getSharedPreferences(SharedKey.Session.SESSION, Context.MODE_PRIVATE).edit()

        editor.putString(SharedKey.Session.USER, json)
        editor.putString(SharedKey.Session.TOKEN, model!!.token)
        editor.putString(SharedKey.Session.EMAIL, email)
        editor.putString(SharedKey.Session.PASSWROD, password)
        editor.apply()

        moveToMain()
    }

    private fun doAnimation() {
        YoYo.with(Techniques.FadeInUp)
            .duration(1400)
            .playOn(fl_hand)
        YoYo.with(Techniques.FadeInUp)
            .duration(1400)
            .playOn(tv_welcome)
        YoYo.with(Techniques.FadeInUp)
            .duration(2400)
            .playOn(tv_title)
    }
}
