package com.example.tp_03_ex2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import java.util.Locale

class ImcResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val peso = intent.getDoubleExtra(ImcEntradaActivity.EXTRA_PESO, 0.0)
        val altura = intent.getDoubleExtra(ImcEntradaActivity.EXTRA_ALTURA, 0.0)
        val imc = peso / (altura * altura)

        val (classificacaoRes, pillBackgroundRes, pillTextColorRes) = when {
            imc < 18.5 -> Triple(R.string.imc_abaixo_peso, R.drawable.bg_pill_abaixo, R.color.imc_abaixo_fg)
            imc < 25.0 -> Triple(R.string.imc_peso_ideal, R.drawable.bg_pill_ideal, R.color.imc_ideal_fg)
            imc < 30.0 -> Triple(R.string.imc_sobrepeso, R.drawable.bg_pill_sobrepeso, R.color.imc_sobrepeso_fg)
            else -> Triple(R.string.imc_obesidade, R.drawable.bg_pill_obesidade, R.color.imc_obesidade_fg)
        }

        findViewById<TextView>(R.id.tvImcValue).text = String.format(Locale.getDefault(), "%.2f", imc)
        findViewById<TextView>(R.id.tvClassificacao).apply {
            setText(classificacaoRes)
            setBackgroundResource(pillBackgroundRes)
            setTextColor(ContextCompat.getColor(this@ImcResultadoActivity, pillTextColorRes))
        }

        findViewById<MaterialButton>(R.id.btnVoltar).setOnClickListener {
            finish()
        }
    }
}
