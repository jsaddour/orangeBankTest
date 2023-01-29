package com.jsaddour.orangebanktest.data.remote
import com.jsaddour.orangebanktest.data.remote.account.AccountResponse
import com.jsaddour.orangebanktest.data.remote.transaction.TransactionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface BankService {
    @GET("ea42529b-1a24-4f3e-9ba4-8e6665666d6b")
    suspend fun getAccounts(): Response<AccountResponse>

    @GET
    suspend fun getTransactions(@Url transactionUrl: String): Response<TransactionResponse>
}