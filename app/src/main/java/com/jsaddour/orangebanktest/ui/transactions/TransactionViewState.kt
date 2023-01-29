package com.jsaddour.orangebanktest.ui.transactions

import android.os.Parcelable
import com.jsaddour.orangebanktest.models.Transaction
import com.jsaddour.orangebanktest.utils.toFormattedDate
import com.jsaddour.orangebanktest.utils.toFormattedPrice
import kotlinx.parcelize.Parcelize

data class TransactionViewState(
    val items: Pair<List<Item>, List<Item>>?,
) {
    @Parcelize
    data class Item(
        val id: Long,
        val date: String,
        val amount: String,
        val info: String,
        val ref: String,
    ) : Parcelable
}

fun Transaction.toItem() =
    TransactionViewState.Item(
        id = transactionId,
        date = date.toFormattedDate(),
        amount = amount.toFormattedPrice(currency),
        info = info,
        ref = ref
    )