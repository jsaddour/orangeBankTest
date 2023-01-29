package com.jsaddour.orangebanktest.ui.accounts

import com.jsaddour.orangebanktest.models.Account

data class AccountsViewState(
    val items: List<Item>,
    val loading: Boolean,
    val error: Event<String>?,
    val refresh: () -> Unit,
    val onAccountSelected: (uri: String) -> Unit
) {
    companion object {
        fun firstState(refresh: () -> Unit, select: (uri: String) -> Unit): AccountsViewState =
            AccountsViewState(
                emptyList(),
                true,
                null,
                refresh,
                select
            )
    }

    fun loading(): AccountsViewState = copy(loading = true, error = null)
    fun update(accounts: List<Account>): AccountsViewState =
        copy(accounts.map { it.toItem() }, false, null)

    fun error(newError: String): AccountsViewState =
        copy(loading = false, error = Event(newError))

    data class Item(
        val name: String,
        val transactionUrl: String,
    )

    private fun Account.toItem() = Item(nickname, transactionsUrl)

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