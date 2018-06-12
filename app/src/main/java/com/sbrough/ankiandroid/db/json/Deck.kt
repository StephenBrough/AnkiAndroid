package com.sbrough.ankiandroid.db.json

import com.squareup.moshi.JsonClass

/**
 * Class representing a Deck object contained inside the JSON of the
 * Collection table, deck column
 */
@Suppress("ArrayInDataClass")
@JsonClass(generateAdapter = true)
data class Deck(
        val mid: String?,
        val name: String,
        val extendRev: Int,
        val usn: Int,
        val collapsed: Boolean,
        val browserCollapsed: Boolean?,
        val newToday: Array<Int>,
        val timeToday: Array<Int>,
        val dyn: Int,
        val extendNew: Int,
        val conf: Int,
        val revToday: Array<Int>,
        val lrnToday: Array<Int>,
        val id: Long,
        val mod: Long,
        val desc: String
)