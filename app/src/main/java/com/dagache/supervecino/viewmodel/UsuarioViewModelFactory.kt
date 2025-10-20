package com.dagache.supervecino.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dagache.supervecino.data.repository.UsuarioRepository

class UsuarioViewModelFactory (private val repo: UsuarioRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return UsuarioViewModel(repo) as T
    }else {
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    }
}

