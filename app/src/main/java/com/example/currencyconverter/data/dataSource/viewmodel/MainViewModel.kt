package com.example.currencyconverter.data.dataSource.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.dataSource.remote.RemoteRatesServiceImpl
import com.example.currencyconverter.data.dataSource.remote.dto.RateDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(context: Context): ViewModel() {

    var accountVM = AccountViewModel()
    var databaseVM = DatabaseViewModel(context)
    var transactionVM = TransactionViewModel()

    fun setDataAccount() {
        databaseVM.getAllAccount()
        accountVM.setCurrencyList(databaseVM.accountList)
    }

    fun setDataTransaction() {
        databaseVM.getAllTransactions()
        transactionVM.setList(databaseVM.transactionList)
    }

    var remote = RemoteRatesServiceImpl()

    private val _ratesFlow = MutableStateFlow<List<RateDto>>(emptyList())
    val ratesFlow: StateFlow<List<RateDto>> = _ratesFlow.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                _ratesFlow.value = remote.getRates(accountVM.getPicked(), accountVM.getAmountInput()).filter { x -> x.currency != accountVM.getPicked() }
                delay(1000)
            }
        }
    }
}