package com.lutungkamarsung.dispen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.key.SharedKey
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.nav_main.*

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        prepareNav()
        prepareImage()
    }

    private fun prepareImage() {
        Glide.with(this).load(intent.getStringExtra(SharedKey.App.ID)).apply(
            RequestOptions()
                .placeholder(R.drawable.loading)
                .centerInside()).into(img)
    }

    private fun prepareNav() {
        tv_nav_title.text = "Lihat Gambar"
    }

    fun back(v: View){finish()}

}
