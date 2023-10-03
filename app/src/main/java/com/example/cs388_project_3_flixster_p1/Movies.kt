package com.example.cs388_project_3_flixster_p1

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single movie from the website
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Movies {
    @SerializedName("rank")
    var rank = 0

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("author")
    var author: String? = null

    @SerializedName("book_image")
    var bookImageUrl: String? = null

    @SerializedName("description")
    var description: String? = null



    //TODO-STRETCH-GOALS amazonUrl
}