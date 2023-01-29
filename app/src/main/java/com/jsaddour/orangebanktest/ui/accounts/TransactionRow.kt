package com.jsaddour.orangebanktest.ui.accounts

import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jsaddour.orangebanktest.ui.transactions.TransactionViewState


@Composable
fun TransactionRow(modifier: Modifier, transaction: TransactionViewState.Item) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(transaction.date)
        Text(transaction.amount)
    }
}


