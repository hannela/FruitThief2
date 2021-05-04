package edu.mills.cs115.fruitthief.database

import kotlinx.coroutines.runBlocking
import timber.log.Timber

class PopulateFruitTable(fruitTreeDao: FruitTreeDAO) {

    init {
        runBlocking{
            if (fruitTreeDao.getFruitList().isEmpty()) {
                Timber.i("Populating table")
                fruitTreeDao.insert(Fruit(0, "Apple", "HIJK"))
                fruitTreeDao.insert(Fruit(0, "Apricot", "FG"))
                fruitTreeDao.insert(Fruit(0, "Asian Pear", "IJK"))
                fruitTreeDao.insert(Fruit(0, "Avocado", "FGH"))
                fruitTreeDao.insert(Fruit(0, "Blackberry", "GHI"))
                fruitTreeDao.insert(Fruit(0, "Fig", "IJFG"))
                fruitTreeDao.insert(Fruit(0, "Grapefruit", "LABCD"))
                fruitTreeDao.insert(Fruit(0, "Lemon", "ABCDEFGHIJKL"))
                fruitTreeDao.insert(Fruit(0, "Lime", "KLABCDEFG"))
                fruitTreeDao.insert(Fruit(0, "Loquat", "EFG"))
                fruitTreeDao.insert(Fruit(0, "Mandarin Orange", "KLAB"))
                fruitTreeDao.insert(Fruit(0, "Orange", "LABCD"))
                fruitTreeDao.insert(Fruit(0, "Peach", "GH"))
                fruitTreeDao.insert(Fruit(0, "Pear", "HI"))
                fruitTreeDao.insert(Fruit(0, "Persimmon", "J"))
                fruitTreeDao.insert(Fruit(0, "Pineapple Guava", "JK"))
                fruitTreeDao.insert(Fruit(0, "Plum", "FGHI"))
                fruitTreeDao.insert(Fruit(0, "Pluot", "FGHI"))
                fruitTreeDao.insert(Fruit(0, "Pomegranate", "JK"))
                fruitTreeDao.insert(Fruit(0, "Pomelo", "KLABC"))
                fruitTreeDao.insert(Fruit(0, "Tangerine", "KLAB"))
                fruitTreeDao.insert(Fruit(0, "Unknown", "ABCDEFGHIJKL"))
            }
        }
    }
}