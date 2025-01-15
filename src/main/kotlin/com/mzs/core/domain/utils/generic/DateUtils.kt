package com.mzs.core.domain.utils.generic

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val ddMM = "dd/MM"
const val ddMMyyyy = "dd/MM/yyyy"
const val ddMMyyyy_HHmm = "dd/MM/yyyy HH:mm"
const val HHmm = "HH:mm"

class DateUtils {
    fun getCurrentDate(formatOut: String): String {
        val sdf = SimpleDateFormat(formatOut, Locale.getDefault())
        return sdf.format(Date())
    }
}