package com.lutungkamarsung.dispen.adapter

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.model.GenericModel
import kotlinx.android.synthetic.main.rv_uploading.view.*


class AdapterRVImgSick(private var data: ArrayList<GenericModel>, private var c: Context) :
    RecyclerView.Adapter<AdapterRVImgSick.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.rv_uploading, viewGroup, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.itemView.tv_id.text = (i + 1).toString() + ""

        if (data[i].value != null)
            Glide.with(c).load(data[i].value).apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_close)
                    .fitCenter()
            ).into(myViewHolder.itemView.img_thumbnail)

        myViewHolder.itemView.btn_delete.setOnClickListener {
            data.removeAt(myViewHolder.adapterPosition);
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
