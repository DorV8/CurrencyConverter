package com.example.currencyconverter.data.dataSource.model

import android.content.Context
import android.util.Log
import com.example.currencyconverter.data.dataSource.room.ConverterDatabase
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseModel(context: Context) {

    private val db = ConverterDatabase.getInstance(context)

    private val accountDao = db.accountDao()
    private val transactionDao = db.transactionDao()

    suspend fun getAllAccount(): List<AccountDbo> = withContext(Dispatchers.IO) {
        return@withContext try {
            accountDao.getAll()
        }
        catch (e: Exception) {
            Log.e("DB_Error", "Ошибка чтения информации об аккаунте из БД: ${e.message}")
            emptyList<AccountDbo>()
        }
    }
}