package com.jsaddour.orangebanktest.ui.accounts

import com.jsaddour.orangebanktest.models.Transaction
import com.jsaddour.orangebanktest.ui.transactions.TransactionViewState.Item
import com.jsaddour.orangebanktest.ui.transactions.toItem

data class TransactionListViewState(
    val items: Pair<List<Item>, List<Item>>?,
    val loading: Boolean,
    val error: Event<String>?,
    val navigateToTransaction: Event<Item>?,
    val onTransactionSelected: (id: Long) -> Unit
) {
    companion object {
        fun firstState(select: (uri: Long) -> Unit): TransactionListViewState =
            TransactionListViewState(
                null, true, null, null, select
            )
    }

    fun navigateToTransaction(transaction: Item): TransactionListViewState =
        copy(navigateToTransaction = Event(transaction))

    fun loading(): TransactionListViewState = copy(items = null, loading = true, error = null)
    fun update(transactions: Pair<List<Transaction>, List<Transaction>>): TransactionListViewState =
        copy(
            Pair(transactions.first.map { it.toItem() },
                transactions.second.map { it.toItem() }
            ),
            false,
            null
        )

    fun error(newError: String): TransactionListViewState =
        copy(items = null, loading = false, error = Event(newError))


    class Event<out T>(private val content: T) {
        private var consumed = false


        fun getContent(): T? {
            return if (consumed) {
                null
            } else {
                consumed = true
                content
            }
        }
    }
}