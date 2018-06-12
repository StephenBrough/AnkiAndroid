package com.sbrough.ankiandroid.db.type_converters

import androidx.room.TypeConverter

class IntListConverters {
    @TypeConverter
    fun listToString(list: List<Int>): String = list.joinToString()

    @TypeConverter
    fun stringToList(stringedList: String): List<Int>  = stringedList
                .substring(1, stringedList.length - 1)
                .split(", ")
                .map { it.toInt() }

}