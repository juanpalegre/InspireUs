package com.example.inspireus.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inspireus.data.model.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object{

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "quote_table"
            ).build()
            return INSTANCE!!
        }

        fun destroyDatabase(){
            INSTANCE = null
        }

    }
}