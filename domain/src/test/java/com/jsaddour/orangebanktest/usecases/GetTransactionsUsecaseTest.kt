package com.jsaddour.orangebanktest.usecases

import com.jsaddour.orangebanktest.models.Transaction
import com.jsaddour.orangebanktest.models.TransactionStatus
import com.jsaddour.orangebanktest.models.TransactionType
import com.jsaddour.orangebanktest.repositories.TransactionRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*


class GetTransactionsUsecaseTest {
    private val repository = mock<TransactionRepository>()
    private val usecase = GetTransactionsUsecase(repository)

    private val initialIncome = Transaction(
        transactionId = 0,
        date = Date(),
        amount = 200.0,
        currency = "EUR",
        type = TransactionType.INCOME,
        status = TransactionStatus.CANCELED,
        info = "info",
        ref = "ref"
    )

    private val initialOutcome = Transaction(
        transactionId = 0,
        date = Date(),
        amount = 200.0,
        currency = "EUR",
        type = TransactionType.OUTCOME,
        status = TransactionStatus.CANCELED,
        info = "info",
        ref = "ref"
    )

    private fun Transaction.incremente(inc: Int, status: TransactionStatus): Transaction {
        return copy(
            transactionId = transactionId + inc,
            date = Date.from(date.toInstant().plusSeconds(inc.toLong())),
            status = status
        )
    }

    @Test
    fun `should return empty lists`() = runBlocking {
        whenever(repository.getTransactions("")).thenReturn(
            listOf(initialOutcome, initialIncome)
        )

        val transactions = usecase.execute("")
        assertEquals(
            transactions,
            Pair<List<Transaction>, List<Transaction>>(emptyList(), emptyList())
        )
    }

    @Test
    fun `should return 2 last incomes and 2 last outcomes sorted by date filtering CANCELLED STATUS`() = runBlocking {
        val incomes = listOf(
            initialIncome,
            initialIncome.incremente(1, TransactionStatus.BOOKED),
            initialIncome.incremente(2, TransactionStatus.CANCELED),
            initialIncome.incremente(3, TransactionStatus.BOOKED),
            initialIncome.incremente(4, TransactionStatus.BOOKED),
        )

        val outcomes = listOf(
            initialOutcome,
            initialOutcome.incremente(1, TransactionStatus.BOOKED),
            initialOutcome.incremente(2, TransactionStatus.CANCELED),
            initialOutcome.incremente(3, TransactionStatus.BOOKED),
            initialOutcome.incremente(4, TransactionStatus.CANCELED),
        )
        whenever(repository.getTransactions("")).thenReturn(
            incomes + outcomes
        )

        val transactions = usecase.execute("")
        assertEquals(
            transactions,
            Pair(
                listOf(
                    initialIncome.incremente(4, TransactionStatus.BOOKED),
                    initialIncome.incremente(3, TransactionStatus.BOOKED)
                ),
                listOf(
                    initialOutcome.incremente(3, TransactionStatus.BOOKED),
                    initialOutcome.incremente(1, TransactionStatus.BOOKED)
                )
            )
        )
    }
}