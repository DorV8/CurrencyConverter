package com.example.currencyconverter.data.dataSource.model

import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo

class AccountModel {
    private var CurrencyList = listOf<AccountDbo>()

    private var AmountInput = 1.0

    fun setCurrencyList(list: List<AccountDbo>) {
        CurrencyList = list
    }

    fun getAmountInput(): Double {
        return AmountInput
    }

    fun setAmountInput(value: Double) {
        AmountInput = value
    }

    fun getCurrencyList(): List<AccountDbo> {
        return CurrencyList
    }

    fun getAmountByTag(name: String): Double {
        return CurrencyList.find { x -> x.code == name }?.amount ?: 100.0
    }

    var codePicked: String = "USD"

    fun getPicked(): String {
        return codePicked
    }

    fun setPicked(name: String) {
        codePicked = name
    }
}