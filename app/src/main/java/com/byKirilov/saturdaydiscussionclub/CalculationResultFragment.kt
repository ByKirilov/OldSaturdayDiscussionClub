package com.byKirilov.saturdaydiscussionclub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class CalculationResultFragment : Fragment() {

    companion object {
        private const val ARGS_NAME = "args_name"

        fun newInstance(name: String): CalculationResultFragment {
            val fragment = CalculationResultFragment()
            val bundle = Bundle()
            bundle.putString(
                name,
                ARGS_NAME
            )
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var drinks: MutableMap<DrinkNames, Double>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(
            R.layout.fragment_calculation_result,
            container,
            false
        )

        val listAdapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_expandable_list_item_1,
            drinks.map {
                "${context?.getString(it.key.displayValueId)}\t-\t${it.value} л."
            })
        val listDrinks = layout.findViewById<ListView>(R.id.list_drinks)
        listDrinks.adapter = listAdapter

        return layout
    }
}
