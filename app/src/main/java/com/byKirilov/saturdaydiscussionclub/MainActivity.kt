package com.byKirilov.saturdaydiscussionclub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

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
}
