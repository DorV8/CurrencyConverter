package com.example.currencyconverter.ui.view


import com.example.currencyconverter.R
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.example.currencyconverter.data.dataSource.viewmodel.MainViewModel
import com.example.currencyconverter.domain.entity.Currency

@Composable
fun CurrencyScreen(viewModel: MainViewModel, navController: NavController) {
    val enumNames: MutableList<String> = Currency.entries.map { it.name }.toMutableList()
    enumNames.remove(viewModel.accountVM.getPicked())

    Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
        CurrencyElement(
            viewModel.accountVM.getPicked(),
            viewModel.accountVM.getFullName(viewModel.accountVM.getPicked()),
            viewModel.accountVM.getAmountByTag(viewModel.accountVM.getPicked()),
            rateCurrency = 0.0,
            viewModel = viewModel,
            isPicked = true,
            navController = navController
        )
        val rates by viewModel.ratesFlow.collectAsState()

        if (viewModel.accountVM.getAmountInput() != 1.0) {
            rates.filter { x -> x.value <= viewModel.accountVM.getAmountByTag(viewModel.accountVM.getPicked()) }.forEach {rate ->
                CurrencyElement(
                    codeCurrency = rate.currency,
                    nameCurrency = viewModel.accountVM.getFullName(rate.currency),
                    accountCurrency = viewModel.accountVM.getAmountByTag(rate.currency),
                    rateCurrency = rate.value,
                    viewModel = viewModel,
                    isPicked = false,
                    navController = navController
                )
                Log.e("DEBUG_RATE", "name: ${rate.currency}, amount: ${viewModel.accountVM.getAmountByTag(rate.currency)}, rate: ${rate.value}")
            }
        }
        else
        {
            rates.forEach {rate ->
                CurrencyElement(
                    codeCurrency = rate.currency,
                    nameCurrency = viewModel.accountVM.getFullName(rate.currency),
                    accountCurrency = viewModel.accountVM.getAmountByTag(rate.currency),
                    rateCurrency = rate.value,
                    viewModel = viewModel,
                    isPicked = false,
                    navController = navController
                )
                //Log.e("DEBUG_RATE", "name: ${rate.currency}, amount: ${viewModel.accountVM.getAmountByTag(rate.currency)}")
            }
        }
    }
}

@Composable
fun CurrencyElement(codeCurrency: String, nameCurrency: String, accountCurrency: Double = 100.0, rateCurrency: Double, viewModel: MainViewModel, isPicked: Boolean, navController: NavController) {
    Row (
        modifier = Modifier.clickable {
            if (viewModel.accountVM.getAmountInput() != 1.0) {
                viewModel.accountVM.setTradePick(codeCurrency)
                navController.navigate("TradeScreen")
            }
            else {
                viewModel.accountVM.setPicked(codeCurrency)
            }
        }
    ) {
        SvgImage(viewModel.accountVM.getImageUrl(codeCurrency))
        Column {
            Text(
                text = codeCurrency
            )
            Text(
                text = nameCurrency
            )
            if (accountCurrency > 0.0) {
                Text(
                    text = "Balance: $accountCurrency"
                )
            }
        }

        Spacer(modifier = Modifier.fillMaxSize(0.5f))

        if (isPicked) {
            TextField(
                modifier = Modifier.align(Alignment.Top).fillMaxSize(),
                value = viewModel.accountVM.getAmountInput().toString(),
                onValueChange = { newValue ->
                    val filteredValue = newValue//.filter { it.isDigit() || it == '.' }
                    viewModel.accountVM.setAmountInput(filteredValue.toDoubleOrNull() ?: 0.0)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("") }
            )
        }
        else {
            Text(
                text = String.format("%.2f",rateCurrency)
            )
        }
    }
}

@Composable
fun SvgImage(url: String, contentDescription: String? = null) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .logger(DebugLogger())
        .build()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.error),
        imageLoader = imageLoader,
        modifier = Modifier.fillMaxSize(0.2f),

    )
}