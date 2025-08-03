package com.example.currencyconverter.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
import com.example.currencyconverter.data.dataSource.viewmodel.MainViewModel
import java.time.LocalDateTime

@Composable
fun TradeScreen(viewModel: MainViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val rates = viewModel.ratesFlow.collectAsState().value
        val rate = rates.find { x -> x.currency == viewModel.accountVM.getTradePick() }?.value

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = viewModel.accountVM.getFullName(viewModel.accountVM.getPicked()) +
                        " to " +
                        viewModel.accountVM.getFullName(viewModel.accountVM.getTradePick())
            )

            Text(
                text = viewModel.accountVM.getSymbol(viewModel.accountVM.getPicked()) +
                        "1 = " +
                        viewModel.accountVM.getSymbol(viewModel.accountVM.getTradePick()) +
                        String.format("%.3f", rate)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CurrencyCard(viewModel, true)
            CurrencyCard(viewModel, false, rate?.toDouble() ?: 1.0)
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = {
                val newTransaction = TransactionDbo(
                    id = 0,
                    from = viewModel.accountVM.getTradePick(),
                    to = viewModel.accountVM.getPicked(),
                    fromAmount = rate?.toDouble() ?: 0.0,
                    toAmount = viewModel.accountVM.getAmountInput(),
                    dateTime = LocalDateTime.now()
                )
                viewModel.databaseVM.insertTransactions(newTransaction)

                val acc1 = AccountDbo(
                    code = viewModel.accountVM.getPicked(),
                    amount = viewModel.accountVM.getAmountByTag(viewModel.accountVM.getPicked()) +
                            viewModel.accountVM.getAmountInput()
                )
                val acc2 = AccountDbo(
                    code = viewModel.accountVM.getTradePick(),
                    amount = viewModel.accountVM.getAmountByTag(viewModel.accountVM.getTradePick()) -
                            (rate?.toDouble() ?: 0.0)
                )

                viewModel.databaseVM.insertNewAccountDetails(acc1, acc2)
                viewModel.setDataAccount()
                viewModel.setDataTransaction()
                navController.popBackStack()
            }
        ) {
            Text(
                text = "Buy " +
                        viewModel.accountVM.getFullName(viewModel.accountVM.getPicked()) +
                        " for " +
                        viewModel.accountVM.getFullName(viewModel.accountVM.getTradePick())
            )
        }
    }
}

@Composable
fun CurrencyCard(viewModel: MainViewModel, isPicked: Boolean, rate: Double = 1.0) {
    val picked = viewModel.accountVM.getPicked()
    val traded = viewModel.accountVM.getTradePick()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SvgImage(
                url = if (isPicked)
                    viewModel.accountVM.getImageUrl(picked)
                else
                    viewModel.accountVM.getImageUrl(traded)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = if (isPicked) picked else traded,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (isPicked)
                        viewModel.accountVM.getFullName(picked)
                    else
                        viewModel.accountVM.getFullName(traded)
                )
            }
        }

        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = if (isPicked)
                "+" + viewModel.accountVM.getSymbol(picked) +
                        String.format("%.2f", viewModel.accountVM.getAmountInput())
            else
                "-" + viewModel.accountVM.getSymbol(traded) +
                        String.format("%.2f", rate),
            fontWeight = FontWeight.Bold
        )
    }
}