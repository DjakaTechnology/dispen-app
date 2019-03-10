package com.lutungkamarsung.dispen.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.activity.SubClassAbsentDetailActivity
import com.lutungkamarsung.dispen.fragment.DispenFragment
import com.lutungkamarsung.dispen.key.MiscTools
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.PermissionModel
import kotlinx.android.synthetic.main.rv_dispen.view.*


class AdapterRVDispen(private var data: ArrayList<PermissionModel>, private var c: Context, private var dispenFragment:DispenFragment?) :
    RecyclerView.Adapter<AdapterRVDispen.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.rv_dispen, viewGroup, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val v = myViewHolder.itemView
        v.tv_title.text = data[i].userDetail!!.name.toString()

        v.tv_reason_duration.text = data[i].title
        v.tv_class.text = data[i].subClass!!.name
        v.tv_reason_duration.text = data[i].title +", Jam ke ${data[i].startHour}-${data[i].endHour}"
        v.tv_date.text = MiscTools.dateToShortDate(data[i].createdAt!!)

        v.layout.setOnClickListener {
            val i = Intent(c, SubClassAbsentDetailActivity::class.java)
            i.putExtra(SharedKey.App.MODEL, data[myViewHolder.adapterPosition])
            c.startActivity(i)
        }

        v.btn_accept.setOnClickListener { dispenFragment!!.confirmPermission(data[myViewHolder.adapterPosition].id!!, 2) }
        v.btn_decline.setOnClickListener { dispenFragment!!.confirmPermission(data[myViewHolder.adapterPosition].id!!, 3) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
