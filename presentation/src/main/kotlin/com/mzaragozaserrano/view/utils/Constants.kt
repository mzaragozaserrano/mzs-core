package com.mzaragozaserrano.view.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale

const val emptyText: String = ""
val emptyLambda: () -> Unit = { Log.d("EMPTY_LAMBDA", emptyText) }

val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
val sdfComplete = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("es", "ES"))
val sdfDayWeekday = SimpleDateFormat("EEEE", Locale("es", "ES"))
val sdfTime = SimpleDateFormat("HH:mm", Locale("es", "ES"))
val sdfCustom = SimpleDateFormat("dd/MM", Locale("es", "ES"))
