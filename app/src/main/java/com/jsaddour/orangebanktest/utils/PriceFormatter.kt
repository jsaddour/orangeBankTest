package com.jsaddour.orangebanktest.utils

import java.text.NumberFormat
import java.util.*


fun Double.toFormattedPrice(currency: String): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.setMaximumFractionDigits(2)
    format.setCurrency(Currency.getInstance(currency))
    return format.format(this)
}

