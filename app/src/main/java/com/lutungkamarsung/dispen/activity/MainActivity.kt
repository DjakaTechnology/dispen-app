package com.lutungkamarsung.dispen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lutungkamarsung.dispen.R
import com.lutungkamarsung.dispen.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var last:Int = 0
    private var homeFragment:Fragment? = null
    private var historyFragment:Fragment? = null

    private var classesFragment:Fragment? = null
    private var dispenFragment:Fragment? = null

    private var parentFragment:Fragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareBottomNav()
    }

    private fun prepareBottomNav() {
        bottom_nav.setOnNavigationItemSelectedListener(onNavigationSelected)
        bottom_nav.selectedItemId = R.id.nav_home
    }

    private val onNavigationSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.nav_home -> {
                if(homeFragment == null)
                    homeFragment = HomeFragment()
                replaceFrame(homeFragment, 0)
                true
            }
            R.id.nav_history -> {
                if(historyFragment == null)
                    historyFragment = HistoryFragment()
                replaceFrame(historyFragment, 1)
                true
            }
            R.id.nav_classes->{
                if(classesFragment == null)
                    classesFragment = ClassesFragment()
                replaceFrame(classesFragment, 0)
                true
            }
            R.id.nav_dispen->{
                if(dispenFragment == null)
                    dispenFragment = DispenFragment()
                replaceFrame(dispenFragment, 1)
                true
            }
            R.id.nav_parent -> {
                if(parentFragment == null)
                    parentFragment = HomeParentFragment()
                replaceFrame(parentFragment, 0)
                true
            }
            else -> {
                false
            }
        }
    }

    private fun replaceFrame(f: Fragment?, i:Int){
        val transaction = supportFragmentManager.beginTransaction()
        if (last > i)
            transaction.setCustomAnimations(
                R.animator.enter_from_left,
                R.animator.exit_to_right
            )
        else
            transaction.setCustomAnimations(
                R.animator.enter_from_right,
                R.animator.exit_to_left
            )
        if (f != null) {
            transaction.replace(R.id.frame_container, f)
        }
        transaction.addToBackStack(null)
        transaction.commit()

        last = i
    }
}
