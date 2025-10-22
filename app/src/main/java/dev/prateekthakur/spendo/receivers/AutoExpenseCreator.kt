package dev.prateekthakur.spendo.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.domain.repository.ExpenseRepository
import dev.prateekthakur.spendo.utils.extractExpenseAmount
import dev.prateekthakur.spendo.utils.extractExpenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

class AutoExpenseCreator : BroadcastReceiver(), KoinComponent {

    private val expenseRepository: ExpenseRepository by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION){
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            messages.forEach { message ->
                Timber.d(message.messageBody)
                val amount = extractExpenseAmount(message.messageBody)
                val type = extractExpenseType(message.messageBody)
                amount?.let {
                    createExpense(Expense(it, type = type))
                }
            }
        }
    }

    private fun createExpense(expense: Expense){
        CoroutineScope(Dispatchers.IO).launch {
            expenseRepository.add(expense)
        }
    }
}

