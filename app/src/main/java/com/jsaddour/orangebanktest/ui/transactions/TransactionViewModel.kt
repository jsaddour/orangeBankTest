package com.jsaddour.orangebanktest.ui.transactions

import androidx.lifecycle.*
import com.jsaddour.orangebanktest.ui.transactions.TransactionActivity.Companion.TRANSACTION_EXTRA_KEY

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _transactionViewState = MutableLiveData<TransactionViewState.Item>()
    val transactionViewState: LiveData<TransactionViewState.Item> = _transactionViewState

    val transaction: TransactionViewState.Item =
        savedStateHandle.get(TRANSACTION_EXTRA_KEY) ?: throw IllegalArgumentException(
            "TRANSACTION_EXTRA_KEY should not be empty"
        )

    init {
        _transactionViewState.value = transaction
    }

}