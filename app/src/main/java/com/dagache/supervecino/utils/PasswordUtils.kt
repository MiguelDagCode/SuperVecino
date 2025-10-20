package com.dagache.supervecino.utils

import java.security.MessageDigest

object PasswordUtils{

    fun hash(contrasena :String):String{
        val md = MessageDigest.getInstance("SHA-256")
        val bytes = md.digest(contrasena.toByteArray(Charsets.UTF_8))
        return  bytes.joinToString(""){
            "%02x".format(it)
        }
    }
}