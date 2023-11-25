package com.example.contactmanager.room


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase(){

    abstract val userDAO: UserDao

    // Singleton Design Pattern
    companion object{
        @Volatile
        private var INSTANCE: UserDatabase ?= null
        fun getInstance(context: Context): UserDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    // Creating the database object
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "users_db"
                    )
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }

}