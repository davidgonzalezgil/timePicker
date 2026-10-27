package com.example.b015timepicker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var textClock: TextView
    private lateinit var timePicker: TimePicker
    private lateinit var getTime: TextView
    private lateinit var editTextTime: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById(R.id.textView)
        textClock = findViewById(R.id.textClock)
        timePicker = findViewById(R.id.timePicker)
        getTime = findViewById(R.id.getTime)
        editTextTime = findViewById(R.id.editTextTime)
        button = findViewById(R.id.button)

        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            val horaFormateada = String.format("%02d:%02d", hourOfDay, minute)
            getTime.text = horaFormateada
        }

        button.setOnClickListener {
            val horaBuscada = editTextTime.text.toString()
            val partes = horaBuscada.split(":")

            if (partes.size == 2) {
                val hora = partes[0].toIntOrNull()
                val minuto = partes[1].toIntOrNull()

                if (hora != null && minuto != null && hora in 0..23 && minuto in 0..59) {
                    textClock.text = String.format("%02d:%02d", hora, minuto)
                } else {
                    Toast.makeText(this, "formato invalido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Usa el formato HH:mm", Toast.LENGTH_SHORT).show()
            }
        }

    }
}