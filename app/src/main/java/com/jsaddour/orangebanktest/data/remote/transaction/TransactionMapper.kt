package com.jsaddour.orangebanktest.data.remote.transaction

import com.jsaddour.orangebanktest.models.Transaction
import com.jsaddour.orangebanktest.models.TransactionStatus
import com.jsaddour.orangebanktest.models.TransactionType
import com.jsaddour.orangebanktest.utils.toDate

fun TransactionResponse.toDomain() = data.transactions.map {
    Transaction(
        transactionId = it.transactionId,
        amount = it.amount.amount,
        currency = it.amount.currency,
        date = it.date.toDate(),
        type = TransactionType.values().find { transactionType -> transactionType.type == it.type },
        status = TransactionStatus.values()
            .find { transactionStatus -> transactionStatus.status == it.status },
        info = it.info,
        ref = it.ref
    )
}

