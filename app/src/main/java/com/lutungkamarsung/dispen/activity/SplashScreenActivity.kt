package com.lutungkamarsung.dispen.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.lutungkamarsung.dispen.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        doAnimation()
        moveToLogin()
    }

    private fun moveToLogin() {
        Handler().postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            },2800)
    }

    private fun doAnimation() {
        YoYo.with(Techniques.Tada)
            .duration(700)
            .repeat(5)
            .playOn(tv_hand)

        YoYo.with(Techniques.Landing)
            .duration(1400)
            .playOn(fl_hand)

        YoYo.with(Techniques.Landing)
            .duration(2400)
            .playOn(tv_welcome)
        YoYo.with(Techniques.FadeInUp)
            .duration(2400)
            .playOn(tv_title)
    }
}
