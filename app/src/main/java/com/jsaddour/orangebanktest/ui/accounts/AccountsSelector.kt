package com.jsaddour.orangebanktest.ui.accounts

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.jsaddour.orangebanktest.R

@Composable
fun AccountsSelector(viewState: LiveData<AccountsViewState>) {
    val context = LocalContext.current
    val state = viewState.observeAsState()

    state.value?.items?.let { accounts ->
        if (accounts.isNotEmpty()) {
            Column() {
                Selector(accounts.map { it.name }, state.value?.onAccountSelected ?: {})
                Divider(Modifier.padding(vertical = 16.dp), thickness = 1.dp, color = Color.Gray)
            }
        }
    }
    viewState.value?.error?.getContent()?.let { error ->
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

}


@Composable
fun Selector(items: List<String>, select: (name: String) -> Unit) {
    val text = remember { mutableStateOf("") } // initial value
    val isOpen = remember { mutableStateOf(false) } // initial value
    val openCloseOfDropDownList: (Boolean) -> Unit = {
        isOpen.value = it
    }
    val userSelectedString: (String) -> Unit = {
        text.value = it
        select(it)
    }
    Box {
        Column {
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = { Text(text = stringResource(R.string.accounts)) },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )
            DropDownList(
                requestToOpen = isOpen.value,
                list = items,
                openCloseOfDropDownList,
                userSelectedString
            )
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { isOpen.value = true }
                )
        )
    }
}

@Composable
fun DropDownList(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.fillMaxWidth(),
        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        list.forEach {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    request(false)
                    selectedString(it)
                }
            ) {
                Text(it, modifier = Modifier.wrapContentWidth())
            }
        }
    }
}