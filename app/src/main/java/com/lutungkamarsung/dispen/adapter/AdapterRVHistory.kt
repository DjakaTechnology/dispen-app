package com.lutungkamarsung.dispen.adapter

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.model.GenericModel
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.rv_history.view.*
import kotlinx.android.synthetic.main.rv_uploading.view.*


class AdapterRVHistory(private var data: ArrayList<PermissionModel>, private var c: Context) :
    RecyclerView.Adapter<AdapterRVHistory.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.rv_history, viewGroup, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val v = myViewHolder.itemView
        v.tv_title.text = data[i].title
        v.tv_status.text = data[i].status!!.name

        when(data[i].permissionTypeId){
            1 -> v.img_icon.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_plus))
            2 -> v.img_icon.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_permission_green))
            3 -> v.img_icon.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_clock_orange))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
