package com.byKirilov.saturdaydiscussionclub.DB

import com.byKirilov.saturdaydiscussionclub.R

enum class DrinkNames(val value: String, val checkBoxId: Int, val displayValueId: Int) {
    BEER(
        "beer",
        R.id.beer_checkbox,
        R.string.beer
    ),
    CIDER(
        "cider",
        R.id.cider_checkbox,
        R.string.cider
    ),
    WINE(
        "wine",
        R.id.wine_checkbox,
        R.string.wine
    ),
    SPARKLING_WINE(
        "sparkling_wine",
        R.id.sparkling_wine_checkbox,
        R.string.sparkling_wine
    ),
    VODKA(
        "vodka",
        R.id.vodka_checkbox,
        R.string.vodka
    ),
    WHISKEY(
        "whiskey",
        R.id.whiskey_checkbox,
        R.string.whiskey
    ),
    COGNAC(
        "cognac",
        R.id.cognac_checkbox,
        R.string.cognac
    ),
    ABSINTHE(
        "absinthe",
        R.id.absinthe_checkbox,
        R.string.absinthe
    ),
    GIN(
        "gin",
        R.id.gin_checkbox,
        R.string.gin
    ),
    MOONSHINE(
        "moonshine",
        R.id.moonshine_checkbox,
        R.string.moonshine
    )
}