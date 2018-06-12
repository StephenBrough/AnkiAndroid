package com.sbrough.ankiandroid.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.sbrough.ankiandroid.db.entities.Collection

@Dao
interface CollectionDao {
    @Query("SELECT * FROM col")
    fun getCollection(): Collection
}