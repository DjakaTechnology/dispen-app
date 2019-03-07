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
import com.lutungkamarsung.dispen.activity.SubClassAbsentActivity
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.SubClass
import kotlinx.android.synthetic.main.rv_subclass.view.*


class AdapterRVSubClass(private var data: ArrayList<SubClass>, private var title:String, private var c: Context) :
    RecyclerView.Adapter<AdapterRVSubClass.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.rv_subclass, viewGroup, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val v = myViewHolder.itemView
        v.tv_title.text = "$title "+ data[i].name
        v.tv_count.text = data[i].activeCount.toString()

        if(data[i].activeCount == 0)
            v.img_icon.visibility = View.INVISIBLE

        v.layout.setOnClickListener {
            val i = Intent(c, SubClassAbsentActivity::class.java)
            i.putExtra(SharedKey.App.ID, data[myViewHolder.adapterPosition].id)
            i.putExtra(SharedKey.App.TITLE, "$title " + data[myViewHolder.adapterPosition].name)
            c.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
