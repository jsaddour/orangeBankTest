package com.jsaddour.orangebanktest.data.remote.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(@SerialName("Data") val data: Data)

@Serializable
data class Data(@SerialName("Account") val accounts: List<Account>)

@Serializable
data class Account(
    @SerialName("AccountId") val accountId: Long,
    @SerialName("Nickname") val nickname: String,
    @SerialName("transactionsUrl") val transactionsUrl: String,
)


