package com.dagache.supervecino.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dagache.supervecino.data.room.dao.UsuarioDao
import com.dagache.supervecino.data.room.entity.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract  class  AppDatabase : RoomDatabase(){
    abstract fun usuarioDAO(): UsuarioDao

    companion object{
        @Volatile private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_ventas"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
               return instance
            }
        }

    }
}