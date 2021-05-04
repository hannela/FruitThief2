package edu.mills.cs115.fruitthief.database

import kotlinx.coroutines.runBlocking
import timber.log.Timber

class PopulateTreeTable(fruitTreeDao: FruitTreeDAO) {

    // TODO: update for suspended DAO methods

    init {
        runBlocking {
            if (fruitTreeDao.getTreeList().isEmpty()) {
                Timber.i("Populating tree table")
                fruitTreeDao.insert(Tree(0, 0, 37.892860, -122.078400))
                fruitTreeDao.insert(Tree(0, 1, 37.870098, -122.070084))
                fruitTreeDao.insert(Tree(0, 0, 37.897701, -122.083549))
            }
        }
    }
}