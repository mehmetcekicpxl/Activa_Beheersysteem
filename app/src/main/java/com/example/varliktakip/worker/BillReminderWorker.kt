package com.example.varliktakip.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.varliktakip.data.AppDatabase
import java.util.concurrent.TimeUnit

class BillReminderWorker(
    appContext: Context, 
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val database = AppDatabase.getDatabase(applicationContext)
        val billDao = database.billDao()

        val currentTime = System.currentTimeMillis()
        val twoDaysInMillis = TimeUnit.DAYS.toMillis(2)
        val oneDayInMillis = TimeUnit.DAYS.toMillis(1)
        
        // Check for bills due from yesterday (to catch today's missed) up to next 48 hours
        val startTime = currentTime - oneDayInMillis
        val endTime = currentTime + twoDaysInMillis

        // Find bills due
        val upcomingBills = billDao.getUnpaidBillsDueInRange(startTime, endTime)

        if (upcomingBills.isNotEmpty()) {
            upcomingBills.forEach { bill ->
                sendNotification(bill.name, bill.amount, bill.currency)
            }
        }

        return Result.success()
    }

    private fun sendNotification(billName: String, amount: Double, currency: String) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "bill_reminders"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Bill Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Reminders for upcoming bills"
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Permission Check for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
             if (androidx.core.app.ActivityCompat.checkSelfPermission(
                    applicationContext,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != android.content.pm.PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Bill Reminder: $billName")
            .setContentText("Upcoming bill: $amount is due soon!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        
        notificationManager.notify(billName.hashCode(), notification)
    }
}
