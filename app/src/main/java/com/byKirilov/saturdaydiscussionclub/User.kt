package com.byKirilov.saturdaydiscussionclub

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(

    val name: String? = ""
)