package com.sbrough.ankiandroid.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity


/**
 *  Contains deleted cards, notes, and decks that need to be synced
 */
@Entity( tableName = "graves")
data class Grave(
        // Should be set to -1
        @ColumnInfo(name = "usn")
        val updateSequenceNum: Int,

        @ColumnInfo(name = "oid")
        val originalId: Long,

        // 0 = card, 1 = note, 2 = deck
        val type: Int
)