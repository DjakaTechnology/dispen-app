package com.lutungkamarsung.dispen.adapter

import android.content.Context
import android.content.Intent
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
import com.lutungkamarsung.dispen.fragment.HomeParentFragment
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.GenericModel
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.rv_permission.view.*


class AdapterRVChildPermission(private var data: ArrayList<PermissionModel>, private var c: Context, private var fragment:HomeParentFragment?) :
    RecyclerView.Adapter<AdapterRVChildPermission.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.rv_permission, viewGroup, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val v = myViewHolder.itemView
        v.tv_title.text = data[i].title
        v.tv_desc.text = data[i].description

        when(data[i].permissionTypeId){
            1 -> v.img_icon.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_plus))
            2 -> v.img_icon.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_permission_green))
            3 -> v.img_icon.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_clock_orange))
        }

        v.layout.setOnClickListener {
            val i = Intent(c, SubClassAbsentDetailActivity::class.java)
            i.putExtra(SharedKey.App.MODEL, data[myViewHolder.adapterPosition])
            c.startActivity(i)
        }

        v.btn_accept.setOnClickListener {
            fragment!!.acceptPermission(data[myViewHolder.adapterPosition].id!!)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
