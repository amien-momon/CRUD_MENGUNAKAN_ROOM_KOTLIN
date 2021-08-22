package com.momon.belajarroom.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.momon.belajarroom.model.user

@Database(entities = [user::class], version = 1, exportSchema = false)
abstract class userDB : RoomDatabase() {

    abstract fun userDao(): userDao
    companion object{
        @Volatile
        private var  INSTANCE : userDB? = null

        fun getDatabase(context: Context):userDB{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    userDB::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}