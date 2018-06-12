package com.sbrough.ankiandroid.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/**
 * Notes contain the raw information that is formatted into a number of cards,
 * according to the models
 */
@Entity(tableName = "notes",
        indices = [
            (Index(name = "ix_notes_csum", value = ["csum"])),
            (Index(name = "ix_notes_usn", value = ["usn"]))
        ])
data class Note(
        @PrimaryKey
        // Milliseconds representing when the note was created
        val id: Long?,

        // Globally unique id, almost certainly used for syncing
        @ColumnInfo(name = "guid")
        val globallyUID: String,

        @ColumnInfo(name = "mid")
        val modelId: Int,

        @ColumnInfo(name = "mod")
        val modifiedAt: Long,

        // Used to figure out diffs when syncing:
        //      value of -1 indicates changes that need to be pushed to the server
        //      usn < server indicates changes that need to be pulled from the server
        @ColumnInfo(name = "usn")
        val updateSequenceNum: Int,

        // Space-separated string ot tags;
        // includes a space at the beginning and end, for LIKE "% tag %" queries
        val tags: String,

        // The values of the fields in this note, separated by 0x1f (31) character
        @ColumnInfo(name = "flds")
        val fields: String,

        // Sort field; used for quick sorting and duplicate check
        @ColumnInfo(name = "sfld")
        val sortField: Int,

        // Field checksum used for duplicate checking
        // Integer representation of first 8 digits of sha1 hash of the first field
        @ColumnInfo(name = "csum")
        val fieldChecksum: Int,

        // Currently unused
        val flags: Int,

        // Currently unused
        val data: String
)