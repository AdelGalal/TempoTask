package com.tempo.tempotask.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Articles(
    var source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    var publishedAt: String?,
    val content: String?
): Parcelable