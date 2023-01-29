package com.jsaddour.orangebanktest.di

import com.jsaddour.orangebanktest.data.remote.RemoteClient
import com.jsaddour.orangebanktest.data.remote.account.AccountRepositoryImpl
import com.jsaddour.orangebanktest.data.remote.transaction.TransactionRepositoryImpl
import com.jsaddour.orangebanktest.repositories.AccountRepository
import com.jsaddour.orangebanktest.repositories.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAccountRepository(
        remoteClient: RemoteClient,
    ): AccountRepository = AccountRepositoryImpl(
        client = remoteClient,
    )

    @Provides
    fun provideTransactionRepository(
        remoteClient: RemoteClient,
    ): TransactionRepository = TransactionRepositoryImpl(
        client = remoteClient,
    )

}