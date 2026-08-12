package com.example.varliktakip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.varliktakip.databinding.ActivityMainBinding
import com.example.varliktakip.ui.AssetsFragment
import com.example.varliktakip.ui.DashboardFragment
import com.example.varliktakip.ui.DebtsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dashboardFragment = DashboardFragment()
        val assetsFragment = AssetsFragment()
        val debtsFragment = DebtsFragment()
        val billsFragment = com.example.varliktakip.ui.BillsFragment()

        setCurrentFragment(dashboardFragment)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_dashboard -> setCurrentFragment(dashboardFragment)
                R.id.navigation_assets -> setCurrentFragment(assetsFragment)
                R.id.navigation_debts -> setCurrentFragment(debtsFragment)
                R.id.navigation_bills -> setCurrentFragment(billsFragment)
            }
            true
        }
        
        setupBillReminders()
        createNotificationChannel()
        checkAndRequestNotificationPermission()
    }
    
    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val name = "Bill Reminders"
            val descriptionText = "Reminders for upcoming bills"
            val importance = android.app.NotificationManager.IMPORTANCE_HIGH
            val channel = android.app.NotificationChannel("bill_reminders", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: android.app.NotificationManager =
                getSystemService(android.content.Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    private fun checkAndRequestNotificationPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (androidx.core.content.ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != android.content.pm.PackageManager.PERMISSION_GRANTED
            ) {
                androidx.core.app.ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }
    }
    
    private fun setupBillReminders() {
        val periodicWorkRequest = androidx.work.PeriodicWorkRequestBuilder<com.example.varliktakip.worker.BillReminderWorker>(
            24, java.util.concurrent.TimeUnit.HOURS
        ).build()

        androidx.work.WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "BillReminderWork",
            androidx.work.ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
}
