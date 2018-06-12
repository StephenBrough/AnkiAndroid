package com.sbrough.ankiandroid.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/**
 * Cards are what you review
 *
 * There can be multiple cards per note, determined by the template
 */
@Entity(tableName = "cards",
        indices = [
            // TODO: Test these to see if they speed up queries
            (Index(name = "ix_cards_nid", value = ["nid"], unique = false)),
            (Index(name = "ix_cards_sched", value = ["did", "queue", "due"])),
            (Index(name = "ix_cards_usn", value = ["usn"]))
        ])
data class Card(

        @PrimaryKey
        // Milliseconds representing when the card was created
        var id: Long?,

        // notes.id
        @ColumnInfo(name = "nid")
        val notesId: Long,

        // Deck id - available in the [Collection] table
        @ColumnInfo(name = "did")
        val deckId: Long,

        // Identifies which of the card templates it corresponds to; valid values are 0..num templates - 1
        @ColumnInfo(name = "ord")
        val ordinal: Int,

        // Timestamp in seconds for the last modified date
        @ColumnInfo(name = "mod")
        val modifiedAt: Long,

        // Used to figure out diffs when syncing:
        //      value of -1 indicates changes that need to be pushed to the server
        //      usn < server indicates changes that need to be pulled from the server
        @ColumnInfo(name = "usn")
        val updateSequenceNum: Int,

        // 0 = new, 1 = learning, 2 = due, 3 = filtered
        val type: Int,

        // -3 = sched buried, -2 = user buried, -1 = suspended, 0 = new, 1 = learning, 2 = due (as for type)
        //  3 = in learning, next review in at least a day after the previous review
        val queue: Int,

        // Due is used differently for different card types:
        //      - New: Note id or random int
        //      - Due: Integer day, relative to the collection's creation time
        //      - Learning: integer timestamp
        val due: Int,

        // Interval (used in the SRS algorithm).
        //      Negative = seconds
        //      Positive = days
        @ColumnInfo(name = "ivl")
        val interval: Int,

        // Used in SRS algorithm
        val factor: Int,

        // The number of times a card has been reviewed
        val reps: Int,

        // Tracks the state change from when a user answers a card correctly to incorrectly
        val lapses: Int,

        // Number of reps left until the user graduates from the card
        val left: Int,

        // Only used when the card is currently in a filtered deck
        @ColumnInfo(name = "odue")
        val originalDue: Int,

        // Only used when the card is currently in a filtered deck
        @ColumnInfo(name = "odid")
        val originalDeckId: Long,

        // Currently unused
        val flags: Int,

        // Currently unused
        val data: String
)