package com.byKirilov.saturdaydiscussionclub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), View.OnClickListener {

    companion object {
        private const val ARGS_NAME = "args_name"

        fun newInstance(name: String) : MainFragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putString(name, ARGS_NAME)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(
            R.layout.fragment_main,
            container,
            false
        )

        setOnClickListener(layout)

        return layout
    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.finish()
    }

    fun setOnClickListener(layout: View){
        val hymnButton = layout.findViewById<Button>(R.id.hymn_button)
        hymnButton.setOnClickListener(this)
        val calcButton = layout.findViewById<Button>(R.id.alc_calc_button)
        calcButton.setOnClickListener(this)
        val jokesButton = layout.findViewById<Button>(R.id.jokes_button)
        jokesButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.hymn_button -> hymnButtonOnClick()
            R.id.alc_calc_button -> calcButtonOnClick()
            R.id.jokes_button -> jokesButtonOnClick()
        }
    }

    fun hymnButtonOnClick() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val fragment = HymnFragment.newInstance("hymn_fragment")
        transaction!!
            .replace(R.id.fragment_container, fragment, "hymn_fragment_tag")
            .addToBackStack(null)
            .commit()
    }

    fun calcButtonOnClick() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val fragment = CalculatorFragment.newInstance("calc_fragment")
        transaction!!
            .replace(R.id.fragment_container, fragment, "calc_fragment_tag")
            .addToBackStack(null)
            .commit()
    }

    fun jokesButtonOnClick() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val fragment = JokesFragment.newInstance("jokes_fragment")
        transaction!!
            .replace(R.id.fragment_container, fragment, "jokes_fragment_tag")
            .addToBackStack(null)
            .commit()
    }
}
