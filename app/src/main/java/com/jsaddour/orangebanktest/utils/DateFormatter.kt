package com.jsaddour.orangebanktest.utils

import java.text.SimpleDateFormat
import java.util.*


fun String.toDate(): Date {
    return SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").parse(this)
}

fun Date.toFormattedDate(): String {
    return SimpleDateFormat("dd/MM/yyyy").format(this)
}