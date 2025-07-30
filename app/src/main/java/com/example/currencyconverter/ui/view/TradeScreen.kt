package com.example.currencyconverter.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.currencyconverter.data.dataSource.viewmodel.MainViewModel

@Composable
fun TradeScreen(viewModel: MainViewModel, navController: NavController) {
    Column {
        Text(
            text = viewModel.accountVM.getFullName(viewModel.accountVM.getPicked()) +
                    " to " +
                    viewModel.accountVM.getFullName(viewModel.accountVM.getTradePick())
        )
        val rates = viewModel.ratesFlow.collectAsState().value
        val rate = rates.find{ x -> x.currency == viewModel.accountVM.getTradePick()}?.value
        Text(
            text = "1 = $rate"
        )

        CurrencyCard(viewModel, true)
        CurrencyCard(viewModel, false, rate?.toDouble() ?: 1.0)

        Button(
            onClick = {

            }
        ) {
            Text(
                text = "Buy " + viewModel.accountVM.getFullName(viewModel.accountVM.getPicked()) + " for " + viewModel.accountVM.getFullName(viewModel.accountVM.getTradePick())
            )
        }
    }
}

@Composable
fun CurrencyCard(viewModel: MainViewModel, isPicked: Boolean, rate: Double = 1.0) {
    val picked = viewModel.accountVM.getPicked()
    val traded = viewModel.accountVM.getTradePick()
    Row {
        SvgImage(
            url = if (isPicked)
                viewModel.accountVM.getImageUrl(picked)
            else
                viewModel.accountVM.getImageUrl(traded)
        )
        Column {
            Text(
                text = if (isPicked)
                    picked
                else
                    traded
            )
            Text(
                text = if (isPicked)
                    viewModel.accountVM.getFullName(picked)
                else
                    viewModel.accountVM.getFullName(traded)
            )
        }
        Spacer(modifier = Modifier.fillMaxSize(0.5f))

        Text(
            text = if (isPicked)
                "+" + viewModel.accountVM.getAmountInput()
            else
                "-" + viewModel.accountVM.getAmountInput() * rate
        )
    }
}