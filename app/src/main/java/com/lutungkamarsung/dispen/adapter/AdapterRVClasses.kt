package com.lutungkamarsung.dispen.adapter

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.activity.SubClassActivity
import com.lutungkamarsung.dispen.key.SharedKey
import com.lutungkamarsung.dispen.model.Classes
import kotlinx.android.synthetic.main.rv_classes.view.*


class AdapterRVClasses(private var data: ArrayList<Classes>, private var c: Context) :
    RecyclerView.Adapter<AdapterRVClasses.MyViewHolder>() {
    var count:Int = 1

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.rv_classes, viewGroup, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val v = myViewHolder.itemView
        v.tv_title.text = "Kelas "+ data[i].name

        when(count){
            1 -> v.img_icon.drawable.setColorFilter(ContextCompat.getColor(c, R.color.red_pale), PorterDuff.Mode.SRC_ATOP)
            2 -> v.img_icon.drawable.setColorFilter(ContextCompat.getColor(c, R.color.green_pale), PorterDuff.Mode.SRC_ATOP)
            3 -> v.img_icon.drawable.setColorFilter(ContextCompat.getColor(c, R.color.orange_pale), PorterDuff.Mode.SRC_ATOP)
        }

        if(count >= 3)
            count = 1
        else
            count++

        v.btn_sub_class.setOnClickListener {
            val i = Intent(c, SubClassActivity::class.java)
            i.putExtra(SharedKey.App.TITLE, data[myViewHolder.adapterPosition].name)
            i.putExtra(SharedKey.App.ID, data[myViewHolder.adapterPosition].id)
            c.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
