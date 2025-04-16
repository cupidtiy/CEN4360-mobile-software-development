package com.example.connectfour

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE_OPTIONS = 1 // Request code for starting GameOptions activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the content view to your main layout

        // Handle the "Connect Four" button click
        findViewById<View>(R.id.button).setOnClickListener {
            // Start Board activity when Connect Four button is clicked
            val intent = Intent(this@MainActivity, Board::class.java)
            startActivity(intent)
        }

        // Handle the "Options" button click
        findViewById<View>(R.id.button_options).setOnClickListener {
            // Start GameOptions activity and listen for result
            val intent = Intent(this@MainActivity, GameOptions::class.java)
            startActivityForResult(intent, REQUEST_CODE_OPTIONS) // Request code 1 for options
        }
    }

    // Override onActivityResult to handle the result from GameOptions
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check if the result is from GameOptions and if it was successful
        if (requestCode == REQUEST_CODE_OPTIONS && resultCode == RESULT_OK && data != null) {
            // Get the selected mode from the Intent
            val selectedMode = data.getStringExtra("gameMode")

            // Display a Toast message with the selected mode
            Toast.makeText(this, "$selectedMode mode", Toast.LENGTH_SHORT).show()
        }
    }
}
