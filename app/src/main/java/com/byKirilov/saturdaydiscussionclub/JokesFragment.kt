package com.byKirilov.saturdaydiscussionclub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class JokesFragment : Fragment() {

    companion object {
        private const val ARGS_NAME = "args_name"

        fun newInstance(name: String) =
            JokesFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        ARGS_NAME,
                        name
                    )
                }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_jokes,
            container,
            false
        )
    }
}
