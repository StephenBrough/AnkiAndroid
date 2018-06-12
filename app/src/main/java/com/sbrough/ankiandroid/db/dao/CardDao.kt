package com.sbrough.ankiandroid.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.sbrough.ankiandroid.db.entities.Card

@Dao
interface CardDao {

    @Query("SELECT * FROM cards")
    fun getAllCards(): List<Card>

    @Query("SELECT * from cards WHERE did = :deckId")
    fun getDeckOfCards(deckId: Long): List<Card>
}