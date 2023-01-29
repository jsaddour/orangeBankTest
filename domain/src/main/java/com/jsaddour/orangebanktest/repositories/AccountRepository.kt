package com.jsaddour.orangebanktest.repositories

import com.jsaddour.orangebanktest.models.Account

interface AccountRepository {
    suspend fun getAccounts(): List<Account>?
}