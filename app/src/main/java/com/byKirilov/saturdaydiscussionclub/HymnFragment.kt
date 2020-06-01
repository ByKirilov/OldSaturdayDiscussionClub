package com.byKirilov.saturdaydiscussionclub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class HymnFragment : Fragment() {

    companion object {
        private const val ARGS_NAME = "args_name"

        fun newInstance(name: String) : HymnFragment {
            val fragment = HymnFragment()
            val bundle = Bundle()
            bundle.putString(name, ARGS_NAME)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_hymn,
            container,
            false
        )
    }
}
