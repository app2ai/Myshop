package com.rtech.myshoppy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rtech.myshoppy.db.dao.UserDao
import com.rtech.myshoppy.db.entities.UserDetailsModel

@Database(entities = [UserDetailsModel::class], version = 1, exportSchema = false)
abstract class ShoppyRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: ShoppyRoomDatabase? = null

        fun getDbInstance(context: Context): ShoppyRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ShoppyRoomDatabase::class.java,
                    "shoppy_db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}