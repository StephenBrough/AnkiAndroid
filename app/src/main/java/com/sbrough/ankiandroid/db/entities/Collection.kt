package com.sbrough.ankiandroid.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONObject

/**
 * Contains a single row that holds various information about the Anki collection
 */
@Entity(tableName = "col")
data class Collection(
        // Arbitrary number since there is only one row
        @PrimaryKey
        val id: Long?,

        // Created timestamp
        @ColumnInfo(name = "crt")
        val createdAt: Long,

        // Last modified timestamp in milliseconds
        @ColumnInfo(name = "mod")
        val modifiedAt: Long,

        // Time when the "schema" was modified
        // If the server scm is different from the client scm a full-sync is required
        @ColumnInfo(name = "scm")
        val schemaModifiedAt: Long,

        @ColumnInfo(name = "ver")
        val version: Int,

        // Unused, set to 0
        @ColumnInfo(name = "dty")
        val dirty: Int,

        // Used to figure out diffs when syncing:
        //      value of -1 indicates changes that need to be pushed to the server
        //      usn < server indicates changes that need to be pulled from the server
        @ColumnInfo(name = "usn")
        val updateSequenceNum: Int,

        // Time we last synced
        @ColumnInfo(name = "ls")
        val lastSyncAt: Long,

        // Json object containing configuration options that are synced
        @ColumnInfo(name = "conf")
        val configuration: String,

        // Json array of json objects containing the models (aka Note types)
        val models: String,

        // Json array of json objects containing the decks
        val decks: String,

        // Json array of Json objects containing the deck options
        @ColumnInfo(name = "dconf")
        val deckConfiguration: String,

        // A cache of tags used in the collection
        // This list is displayed in the browser, potentially other places as well
        val tags: String
)

// Convenience method for returning the String Json data in the configuration column as a Json object
fun Collection.getConfiguration() = JSONObject(this.configuration)