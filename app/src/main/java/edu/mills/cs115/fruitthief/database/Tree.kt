package edu.mills.cs115.fruitthief.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data table of trees.
 *
 * @param fruit corresponds to the key of the fruit type in fruit_info_table
 * @param lat latitude of Tree
 * @param long longitude of Tree
 */
@Entity(tableName = "tree_info_table")
data class Tree(
    @PrimaryKey(autoGenerate = true)
    var treeId: Int = 0,

    //points to fruit type in fruit_info_table
    @ColumnInfo(name = "fruit_type")
    var fruit: Int,

    @ColumnInfo(name = "tree_latitude")
    var lat: Double,

    @ColumnInfo(name = "tree_longitude")
    var lng: Double
)