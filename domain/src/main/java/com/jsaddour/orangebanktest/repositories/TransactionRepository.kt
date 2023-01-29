package com.jsaddour.orangebanktest.repositories

import com.jsaddour.orangebanktest.models.Transaction

interface TransactionRepository {
    suspend fun getTransactions(transactionsUrl: String): List<Transaction>?
}