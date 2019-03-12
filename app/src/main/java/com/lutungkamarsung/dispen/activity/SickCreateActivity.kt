package com.lutungkamarsung.dispen.activity

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.adapter.AdapterRVImgSick
import com.lutungkamarsung.dispen.connection.Request
import com.lutungkamarsung.dispen.key.MiscTools
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.GenericModel
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.activity_sick_create.*
import kotlinx.android.synthetic.main.nav_main.*
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class SickCreateActivity : AppCompatActivity() {
    companion object {
        const   val REQUEST_GALLERY_CODE = 200
        const val READ_REQUEST_CODE = 300
    }

    private var uri: Uri? = null
    private var uploadJob: Job? = null
    private var submitJob: Job? = null
    private var imgData : ArrayList<GenericModel> = ArrayList()
    private var days: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sick_create)

        prepareNav()
        prepareBtnDays()
    }

    private fun prepareNav() {
        tv_nav_title.text = "Sakit"
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

    fun back(v: View){
        onBackPressed()
    }

    fun submit(v:View){
        val img:String = combineImgArray()

        submitJob = CoroutineScope(Dispatchers.IO).launch {
            val result = Request.submitSick(baseContext,
                PermissionModel(days = days, title = et_title.text.toString(), description = et_desc.text.toString(), img = img))

            withContext(Dispatchers.Main){
                if(result.result == true) {
                    onSucceedSubmit(result)
                }
            }
        }
    }

    private fun onSucceedSubmit(result: GenericModel) {
        val i = Intent(this, SuccessActivity::class.java)
        i.putExtra(SharedKey.Result.MESSAGE, "Surat berhasil dibuat. Tunggu konfirmasi dari orang tuamu yaa \uD83D\uDE01")
        startActivity(i)
        finish()
    }

    private fun combineImgArray(): String {
        var img = ""
        for (i in imgData) {
            img += "${i.value},"
        }

        if (imgData.size > 1)
            img = img.substring(0, img.length - 1)
        return img
    }

    @AfterPermissionGranted(READ_REQUEST_CODE)
    fun showGallery(v:View) {
        if (EasyPermissions.hasPermissions(this, READ_EXTERNAL_STORAGE)) {
            val i = Intent(Intent.ACTION_PICK)
            i.type = "image/*"
            startActivityForResult(i, REQUEST_GALLERY_CODE)
        } else {
            EasyPermissions.requestPermissions(this, "Required Permission", READ_REQUEST_CODE, READ_EXTERNAL_STORAGE)
            Log.e("HAPPEN", "HAPPEN")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            uri = data!!.data
            if (EasyPermissions.hasPermissions(this, READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Uploading...", Toast.LENGTH_SHORT).show()
                doUpload(MiscTools.createImgPart(
                    File(MiscTools.getRealPathFromURIPath(uri!!, this)))
                )
            } else {
                EasyPermissions.requestPermissions(this, "Required Permission", READ_REQUEST_CODE, READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun doUpload(fileToUpload: MultipartBody.Part) {
        uploadJob = CoroutineScope(Dispatchers.IO).launch {
            val result = Request.uploadImg(baseContext, fileToUpload)

            withContext(Dispatchers.Main) {
                if (result.result == true) onSucceed(result)
            }
        }
    }

    private fun onSucceed(model:GenericModel) {
        if(model.value == "") return

        imgData.add(model)
        val adapter = AdapterRVImgSick(imgData, this)
        rv_img.adapter = adapter
        rv_img.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, true)
    }
}
