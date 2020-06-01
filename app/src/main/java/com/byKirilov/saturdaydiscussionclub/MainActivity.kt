package com.byKirilov.saturdaydiscussionclub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = MainFragment.newInstance("main_fragment")
            transaction
                .add(R.id.fragment_container, fragment, "main_fragment_tag")
                .addToBackStack(null)
                .commit()
        }
    }
}
