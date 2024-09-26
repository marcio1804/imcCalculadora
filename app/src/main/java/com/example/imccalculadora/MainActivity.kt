package com.example.imccalculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextHeight: EditText
    private lateinit var editTextWeight: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var buttonClear: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextHeight = findViewById(R.id.editTextHeight)
        editTextWeight = findViewById(R.id.editTextWeight)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        buttonClear = findViewById(R.id.buttonClear)
        textViewResult = findViewById(R.id.textViewResult)

        buttonCalculate.setOnClickListener { calcularIMC() }
        buttonClear.setOnClickListener { limparCampos() }
    }

    private fun calcularIMC() {
        val alturaString = editTextHeight.text.toString()
        val pesoString = editTextWeight.text.toString()

        if (alturaString.isNotEmpty() && pesoString.isNotEmpty()) {
            val altura = alturaString.toDoubleOrNull()
            val peso = pesoString.toDoubleOrNull()

            if (altura != null && peso != null) {
                val imc = peso / (altura * altura)
                val categoria = determinarCategoria(imc)
                textViewResult.text = String.format("IMC: %.2f\nCategoria: %s", imc, categoria)
            } else {
                textViewResult.text = "Por favor, insira valores válidos."
            }
        } else {
            textViewResult.text = "Por favor, insira altura e peso."
        }
    }

    private fun limparCampos() {
        editTextHeight.text.clear()
        editTextWeight.text.clear()
        textViewResult.text = ""
    }

    private fun determinarCategoria(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Peso normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }
}
