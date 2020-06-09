package com.byKirilov.saturdaydiscussionclub

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginFragment : Fragment() {

    companion object {
        private const val RC_SIGN_IN = 1001

        private const val ARGS_NAME = "args_name"

        fun newInstance(name: String): LoginFragment {
            val fragment = LoginFragment()
            val bundle = Bundle()
            bundle.putString(
                name,
                ARGS_NAME
            )
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var layout: View

    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        layout = inflater.inflate(
            R.layout.fragment_login,
            container,
            false
        )

        val signInButton = layout.findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setOnClickListener { signInToGoogle() }

        configureGoogleClient()

        return layout
    }

    fun configureGoogleClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(
            context!!,
            gso
        )

        val signInButton = layout.findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_WIDE)

        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun signInToGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(
            signInIntent,
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                Toast.makeText(
                    context,
                    "Google Sign in Succeeded",
                    Toast.LENGTH_LONG
                ).show()
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Toast.makeText(
                    context,
                    "Google sign in failed $e",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(
            account.idToken,
            null
        )
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(
                activity!!,
                object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful) {
                            val user = firebaseAuth.currentUser
                            addUserToDatabase(user)
                            launchMainActivity(user)
                        }
                    }
                })
    }

    fun addUserToDatabase(user: FirebaseUser?) {
        val database = FirebaseDatabase.getInstance().getReference("users")

        val userReference = database.child(user?.uid.toString())
        userReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    userReference.setValue(User(user?.displayName))
                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })
    }

    private fun launchMainActivity(user: FirebaseUser?) {
        if (user != null) {
            MainActivity.startActivity(
                context!!,
                user.displayName!!
            )
            activity!!.finish()
        }
    }
}
