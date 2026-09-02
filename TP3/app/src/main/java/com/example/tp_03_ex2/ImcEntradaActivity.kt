package com.example.tp_03_ex2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class ImcEntradaActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PESO = "extra_peso"
        const val EXTRA_ALTURA = "extra_altura"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_entrada)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etPeso = findViewById<TextInputEditText>(R.id.etPeso)
        val etAltura = findViewById<TextInputEditText>(R.id.etAltura)
        val btnCalcular = findViewById<MaterialButton>(R.id.btnCalcular)
        val btnVoltar = findViewById<MaterialButton>(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }

        btnCalcular.setOnClickListener {
            val peso = etPeso.text?.toString()?.trim()?.toDoubleOrNull()
            val altura = etAltura.text?.toString()?.trim()?.toDoubleOrNull()

            if (peso == null || peso <= 0 || altura == null || altura <= 0) {
                Toast.makeText(this, R.string.error_campos_imc_invalidos, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (altura > 3.0) {
                Toast.makeText(this, R.string.error_altura_em_metros, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ImcResultadoActivity::class.java).apply {
                putExtra(EXTRA_PESO, peso)
                putExtra(EXTRA_ALTURA, altura)
            }
            startActivity(intent)
        }
    }
}
