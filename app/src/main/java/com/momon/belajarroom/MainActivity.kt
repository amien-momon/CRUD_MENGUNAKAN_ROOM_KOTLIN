package com.momon.belajarroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment2))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment2)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}