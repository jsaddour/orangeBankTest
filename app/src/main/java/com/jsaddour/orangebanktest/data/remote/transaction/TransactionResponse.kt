package com.jsaddour.orangebanktest.data.remote.transaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponse(@SerialName("Data") val data: Data)

@Serializable
data class Data(@SerialName("Transaction") val transactions: List<Transaction>)

@Serializable
data class Transaction(
    @SerialName("TransactionId") val transactionId: Long,
    @SerialName("Amount") val amount: Amount,
    @SerialName("ValueDateTime") val date: String,
    @SerialName("CreditDebitIndicator") val type: String,
    @SerialName("Status") val status: String,
    @SerialName("TransactionInformation") val info: String,
    @SerialName("TransactionReference") val ref: String,
)


@Serializable
data class Amount(
    @SerialName("Amount") val amount: Double,
    @SerialName("Currency") val currency: String,
)

