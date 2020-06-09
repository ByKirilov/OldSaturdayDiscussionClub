package com.byKirilov.saturdaydiscussionclub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        if (!isUserLoggedIn()) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = ClubKeyFragment.newInstance("club_key_fragment")
            transaction
                .replace(
                    R.id.fragment_container,
                    fragment,
                    "club_key_fragment_tag"
                )
                .addToBackStack(null)
                .commit()
        } else {
            launchMainActivity(firebaseAuth.currentUser)
        }
    }

    fun isUserLoggedIn(): Boolean {
        val currentUser = firebaseAuth.currentUser
        return currentUser != null
    }

    private fun launchMainActivity(user: FirebaseUser?) {
        if (user != null) {
            MainActivity.startActivity(
                this,
                user.displayName!!
            )
            finish()
        }
    }
}
