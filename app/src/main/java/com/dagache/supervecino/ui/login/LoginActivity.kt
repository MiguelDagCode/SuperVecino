package com.dagache.supervecino.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dagache.supervecino.R
import com.dagache.supervecino.databinding.ActivityLoginBinding
import com.dagache.supervecino.ui.adapters.LoginFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorFragmento, com.dagache.supervecino.ui.adapters.LoginFragment())
            .commit()

    }
    }
