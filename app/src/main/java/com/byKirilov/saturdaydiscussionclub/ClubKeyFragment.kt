package com.byKirilov.saturdaydiscussionclub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class ClubKeyFragment : Fragment() {

    companion object {
        private const val ARGS_NAME = "args_name"

        fun newInstance(name: String) : ClubKeyFragment {
            val fragment = ClubKeyFragment()
            val bundle = Bundle()
            bundle.putString(name, ARGS_NAME)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var layout: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        layout = inflater.inflate(
            R.layout.fragment_club_key,
            container,
            false
        )

        val submitButton = layout.findViewById<Button>(R.id.submit_club_key)
        submitButton.setOnClickListener { check_key() }

        return layout
    }

    fun check_key() {
        val keyField = layout.findViewById<EditText>(R.id.club_key)
        val key = keyField.text.toString()

        if (key == ClubKey.key)
        {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            val fragment = LoginFragment.newInstance("login_fragment")
            transaction!!
                .replace(R.id.fragment_container, fragment, "login_fragment_tag")
                .addToBackStack(null)
                .commit()
        } else {
            Toast.makeText(
                context,
                "Неправильный ключ",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
