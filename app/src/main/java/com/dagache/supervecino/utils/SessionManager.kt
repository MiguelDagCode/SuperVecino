package com.dagache.supervecino.utils

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun guardarSesionUsuario(id: Int, corrreo: String){
        prefs.edit().putInt("idUsuario",id).putString("emailUsuario", corrreo).apply()
    }

    fun limpiarSesion(){
        prefs.edit().clear().apply()
    }

    fun getUsuarioId(): Int{
    return   prefs.getInt("idUsuario",-1)
    }

    fun getCorreo():String?{
        return prefs.getString("emailUsuario",null)
    }

    fun confirmoLogeo(): Boolean{
        if (getUsuarioId() != -1 && getCorreo() != null){
            return true
        }else{
            return false
        }
    }

}