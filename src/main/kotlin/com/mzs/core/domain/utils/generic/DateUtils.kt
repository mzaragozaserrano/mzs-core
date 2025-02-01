package com.mzs.core.domain.utils.generic

import com.mzs.core.R
import com.mzs.core.data.datasources.local.ResourcesDataSource
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

const val ddMM = "dd/MM"
const val ddMMyyyy = "dd/MM/yyyy"
const val ddMMyyyy_HHmm = "dd/MM/yyyy HH:mm"
const val HHmm = "HH:mm"

class DateUtils(private val resourcesDataSource: ResourcesDataSource) {

    fun getCurrentDate(formatOut: String): String? {
        return try {
            val sdf = SimpleDateFormat(formatOut, Locale.getDefault())
            sdf.format(Date())
        } catch (e: Exception) {
            null
        }
    }

    fun convertStringToLocalDate(dateString: String, format: String): LocalDate? {
        return try {
            val formatter = DateTimeFormatter.ofPattern(format)
            LocalDate.parse(dateString, formatter)
        } catch (e: Exception) {
            null
        }
    }

    fun formatDateToFriendlyString(date: LocalDate?): String {
        return if (date == null) {
            resourcesDataSource.getStringFromResource(resId = R.string.date_today)
        } else {
            when {
                date.isEqual(LocalDate.now()) -> {
                    resourcesDataSource.getStringFromResource(resId = R.string.date_today)
                }

                date.isEqual(LocalDate.now().minusDays(1)) -> {
                    resourcesDataSource.getStringFromResource(resId = R.string.date_yesterday)
                }

                else -> {
                    date.format(DateTimeFormatter.ofPattern(ddMMyyyy))
                }
            }
        }
    }

}