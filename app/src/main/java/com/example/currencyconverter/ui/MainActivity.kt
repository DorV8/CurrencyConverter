package com.example.currencyconverter.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import com.example.currencyconverter.data.dataSource.model.AccountModel
import com.example.currencyconverter.data.dataSource.model.DatabaseModel
import com.example.currencyconverter.data.dataSource.viewmodel.MainViewModel
import com.example.currencyconverter.navigation.NavigationApp

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var viewModel = MainViewModel(this.applicationContext)

            viewModel.setDataAccount()

            NavigationApp(viewModel)
        }
    }
}