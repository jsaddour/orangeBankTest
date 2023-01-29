package com.jsaddour.orangebanktest.data.remote

import javax.inject.Inject


class RemoteClient @Inject constructor(
    private val bankService: BankService
) {

    suspend fun getAccounts() = bankService.getAccounts()
    suspend fun getTransactions(transactionUrl: String) = bankService.getTransactions(transactionUrl)
}