package com.thecocktailapp.core.domain.utils

import java.text.SimpleDateFormat
import java.util.Locale

val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
val sdfComplete = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("es", "ES"))
val sdfDayWeekday = SimpleDateFormat("EEEE", Locale("es", "ES"))
val sdfTime = SimpleDateFormat("HH:mm", Locale("es", "ES"))
val sdfCustom = SimpleDateFormat("dd/MM", Locale("es", "ES"))