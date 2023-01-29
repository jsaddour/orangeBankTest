package com.jsaddour.orangebanktest.ui.accounts

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.jsaddour.orangebanktest.R

@Composable
fun TransactionList(viewState: LiveData<TransactionListViewState>) {
    val context = LocalContext.current
    val state = viewState.observeAsState()


    state.value?.items?.let { transactions ->

        Column() {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = stringResource(R.string.incomes),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Column {
                transactions.first.forEach {
                    TransactionRow(
                        Modifier.clickable(onClick = { state.value?.onTransactionSelected?.invoke(it.id) }),
                        it
                    )
                    Divider(thickness = 1.dp, color = Color.Gray)
                }
            }
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = stringResource(R.string.outcomes),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Column {
                transactions.second.forEach {
                    TransactionRow(
                        Modifier.clickable(onClick = { state.value?.onTransactionSelected?.invoke(it.id) }),
                        it
                    )
                    Divider(thickness = 1.dp, color = Color.Gray)
                }
            }
        }

    }
    viewState.value?.error?.getContent()?.let { error ->
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }
}

