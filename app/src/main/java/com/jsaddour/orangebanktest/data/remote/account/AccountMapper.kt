package com.jsaddour.orangebanktest.data.remote.account

import com.jsaddour.orangebanktest.models.Account

fun AccountResponse.toDomain() = data.accounts.map {
    Account(
        accountId = it.accountId,
        nickname = it.nickname,
        transactionsUrl = it.transactionsUrl
    )
}
