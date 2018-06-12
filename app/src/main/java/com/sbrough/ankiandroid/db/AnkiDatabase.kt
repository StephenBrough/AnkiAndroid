package com.sbrough.ankiandroid.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sbrough.ankiandroid.AnkiApp
import com.sbrough.ankiandroid.db.dao.CardDao
import com.sbrough.ankiandroid.db.dao.CollectionDao
import com.sbrough.ankiandroid.db.dao.NoteDao
import com.sbrough.ankiandroid.db.entities.Card
import com.sbrough.ankiandroid.db.entities.Collection
import com.sbrough.ankiandroid.db.entities.Deck
import com.sbrough.ankiandroid.db.entities.Note

@Database(version = 2,
        entities = [
            Card::class,
            Collection::class,
            Deck::class,
            Note::class])
abstract class AnkiDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao
    abstract fun collectionDao(): CollectionDao
    abstract fun noteDao(): NoteDao

    companion object {
        const val DB_NAME = "anki-db.db"
        private var instance: AnkiDatabase? = null

        fun get(): AnkiDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(AnkiApp.appContext, AnkiDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .addMigrations(INITIAL_MIGRATION_1_2)
                        .build()
            }

            return instance!!
        }

        // Adds in the metadata Room wants
        private val INITIAL_MIGRATION_1_2 = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Do nothing; The sole purpose of this is to get Room to add it's signature so we can work with it via Room
            }
        }
    }
}