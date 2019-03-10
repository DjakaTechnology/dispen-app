package com.lutungkamarsung.dispen.key

import android.app.Activity
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object MiscTools{
    fun divideComa(s: String): ArrayList<String> {
        val `val` = ArrayList<String>()
        var last = -1
        while (true) {
            last++
            if (s.indexOf(',', last) == -1) {
                `val`.add(s.substring(last, s.length))
                break
            }
            `val`.add(s.substring(last, s.indexOf(',', last)))
            last = s.indexOf(',', last)
        }

        return `val`
    }

    @Throws(ParseException::class)
    fun getHour(date: String): String {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"))
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val startDate = simpleDateFormat.parse(date)
        var hour = if (startDate.getHours() / 10 === 0) "0" + startDate.getHours() else startDate.getHours().toString()
        val minute =
            if (startDate.getMinutes() / 10 === 0) "0" + startDate.getMinutes() else startDate.getMinutes().toString()
        if (hour == "00")
            hour = "12"
        return "$hour:$minute"
    }

    fun getRealPathFromURIPath(contentURI: Uri, activity: Activity): String {
        val cursor = activity.contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            return contentURI.getPath()
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }

    fun createImgPart(file:File): MultipartBody.Part {
        val mFile = RequestBody.create(MediaType.parse("image/*"), file)
        val part = MultipartBody.Part.createFormData("img", file.getName(), mFile)

        Log.e("TAG", file.getName())

        return part
    }

    fun dateToShortDate(date:String): String? {

        val dateFormat = SimpleDateFormat("EEE, dd MMM")
        return dateFormat.format(SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date))
    }
}
