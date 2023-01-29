package com.jsaddour.orangebanktest.models

import java.util.Date

data class Transaction(
    val transactionId: Long,
    val amount: Double,
    val currency: String,
    val date: Date,
    val type: TransactionType?,
    val status: TransactionStatus?,
    val info: String,
    val ref: String,
)


enum class TransactionType(val type: String) {
    INCOME("Credit"),
    OUTCOME("Debit"),
}

enum class TransactionStatus(val status: String) {
    BOOKED("Booked"),
    CANCELED("Canceled"),
}