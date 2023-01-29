package com.jsaddour.orangebanktest.usecases

import com.jsaddour.orangebanktest.repositories.AccountRepository
import javax.inject.Inject

class GetAccountsUsecase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend fun execute() = repository.getAccounts()
}