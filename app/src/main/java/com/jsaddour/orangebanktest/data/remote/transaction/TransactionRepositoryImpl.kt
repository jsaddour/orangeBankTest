package com.jsaddour.orangebanktest.data.remote.transaction

import com.jsaddour.orangebanktest.data.remote.RemoteClient
import com.jsaddour.orangebanktest.models.Transaction
import com.jsaddour.orangebanktest.repositories.TransactionRepository

class TransactionRepositoryImpl(
    private val client: RemoteClient,
) : TransactionRepository {
    override suspend fun getTransactions(transactionsUrl: String): List<Transaction>? {
        return try {
            client.getTransactions(transactionsUrl).body()?.toDomain()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}