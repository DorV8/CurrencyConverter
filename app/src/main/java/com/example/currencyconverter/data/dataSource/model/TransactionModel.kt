package com.example.currencyconverter.data.dataSource.model

import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo

class TransactionModel {
    var transactionList = listOf<TransactionDbo>()

    fun setList(list: List<TransactionDbo>) {
        transactionList = list
    }

    fun getList(): List<TransactionDbo> {
        return transactionList
    }
}