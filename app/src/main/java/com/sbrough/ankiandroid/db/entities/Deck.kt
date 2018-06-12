package com.sbrough.ankiandroid.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sbrough.ankiandroid.db.type_converters.IntListConverters

/**
 * Class representing the Deck table inside our Room database
 * Basically a copy of [com.sbrough.ankiandroid.db.json.Deck]
 */
@Suppress("ArrayInDataClass")
@Entity(tableName = "decks")
@TypeConverters(IntListConverters::class)
data class Deck(
        @PrimaryKey
        val id: Long,
        val mid: String?,
        val name: String,
        val extendRev: Int,
        val usn: Int,
        val collapsed: Boolean,
        val browserCollapsed: Boolean?,
        val newToday: List<Int>,
        val timeToday: List<Int>,
        val dyn: Int,
        val extendNew: Int,
        val conf: Int,
        val revToday: List<Int>,
        val lrnToday: List<Int>,
        val mod: Long,
        val desc: String
)

const val DECK_TABLE_CREATION= "CREATE TABLE IF NOT EXISTS `decks` (" +
        "`id` INTEGER NOT NULL, " +
        "`mid` TEXT, " +
        "`name` TEXT NOT NULL, " +
        "`extendRev` INTEGER NOT NULL, " +
        "`usn` INTEGER NOT NULL, " +
        "`collapsed` INTEGER NOT NULL, " +
        "`browserCollapsed` INTEGER, " +
        "`newToday` TEXT NOT NULL, " +
        "`timeToday` TEXT NOT NULL, " +
        "`dyn` INTEGER NOT NULL, " +
        "`extendNew` INTEGER NOT NULL, " +
        "`conf` INTEGER NOT NULL, " +
        "`revToday` TEXT NOT NULL, " +
        "`lrnToday` TEXT NOT NULL, " +
        "`mod` INTEGER NOT NULL, " +
        "`desc` TEXT NOT NULL, " +
        "PRIMARY KEY(`id`))"