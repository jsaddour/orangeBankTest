package com.jsaddour.orangebanktest.ui.transactions

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jsaddour.orangebanktest.R
import com.jsaddour.orangebanktest.ui.accounts.TransactionRow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class TransactionActivity : ComponentActivity() {

    private val viewModel by viewModels<TransactionViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transaction =
            intent.getParcelableExtra<TransactionViewState.Item>(TRANSACTION_EXTRA_KEY)
        setContent {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = colorResource(R.color.orange),
                            titleContentColor = Color.White
                        ),
                        title = {
                            Text(
                                color = colorResource(id = R.color.white),
                                text = stringResource(
                                    R.string.transaction_ref,
                                    transaction?.ref ?: ""
                                ),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        },
                    )
                }, content = { content() })
        }
    }

    @Composable
    fun content() {
        val state = viewModel.transactionViewState.observeAsState()

        state.value?.let { transaction ->
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(
                        R.string.transaction_ref,
                        transaction?.ref ?: ""
                    ),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = stringResource(R.string.transaction_info, transaction.info),
                    fontSize = 16.sp,
                )

                Divider(thickness = 1.dp, color = Color.Gray)

                TransactionRow(Modifier, transaction)

                Divider(thickness = 1.dp, color = Color.Gray)

            }
        }
    }

    companion object {
        const val TRANSACTION_EXTRA_KEY = "TRANSACTION_EXTRA_KEY"
    }
}
