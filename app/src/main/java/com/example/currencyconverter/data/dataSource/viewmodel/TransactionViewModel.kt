package com.example.currencyconverter.data.dataSource.viewmodel

import androidx.lifecycle.ViewModel
import com.example.currencyconverter.data.dataSource.model.TransactionModel
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo

class TransactionViewModel(): ViewModel() {
    val model = TransactionModel()

    fun setList(list: List<TransactionDbo>) {
        model.setList(list)
    }

    fun getList(): List<TransactionDbo> {
        return model.getList()
    }
}