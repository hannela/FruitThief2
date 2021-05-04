package edu.mills.cs115.fruitthief.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * DAO Database containing two tables, fruit_info_table and tree_info_table.
 *
 * @see Tree for tree_info_table
 * @see Fruit for fruit_info_table
 */
@Dao
interface FruitTreeDAO {
    /**
     *
     * @param tree New tree to be added.
     *
     */
    @Insert
    suspend fun insert(tree: Tree)

    /**
     *
     * fruit_info_table is prepopulated, so insert(fruit) should only be used on app's first run.
     * @param fruit Fruit to be added.
     */
    @Insert
    suspend fun insert(fruit: Fruit)

    /**
     *
     * @param tree Tree with updated information.
     */
    @Update
    suspend fun update(tree: Tree)

    /**
     *
     * @param tree Tree to be deleted.
     */
    @Delete
    suspend fun deleteTree(tree: Tree)

    @Query("DELETE FROM tree_info_table")
    suspend fun clearTrees()

    /**
     *
     * Only for test purposes, should never actually be used.
     */
    @Query("DELETE FROM fruit_info_table")
    suspend fun clearFruitTable()

    /**
     *
     * @return Array of all Fruit
     */
    @Query("SELECT * FROM fruit_info_table")
    suspend fun getFruitList(): Array<Fruit>

    /**
     *
     * @return Array of all Trees
     */
    @Query("SELECT * FROM tree_info_table")
    suspend fun getTreeList(): List<Tree>

    /**
     *
     * @param fruitName String of fruit name
     * @return Fruit that matches given string
     */
    @Query("SELECT * FROM fruit_info_table WHERE fruit_name = :fruitName")
    suspend fun getFruitByName(fruitName: String): Fruit

    /**
     *
     * @param fruit String specifying fruit type
     * @return Array of Trees with fruit matching given type
     */
    @Query("SELECT tree.* FROM tree_info_table tree INNER JOIN fruit_info_table ON fruit_type = fruitId WHERE fruit_name = :fruit")
    suspend fun filterByFruit(fruit: String): List<Tree>

    /**
     *
     * @param month String of a single character representing the current month. A-L = Jan-Dec.
     * @return Array of all Trees whose fruit is currently in season
     */
    @Query("SELECT tree.* FROM tree_info_table tree INNER JOIN fruit_info_table ON fruit_type = fruitId WHERE fruit_season LIKE '%'+:month+'%'")
    suspend fun getInSeasonTrees(month: String): List<Tree>

    /**
     *
     * @return Array of all fruit names
     */
    @Query("SELECT fruit_name FROM fruit_info_table")
    suspend fun getFruitNamesList(): Array<String>
}