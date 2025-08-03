package com.example.currencyconverter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.data.dataSource.viewmodel.MainViewModel
import com.example.currencyconverter.ui.view.CurrencyScreen
import com.example.currencyconverter.ui.view.TradeScreen
import com.example.currencyconverter.ui.view.TransactionsScreen

@Composable
fun NavigationApp(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "CurrencyScreen"
    ) {
        composable("CurrencyScreen") { CurrencyScreen(viewModel, navController) }

        composable("TradeScreen") { TradeScreen(viewModel, navController) }

        composable("TransactionScreen") { TransactionsScreen(viewModel, navController) }
    }
}