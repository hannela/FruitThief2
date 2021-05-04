package edu.mills.cs115.fruitthief.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Data table of fruit and their seasons. Prepopulated when the user first opens the app.
 *
 */
@Entity(tableName = "fruit_info_table")
data class Fruit (
    @PrimaryKey(autoGenerate = true)
    var fruitId: Int = 0,

    @ColumnInfo(name = "fruit_name")
    var fruitName: String,

    /**
     * Representation of fruit's season.
     * A-L = Jan-Dec, eg "ABC" = Jan-March
     */
    @ColumnInfo(name = "fruit_season")
    var fruitSeason: String
)