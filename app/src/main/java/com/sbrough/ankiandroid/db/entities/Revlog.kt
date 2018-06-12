package com.sbrough.ankiandroid.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/**
 * Keeps track of the user's review history; It has a row for every completed review
 */
@Entity(tableName = "revlog",
        indices = [
            (Index(name = "ix_revlog_cid", value = ["cid"])),
            (Index(name = "ix_revlog_usn", value = ["usn"]))
        ])
data class Revlog(
        // // Milliseconds representing when the review was done
        @PrimaryKey
        val id: Long,

        // cards.id
        @ColumnInfo(name = "cid")
        val cardId: Long,

        // Used to figure out diffs when syncing:
        //      value of -1 indicates changes that need to be pushed to the server
        //      usn < server indicates changes that need to be pulled from the server
        @ColumnInfo(name = "usn")
        val updateSequenceNum: Int,

        // Indicates which button was pressed when scoring recall
        // Review: 1 = wrong, 2 = hard, 3 = ok, 4 = easy
        // Learn/Relearn: 1 = wrong, 2 = ok, 3 = easy
        val ease: Int,

        @ColumnInfo(name = "ivl")
        val interval: Int,

        @ColumnInfo(name = "lastIvl")
        val lastInterval: Int,

        val factor: Int,

        // How many milliseconds your review took, up to 60000 (60s)
        @ColumnInfo(name = "time")
        val reviewTime: Long,

        // 0 = learn, 1 = review, 2 = relearn, 3 = cram
        val type: Int
)
