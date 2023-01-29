package com.jsaddour.orangebanktest.data.remote.account

import com.jsaddour.orangebanktest.data.remote.RemoteClient
import com.jsaddour.orangebanktest.models.Account
import com.jsaddour.orangebanktest.repositories.AccountRepository

class AccountRepositoryImpl(
    private val client: RemoteClient,
) : AccountRepository {
    override suspend fun getAccounts(): List<Account>? {
        return try {
            client.getAccounts().body()?.toDomain()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}