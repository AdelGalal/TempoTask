package com.tempo.tempotask.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    const val YYYY_MM_DD = "yyyy-MM-dd hh:mm:ss"

    fun getSimpleDateFormate( date:String) :String
    {
        var spf =
            SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
        val newDate = spf.parse(date)
        spf = SimpleDateFormat(YYYY_MM_DD)
        return spf.format(newDate)
    }
}