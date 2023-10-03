package com.example.cs388_project_3_flixster_p1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cs388_project_3_flixster_p1.R.id

/**
 * The MainActivity for the BestSellerList app.
 * Launches a [FlixsterFragment].
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, FlixsterFragment(), null).commit()
    }
}

