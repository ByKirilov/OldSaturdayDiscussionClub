package com.byKirilov.saturdaydiscussionclub

class Drinks {

    companion object {
        val drinks = mapOf<DrinkNames, Int>(
            DrinkNames.BEER to 5,
            DrinkNames.CIDER to 5,
            DrinkNames.WINE to 11,
            DrinkNames.SPARKLING_WINE to 11,
            DrinkNames.WHISKEY to 40,
            DrinkNames.COGNAC to 40,
            DrinkNames.GIN to 40,
            DrinkNames.ABSINTHE to 70,
            DrinkNames.VODKA to 40,
            DrinkNames.MOONSHINE to 40
        )
    }
}