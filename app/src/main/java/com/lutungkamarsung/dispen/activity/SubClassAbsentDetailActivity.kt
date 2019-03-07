package com.lutungkamarsung.dispen.activity

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.adapter.AdapterRVImgSick
import com.lutungkamarsung.dispen.connection.ApiServices
import com.lutungkamarsung.dispen.key.MiscTools
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.GenericModel
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.activity_sub_class_absent_detail.*
import kotlinx.android.synthetic.main.nav_main.*

class SubClassAbsentDetailActivity : AppCompatActivity() {
    var model:PermissionModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_class_absent_detail)

        prepareModel()
        prepareView()
        prepareNav()
    }

    private fun prepareNav() {
        tv_nav_title.text = model!!.subClass!!.name
    }

    private fun prepareModel() {
        model = intent.getParcelableExtra(SharedKey.App.MODEL)
    }

    private fun prepareView() {
        changeTextView()
        changeColor()
        showImage()
    }

    private fun showImage() {
        if(model!!.img == null ||  model!!.img == "")
            return
        rv_img.adapter = AdapterRVImgSick(convertStringToArray(), this)
        rv_img.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, true)
    }

    private fun convertStringToArray():ArrayList<GenericModel> {
        var data = ArrayList<GenericModel>()

        for (i in MiscTools.divideComa(model!!.img.toString())) {
            data.add(GenericModel(i))
            Log.d("TAG", i)
        }

        return data
    }

    private fun changeColor(){
        when(model!!.permissionTypeId){
            1 -> tv_status.background.setColorFilter(ContextCompat.getColor(this, R.color.red_pale), PorterDuff.Mode.SRC_ATOP)
            2 -> tv_status.background.setColorFilter(ContextCompat.getColor(this, R.color.green_pale), PorterDuff.Mode.SRC_ATOP)
            3 -> tv_status.background.setColorFilter(ContextCompat.getColor(this, R.color.orange_pale), PorterDuff.Mode.SRC_ATOP)
        }
    }

    private fun changeTextView() {
        tv_title.text = model!!.userDetail!!.name.toString()
        tv_reason_duration.text = model!!.title
        tv_status.text = model!!.permissionType!!.name
        tv_desc.text = model!!.description
    }

    fun back(v:View){
        onBackPressed()
    }
}
