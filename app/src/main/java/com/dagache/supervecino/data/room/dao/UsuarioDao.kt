package com.dagache.supervecino.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dagache.supervecino.data.room.entity.Usuario


@Dao
interface  UsuarioDao{

    @Insert
    suspend fun insertar(reg: Usuario): Long

    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    suspend fun obtenerUserByEmail(correo: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE correo = :correo AND contrasenia = :contrasenia LIMIT 1")
    suspend fun login(correo: String, contrasenia: String): Usuario?
}