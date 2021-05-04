package edu.mills.cs115.fruitthief

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import edu.mills.cs115.fruitthief.database.FruitTreeDAO
import edu.mills.cs115.fruitthief.database.FruitTreeDatabase
import edu.mills.cs115.fruitthief.database.PopulateFruitTable
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FruitTreeDatabaseTest {
    private lateinit var fruitTreeDao: FruitTreeDAO
    private lateinit var db: FruitTreeDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, FruitTreeDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        fruitTreeDao = db.fruitTreeDAO
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertFruit(){
        runBlocking{
            PopulateFruitTable(fruitTreeDao);
            assertEquals("Apple", fruitTreeDao.getFruitByName("Apple").fruitName)
            PopulateFruitTable(fruitTreeDao)
            assertEquals("KLABC", fruitTreeDao.getFruitByName("Pomelo").fruitSeason)
        }
    }
}