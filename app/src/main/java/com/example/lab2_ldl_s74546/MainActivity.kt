package com.example.lab2_ldl_s74546

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtName = findViewById<EditText>(R.id.editName)
        val edtAge = findViewById<EditText>(R.id.editAge)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnGoogle = findViewById<Button>(R.id.btnWeb)
        val btnShared = findViewById<Button>(R.id.btnSharing)

        btnProfile.setOnClickListener {
            val name = edtName.text.toString().trim()
            val age = edtAge.text.toString().trim()

            if (name.isEmpty() && age.isEmpty()) {
                Toast.makeText(this, R.string.error_empty, Toast.LENGTH_LONG).show()

            } else {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                intent.putExtra("EXTRA_AGE", age)
                startActivity(intent)


            }

        }
        btnGoogle.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_MAIN, Uri.parse("https//kirakira.sekutu.org"))
            startActivity(webIntent)
        }

        btnShared.setOnClickListener {
            val textString = "Nama: ${(edtName.text)}, Age: ${(edtAge.text)}"
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, textString)
            }
                startActivity(Intent.createChooser(shareIntent, "Shared Through..."))
            }
        }
    }

