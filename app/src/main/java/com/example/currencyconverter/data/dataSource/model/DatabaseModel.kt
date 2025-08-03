package com.example.currencyconverter.data.dataSource.model

import android.content.Context
import android.util.Log
import com.example.currencyconverter.data.dataSource.room.ConverterDatabase
import com.example.currencyconverter.data.dataSource.room.account.dbo.AccountDbo
import com.example.currencyconverter.data.dataSource.room.transaction.dbo.TransactionDbo
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

    suspend fun getAllTransactions(): List<TransactionDbo> = withContext(Dispatchers.IO) {
        return@withContext try {
            transactionDao.getAll()
        }
        catch (e: Exception) {
            Log.e("DB_Error", "Ошибка чтения информации о транзакциях из БД: ${e.message}")
            emptyList<TransactionDbo>()
        }
    }

    suspend fun insertNewAccountDetails(vararg account: AccountDbo) = withContext(Dispatchers.IO) {
        try {
            accountDao.insertAll(*account)
        }
        catch (e: Exception) {
            Log.e("DB_Error", "Произошла ошибка при изменении данных аккаунта: ${e.message}")
        }
    }

    suspend fun insertNewTransaction(vararg transaction: TransactionDbo) = withContext(Dispatchers.IO) {
        try {
            transactionDao.insertAll(*transaction)
        }
        catch (e: Exception) {
            Log.e("DB_Error", "Произошла ошибка при внесении новой транзакции: ${e.message}")
        }
    }
}