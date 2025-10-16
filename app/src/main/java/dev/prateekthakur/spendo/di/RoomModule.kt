package dev.prateekthakur.spendo.di

import androidx.room.Room
import dev.prateekthakur.spendo.data.room.dao.ExpenseDao
import dev.prateekthakur.spendo.data.room.db.ExpenseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val RoomModule = module {
    single <ExpenseDatabase>{
        Room.databaseBuilder(
            androidContext(), ExpenseDatabase::class.java, "expenses.db"
        ).fallbackToDestructiveMigration(false).build()
    }

    single <ExpenseDao>{
        get <ExpenseDatabase>().expenseDao()
    }
}