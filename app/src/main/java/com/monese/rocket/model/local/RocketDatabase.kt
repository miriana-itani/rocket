package com.monese.rocket.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.monese.rocket.model.models.*


@Database(
    entities = [Launch::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RocketDatabase : RoomDatabase() {
    abstract fun daoAccess(): DaoAccess


}
