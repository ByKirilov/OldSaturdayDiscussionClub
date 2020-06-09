package com.byKirilov.saturdaydiscussionclub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val ARG_NAME = "username"

        fun startActivity(context: Context, username: String) {
            val intent = Intent(
                context,
                MainActivity::class.java
            )
            intent.putExtra(
                ARG_NAME,
                username
            )
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.nav_open_drawer,
            R.string.nav_close_drawer
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = MainFragment.newInstance("main_fragment")
            transaction
                .replace(
                    R.id.fragment_container,
                    fragment,
                    "main_fragment_tag"
                )
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()

        Toast.makeText(
            this,
            "Login: ${intent.getStringExtra(ARG_NAME)}",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onNavigationItemSelected(item: MenuItem) : Boolean {
        val id = item.itemId
        val fragment: Fragment? = when (id) {
            R.id.nav_main -> MainFragment.newInstance("main_fragment")
            R.id.nav_hymn -> HymnFragment.newInstance("hymn_fragment")
            R.id.nav_alc_calc -> CalculatorFragment.newInstance("alc_calc_fragment")
            R.id.nav_jokes -> JokesFragment.newInstance("jokes_fragment")
            else -> null
        }

        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction
                .replace(
                    R.id.fragment_container,
                    fragment,
                    "fragment_tag"
                )
                .addToBackStack(null)
                .commit()
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}
