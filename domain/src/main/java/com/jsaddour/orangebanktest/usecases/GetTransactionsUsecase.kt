package com.jsaddour.orangebanktest.usecases

import com.jsaddour.orangebanktest.models.Transaction
import com.jsaddour.orangebanktest.models.TransactionStatus
import com.jsaddour.orangebanktest.models.TransactionType
import com.jsaddour.orangebanktest.repositories.TransactionRepository
import javax.inject.Inject

class GetTransactionsUsecase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend fun execute(transactionUrl: String): Pair<List<Transaction>, List<Transaction>>? {
        val transactions = repository.getTransactions(transactionUrl)
            ?: return null

        val incomes = transactions
            .filter { it.status != TransactionStatus.CANCELED }
            .filter { it.type == TransactionType.INCOME }
            .sortedByDescending { it.date }
            .take(2)

        val outcomes = transactions
            .filter { it.status != TransactionStatus.CANCELED }
            .filter { it.type == TransactionType.OUTCOME }
            .sortedByDescending { it.date }
            .take(2)

        return Pair(incomes, outcomes)
    }
}