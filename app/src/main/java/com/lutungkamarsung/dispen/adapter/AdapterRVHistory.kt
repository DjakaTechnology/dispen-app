package com.lutungkamarsung.dispen.adapter

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
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
import com.lutungkamarsung.dispen.activity.SubClassAbsentDetailActivity
import com.lutungkamarsung.dispen.key.MiscTools
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.GenericModel
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.activity_sub_class_absent_detail.*
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
        v.tv_date.text = MiscTools.dateToShortDate(data[i].createdAt!!)

        v.img_icon.run {
            when(data[i].permissionTypeId){
                1 -> setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_plus))
                2 -> setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_permission_green))
                3 -> setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_clock_orange))
            }
        }

        v.tv_status.run{
            when(data[i].statusId){
                1 -> background.setColorFilter(ContextCompat.getColor(c, R.color.orange_pale), PorterDuff.Mode.SRC_ATOP)
                2 -> background.setColorFilter(ContextCompat.getColor(c, R.color.green_pale), PorterDuff.Mode.SRC_ATOP)
                3 -> background.setColorFilter(ContextCompat.getColor(c, R.color.red_pale), PorterDuff.Mode.SRC_ATOP)
            }
        }

        v.layout.setOnClickListener {
            val i = Intent(c, SubClassAbsentDetailActivity::class.java)
            i.putExtra(SharedKey.App.MODEL, data[myViewHolder.adapterPosition])
            c.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
