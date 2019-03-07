package com.lutungkamarsung.dispen.adapter

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.activity.SubClassAbsentDetailActivity
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.rv_subclass_absent.view.*


class AdapterRVSubClassAbsent(private var data: ArrayList<PermissionModel>, private var c: Context) :
    RecyclerView.Adapter<AdapterRVSubClassAbsent.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.rv_subclass_absent, viewGroup, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val v = myViewHolder.itemView
        v.tv_title.text = data[i].userDetail!!.name.toString()

        v.tv_reason_duration.text = data[i].title
        v.tv_status.text = data[i].permissionType!!.name

        when(data[i].permissionTypeId){
            1 -> v.tv_status.background.setColorFilter(ContextCompat.getColor(c, R.color.red_pale), PorterDuff.Mode.SRC_ATOP)
            2 -> v.tv_status.background.setColorFilter(ContextCompat.getColor(c, R.color.green_pale), PorterDuff.Mode.SRC_ATOP)
            3 -> v.tv_status.background.setColorFilter(ContextCompat.getColor(c, R.color.orange_pale), PorterDuff.Mode.SRC_ATOP)
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
