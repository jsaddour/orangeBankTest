package com.jsaddour.orangebanktest.ui.accounts

import com.jsaddour.orangebanktest.models.Account

import junit.framework.TestCase.assertEquals
import org.junit.Test


class AccountsViewStateTest {

    private val firstState = AccountsViewState.firstState({}, {})

    @Test
    fun `should return loading state`() {
        val loadingState = firstState.loading()
        assertEquals(
            loadingState,
            firstState.copy(loading = true, error = null)
        )
    }

    @Test
    fun `should return update state`() {
        val accounts = listOf(Account(1, "name", "url"))
        val updateState = firstState.update(accounts)
        assertEquals(
            updateState,
            firstState.copy(listOf(AccountsViewState.Item("name", "url")), false, null)
        )
    }

    @Test
    fun `should return error state`() {
        val errorState = firstState.error("an error")
        assertEquals(
            errorState.loading,
            false
        )
        assertEquals(
            errorState.loading,
            false
        )
        assertEquals(
            errorState.error?.getContent(),
            "an error"
        )
        //check if error event is consumed when get content second time
        assertEquals(
            errorState.error?.getContent(),
            null
        )
    }

}