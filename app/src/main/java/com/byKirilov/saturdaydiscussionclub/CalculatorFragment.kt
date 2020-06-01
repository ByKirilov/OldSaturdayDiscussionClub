package com.byKirilov.saturdaydiscussionclub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.byKirilov.saturdaydiscussionclub.DB.DrinkNames
import kotlin.math.roundToInt

class CalculatorFragment : Fragment(),
    View.OnClickListener {

    companion object {
        private const val ARGS_NAME = "args_name"

        fun newInstance(name: String): CalculatorFragment {
            val fragment = CalculatorFragment()
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        layout = inflater.inflate(
            R.layout.fragment_calculator,
            container,
            false
        )

        val calculateButton = layout.findViewById<Button>(R.id.calculate_button)
        calculateButton.setOnClickListener(this)

        return layout
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.calculate_button -> calculateButtonOnClick()
        }
    }

    fun calculateButtonOnClick() {
        val strongCountField = layout.findViewById<EditText>(R.id.strong_persons_count)
        val strongCountValue = strongCountField.text.toString()
        val strongCount = if (strongCountValue == "") 0 else strongCountValue.toInt()

        val lightCountField = layout.findViewById<EditText>(R.id.light_persons_count)
        val lightCountValue = lightCountField.text.toString()
        val lightCount = if (lightCountValue == "") 0 else lightCountValue.toInt()

        val lightDrinks = mutableMapOf<DrinkNames, Int>()
        val strongDrinks = mutableMapOf<DrinkNames, Int>()

        fillDrinksLists(
            lightDrinks,
            strongDrinks
        )

        val result = mutableMapOf<DrinkNames, Double>()

        for (drink in lightDrinks.keys) {
            val volume = ((0.1 / lightDrinks[drink]!!) * 100 * lightCount * 100).roundToInt().toDouble() / 100
            result[drink] = volume
        }
        for (drink in strongDrinks.keys) {
            val volume = ((0.132 / strongDrinks[drink]!!) * 100 * strongCount * 100).roundToInt().toDouble() / 100
            result[drink] = volume
        }

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val fragment = CalculationResultFragment.newInstance("calculationResult").also {
            it.drinks = result
        }
        transaction!!
            .replace(R.id.fragment_container, fragment, "calc_result_fragment_tag")
            .addToBackStack(null)
            .commit()
    }

    private fun fillDrinksLists(lightDrinks: MutableMap<DrinkNames, Int>,
                                strongDrinks: MutableMap<DrinkNames, Int>) {

        addDrinkToDrinksLists(
            DrinkNames.BEER,
            lightDrinks,
            strongDrinks
        )
        addDrinkToDrinksLists(
            DrinkNames.CIDER,
            lightDrinks,
            strongDrinks
        )
        addDrinkToDrinksLists(
            DrinkNames.ABSINTHE,
            lightDrinks,
            strongDrinks
        )
        addDrinkToDrinksLists(
            DrinkNames.COGNAC,
            lightDrinks,
            strongDrinks
        )
        addDrinkToDrinksLists(
            DrinkNames.GIN,
            lightDrinks,
            strongDrinks
        )
        addDrinkToDrinksLists(
            DrinkNames.MOONSHINE,
            lightDrinks,
            strongDrinks
        )
        addDrinkToDrinksLists(
            DrinkNames.SPARKLING_WINE,
            lightDrinks,
            strongDrinks
        )
        addDrinkToDrinksLists(
            DrinkNames.VODKA,
            lightDrinks,
            strongDrinks
        )
        addDrinkToDrinksLists(
            DrinkNames.WHISKEY,
            lightDrinks,
            strongDrinks
        )
        addDrinkToDrinksLists(
            DrinkNames.WINE,
            lightDrinks,
            strongDrinks
        )
    }

    private fun addDrinkToDrinksLists(drink: DrinkNames,
                                      lightDrinks: MutableMap<DrinkNames, Int>,
                                      strongDrinks: MutableMap<DrinkNames, Int>) {

        val checkBox = layout.findViewById<CheckBox>(drink.checkBoxId)
        if (checkBox.isChecked) {
            val strength = Drinks.drinks[drink] ?: error("Unknown drink")
            if (strength <= 15) {
                lightDrinks[drink] = strength
            }
            else {
                strongDrinks[drink] = strength
            }
        }
    }
}
