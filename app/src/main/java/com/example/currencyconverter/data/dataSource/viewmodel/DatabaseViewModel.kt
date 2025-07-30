package com.example.currencyconverter.data.dataSource.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.dataSource.model.DatabaseModel
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import kotlinx.coroutines.launch

class DatabaseViewModel(context: Context): ViewModel() {

    private var model = DatabaseModel(context)

    var accountList = listOf<AccountDbo>()

    fun getAllAccount() {
        viewModelScope.launch {
            try {
                val account = model.getAllAccount()
                accountList = account
            }
            catch (e: Exception) {
                Log.e("ViewModel_Error", "Не удалось считать данные аккаунта из БД: ${e.message}")
            }
        }
    }
}