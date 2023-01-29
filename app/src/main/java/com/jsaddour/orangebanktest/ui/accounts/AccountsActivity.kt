package com.jsaddour.orangebanktest.ui.accounts

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jsaddour.orangebanktest.R
import com.jsaddour.orangebanktest.ui.transactions.TransactionActivity
import com.jsaddour.orangebanktest.ui.transactions.TransactionActivity.Companion.TRANSACTION_EXTRA_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class AccountsActivity : ComponentActivity() {
    private val viewModel by viewModels<AccountsViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                                text = stringResource(R.string.app_name),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        },
                    )
                }, content = { content() })
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun content() {
        val context = LocalContext.current
        viewModel.transactionListViewState.observeAsState().value?.navigateToTransaction?.getContent()
            ?.let { transaction ->
                val intent = Intent(context, TransactionActivity::class.java)
                intent.putExtra(TRANSACTION_EXTRA_KEY, transaction)
                context.startActivity(intent)
            }
        val state = viewModel.accountsViewState.observeAsState()

        val loading = state.value?.loading ?: false

        val pullRefreshState =
            rememberPullRefreshState(
                loading,
                { state.value?.refresh?.invoke() })

        Box(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize()
                .pullRefresh(pullRefreshState)
                .verticalScroll(rememberScrollState())
        ) {

            Column(Modifier.fillMaxSize()) {
                AccountsSelector(viewModel.accountsViewState)
                TransactionList(viewModel.transactionListViewState)
            }
            PullRefreshIndicator(
                loading,
                pullRefreshState,
                Modifier
                    .align(Alignment.TopCenter)
            )
        }
    }
}
