package com.monese.rocket.model.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.monese.rocket.model.models.Core
import com.monese.rocket.model.models.Links
import com.monese.rocket.model.models.Payload
import java.util.*


class Converters {


    @TypeConverter
    fun fromMissionIds(list: List<String>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCore(list: List<Core>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringCore(value: String?): List<Core>? {
        val listType = object : TypeToken<List<Core>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromPayloads(list: List<Payload>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringPayloads(value: String?): List<Payload>? {
        val listType = object : TypeToken<List<Payload>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    /*@TypeConverter
    fun fromLink(list: Links?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringLink(value: String?): Links? {
        val listType = object : TypeToken<Links>() {

        }.type
        return Gson().fromJson(value, listType)
    }*/

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time ?: Date().time
    }
}