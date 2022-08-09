package com.ananta.is4_10519152.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactEntity::class], version = 1)
abstract class DaoDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: DaoDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): DaoDatabase {
            if (INSTANCE == null) {
                synchronized(DaoDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DaoDatabase::class.java, "contact_database"
                    )
                        .build()
                }
            }
            return INSTANCE as DaoDatabase
        }
    }
}