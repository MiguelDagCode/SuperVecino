package com.dagache.supervecino.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dagache.supervecino.data.repository.UsuarioRepository
import com.dagache.supervecino.data.room.entity.Usuario
import com.dagache.supervecino.utils.PasswordUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class UsuarioViewModel(private val repo: UsuarioRepository) : ViewModel(){

    private val _registroResult = MutableLiveData<Result<Long>>()
    val registroResult: LiveData<Result<Long>> = _registroResult

    private val _loginResult = MutableLiveData<Result<Usuario>>()
    val loginResult: LiveData<Result<Usuario>> = _loginResult

    fun registrarUsuario(nombre: String, correo: String, contra: String){
       viewModelScope.launch(Dispatchers.IO){
           try {
               if (nombre.isBlank() || correo.isEmpty() || contra.isEmpty()){
                    _registroResult.postValue(Result.failure(java.lang.Exception("Porfavor verifique los campos")))
                    return@launch
               }

               val emailRegistrado = repo.obtenerPorCorreo(correo)
               if (emailRegistrado != null) {
                   _registroResult.postValue(Result.failure(java.lang.Exception("Porfavor verifique el correo(Ya esta registrado)")))
                   return@launch
               }else{
                   val hash = PasswordUtils.hash(contra)
                   val userReg = Usuario(nombre = nombre, correo = correo, contrasenia = hash, rol = "vendedor")
                   val idGenerado = repo.registrarUsuario(userReg)
                   _registroResult.postValue(Result.success(idGenerado))
               }
           }catch(excepcion: Exception){
                _registroResult.postValue(Result.failure(excepcion))
           }
       }
    }

    fun login(correo: String, contra: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val hash = PasswordUtils.hash(contra)
                val usuario = repo.login(correo, hash)
                if (usuario != null){
                    _loginResult.postValue(Result.success(usuario))
                }else{
                    _loginResult.postValue(Result.failure(Exception("Credenciales Invalidas")))
                }

            }catch (e : Exception){
                _loginResult.postValue(Result.failure(e))
            }
        }
    }

}