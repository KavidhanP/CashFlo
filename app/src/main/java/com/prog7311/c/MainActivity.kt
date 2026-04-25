package com.prog7311.c

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.prog7311.c.data.database.AppDatabase
import com.prog7311.c.viewmodel.AuthViewModel
import com.prog7311.c.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = ViewModelFactory(AppDatabase.getDatabase(this))
        authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        // Test register then login
        lifecycleScope.launch {

            // Register a test user
            authViewModel.register("testuser", "password123")

            // Try logging in
            authViewModel.login("testuser", "password123")
        }

        // Observe login result
        lifecycleScope.launch {
            authViewModel.loginResult.collect { result ->
                when (result) {
                    true -> Log.d("CashFlo", " LOGIN SUCCESS")
                    false -> Log.d("CashFlo", " LOGIN FAILED")
                    null -> {} // initial state, ignore
                }
            }
        }
    }
}