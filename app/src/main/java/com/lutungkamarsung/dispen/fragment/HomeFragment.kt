package com.lutungkamarsung.dispen.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.activity.DispensationCreateActivity
import com.lutungkamarsung.dispen.activity.PermissionCreateActivity
import com.lutungkamarsung.dispen.activity.SickCreateActivity
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        prepareNavButton(view)

        return view
    }

    fun prepareNavButton(v:View){
        v.btn_sick.setOnClickListener { startActivity(Intent(context, SickCreateActivity::class.java)) }
        v.btn_permission.setOnClickListener { startActivity(Intent(context, PermissionCreateActivity::class.java)) }
        v.btn_dispen.setOnClickListener { startActivity(Intent(context, DispensationCreateActivity::class.java)) }
    }


}
