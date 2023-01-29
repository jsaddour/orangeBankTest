package com.jsaddour.orangebanktest.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsaddour.orangebanktest.usecases.GetAccountsUsecase
import com.jsaddour.orangebanktest.usecases.GetTransactionsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val getAccountsUsecase: GetAccountsUsecase,
    private val getTransactionsUsecase: GetTransactionsUsecase,
) : ViewModel() {

    private val _accountsViewState =
        MutableLiveData(AccountsViewState.firstState(::refresh, ::getTransactions))
    val accountsViewState: LiveData<AccountsViewState> = _accountsViewState

    private val _transactionListViewState =
        MutableLiveData(TransactionListViewState.firstState(::getTransaction))
    val transactionListViewState: LiveData<TransactionListViewState> = _transactionListViewState

    private var selectedAccount: String? = null

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            _accountsViewState.value = accountsViewState.value?.loading()
            val accounts = getAccountsUsecase.execute()
            if (accounts != null) {
                _accountsViewState.value = accountsViewState.value?.update(accounts)
                selectedAccount?.let {
                    getTransactions(it)
                }
            } else {
                _accountsViewState.value = accountsViewState.value?.error("an error occured")
            }
        }
    }

    private fun getTransactions(name: String) {
        selectedAccount = name
        viewModelScope.launch {
            _transactionListViewState.value = _transactionListViewState.value?.loading()
            _accountsViewState.value?.items?.find { it.name == name }?.let { account ->
                val transactions = getTransactionsUsecase.execute(account.transactionUrl)
                if (transactions != null) {
                    _transactionListViewState.value =
                        transactionListViewState.value?.update(transactions)
                } else {
                    _transactionListViewState.value =
                        transactionListViewState.value?.error("an error occured")
                }
            }
        }
    }

    private fun getTransaction(transactionId: Long) {
        transactionListViewState.value?.items?.first?.find { it.id == transactionId }
            ?.let { transaction ->
                _transactionListViewState.value =
                    transactionListViewState.value?.navigateToTransaction(transaction)
            }
        transactionListViewState.value?.items?.second?.find { it.id == transactionId }
            ?.let { transaction ->
                _transactionListViewState.value =
                    transactionListViewState.value?.navigateToTransaction(transaction)
            }
    }
}