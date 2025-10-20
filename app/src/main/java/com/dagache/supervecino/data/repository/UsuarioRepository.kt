package com.dagache.supervecino.data.repository

import com.dagache.supervecino.data.room.dao.UsuarioDao
import com.dagache.supervecino.data.room.entity.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao){

    suspend fun registrarUsuario(usuReg : Usuario):Long{
        return  usuarioDao.insertar(usuReg)
    }

    suspend fun obtenerPorCorreo(correo : String) : Usuario?{
      return  usuarioDao.obtenerUserByEmail(correo)
    }

    suspend fun login(correo: String, contra: String) : Usuario?{
        return  usuarioDao.login(correo, contra)
    }
}