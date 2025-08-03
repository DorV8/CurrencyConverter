package com.example.currencyconverter.data.dataSource.viewmodel

import com.example.currencyconverter.data.dataSource.model.AccountModel
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo

class AccountViewModel {

    private var model = AccountModel()

    fun setCurrencyList(list: List<AccountDbo>) {
        model.setCurrencyList(list)
    }

    fun getCurrencyList(): List<AccountDbo> {
        return model.getCurrencyList()
    }

    fun getAmountInput(): Double {
        return model.getAmountInput()
    }

    fun setAmountInput(value: Double) {
        model.setAmountInput(value)
    }

    fun getAmountByTag(name: String): Double {
        return model.getAmountByTag(name)
    }

    fun getFullName(tag: String): String {
        return CurrencyName.get(tag).toString()
    }

    fun getImageUrl(tag: String): String {
        return CurrencyImageUrl.get(tag).toString()
    }

    fun getPicked(): String {
        return model.codePicked
    }

    fun setPicked(name: String) {
        model.setPicked(name)
    }

    var tradedPick: String = ""

    fun setTradePick(tag: String) {
        tradedPick = tag
    }

    fun getTradePick(): String {
        return tradedPick
    }

    fun getSymbol(tag: String): String {
        return currencySymbols[tag].toString()
    }

}

private val CurrencyName: Map<String, String> = mapOf(
    "USD" to "United States Dollar",
    "GBP" to "British Pound Sterling",
    "EUR" to "Euro",
    "AUD" to "Australian Dollar",
    "BGN" to "Bulgarian Lev",
    "BRL" to "Brazilian Real",
    "CAD" to "Canadian Dollar",
    "CHF" to "Swiss Franc",
    "CNY" to "Chinese Yuan",
    "CZK" to "Czech Koruna",
    "DKK" to "Danish Krone",
    "HKD" to "Hong Kong Dollar",
    "HRK" to "Croatian Kuna",
    "HUF" to "Hungarian Forint",
    "IDR" to "Indonesian Rupiah",
    "ILS" to "Israeli Shekel",
    "INR" to "Indian Rupee",
    "ISK" to "Icelandic Krona",
    "JPY" to "Japanese Yen",
    "KRW" to "South Korean Won",
    "MXN" to "Mexican Peso",
    "MYR" to "Malaysian Ringgit",
    "NOK" to "Norwegian Krone",
    "NZD" to "New Zealand Dollar",
    "PHP" to "Philippine Peso",
    "PLN" to "Polish Zloty",
    "RON" to "Romanian Leu",
    "RUB" to "Russian Ruble",
    "SEK" to "Swedish Krona",
    "SGD" to "Singapore Dollar",
    "THB" to "Thai Baht",
    "TRY" to "Turkish Lira",
    "ZAR" to "South African Rand",
)

private val CurrencyImageUrl: Map<String, String> = mapOf(
    "USD" to "https://flagcdn.com/us.svg",
    "GBP" to "https://flagcdn.com/gb.svg",
    "EUR" to "https://flagcdn.com/eu.svg",
    "AUD" to "https://flagcdn.com/au.svg",
    "BGN" to "https://flagcdn.com/bg.svg",
    "BRL" to "https://flagcdn.com/br.svg",
    "CAD" to "https://flagcdn.com/ca.svg",
    "CHF" to "https://flagcdn.com/ch.svg",
    "CNY" to "https://flagcdn.com/cn.svg",
    "CZK" to "https://flagcdn.com/cz.svg",
    "DKK" to "https://flagcdn.com/dk.svg",
    "HKD" to "https://flagcdn.com/hk.svg",
    "HRK" to "https://flagcdn.com/hr.svg",
    "HUF" to "https://flagcdn.com/hu.svg",
    "IDR" to "https://flagcdn.com/id.svg",
    "ILS" to "https://flagcdn.com/il.svg",
    "INR" to "https://flagcdn.com/in.svg",
    "ISK" to "https://flagcdn.com/is.svg",
    "JPY" to "https://flagcdn.com/jp.svg",
    "KRW" to "https://flagcdn.com/kr.svg",
    "MXN" to "https://flagcdn.com/mx.svg",
    "MYR" to "https://flagcdn.com/my.svg",
    "NOK" to "https://flagcdn.com/no.svg",
    "NZD" to "https://flagcdn.com/nz.svg",
    "PHP" to "https://flagcdn.com/ph.svg",
    "PLN" to "https://flagcdn.com/pl.svg",
    "RON" to "https://flagcdn.com/ro.svg",
    "RUB" to "https://flagcdn.com/ru.svg",
    "SEK" to "https://flagcdn.com/se.svg",
    "SGD" to "https://flagcdn.com/sg.svg",
    "THB" to "https://flagcdn.com/th.svg",
    "TRY" to "https://flagcdn.com/tr.svg",
    "ZAR" to "https://flagcdn.com/za.svg",
    )


private val currencySymbols = mapOf(
    "USD" to "$",
    "GBP" to "£",
    "EUR" to "€",
    "AUD" to "$",
    "BGN" to "лв",
    "BRL" to "R$",
    "CAD" to "$",
    "CHF" to "CHF",
    "CNY" to "¥",
    "CZK" to "Kč",
    "DKK" to "kr",
    "HKD" to "HK$",
    "HRK" to "kn",
    "HUF" to "Ft",
    "IDR" to "Rp",
    "ILS" to "₪",
    "INR" to "₹",
    "ISK" to "kr",
    "JPY" to "¥",
    "KRW" to "₩",
    "MXN" to "$",
    "MYR" to "RM",
    "NOK" to "kr",
    "NZD" to "$",
    "PHP" to "₱",
    "PLN" to "zł",
    "RON" to "lei",
    "RUB" to "₽",
    "SEK" to "kr",
    "SGD" to "S$",
    "THB" to "฿",
    "TRY" to "₺",
    "ZAR" to "R"
)