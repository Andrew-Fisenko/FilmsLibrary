package com.example.filmslibrary

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.filmslibrary.ui.main.MainFragment
//import com.example.filmslibrary.ui.details.DetailsFragment
import com.example.filmslibrary.ui.receiver.MainBroadcastReceiver
import com.example.filmslibrary.ui.service.ServiceFragment

class MainActivity : AppCompatActivity() {
    private val receiver = MainBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        registerReceiver(receiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_options -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .add(R.id.container, ServiceFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}