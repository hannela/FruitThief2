package edu.mills.cs115.fruitthief.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Fruit::class, Tree::class], version = 1, exportSchema = false)
abstract class FruitTreeDatabase : RoomDatabase() {
    abstract val fruitTreeDAO: FruitTreeDAO

    companion object {
        @Volatile
        private var INSTANCE: FruitTreeDatabase? = null

        /**
         *
         * @param context app Context
         * @return app's instance of the database
         */

        fun getInstance(context: Context): FruitTreeDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {

                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FruitTreeDatabase::class.java,
                        "fruit_tree_database"
                    )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // You can learn more about
                        // migration with Room in this blog post:
                        // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}