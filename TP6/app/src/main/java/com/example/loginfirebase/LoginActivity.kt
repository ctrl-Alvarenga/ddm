package com.example.loginfirebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val telaLogin = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { resultado -> aoFazerLogin(resultado) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (FirebaseAuth.getInstance().currentUser != null) {
            abrirApp()
        } else if (savedInstanceState == null) {
            abrirTelaLogin()
        }
    }

    private fun abrirTelaLogin() {
        // O login por e-mail do FirebaseUI já traz a opção de cadastrar usuários.
        val provedores = listOf(
            AuthUI.IdpConfig.EmailBuilder()
                .setDefaultEmail("rafael.omelczuk@hotmail.com")
                .build(),
        )

        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(provedores)
            .build()
        telaLogin.launch(intent)
    }

    private fun aoFazerLogin(resultado: FirebaseAuthUIAuthenticationResult) {
        if (resultado.resultCode == RESULT_OK) {
            abrirApp()
        } else {
            finish()
        }
    }

    private fun abrirApp() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
