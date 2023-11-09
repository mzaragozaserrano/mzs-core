package com.mzaragozaserrano.domain.utils

import java.text.SimpleDateFormat
import java.util.Date

fun getCurrentDate(simpleDateFormat: SimpleDateFormat) = simpleDateFormat.format(Date())