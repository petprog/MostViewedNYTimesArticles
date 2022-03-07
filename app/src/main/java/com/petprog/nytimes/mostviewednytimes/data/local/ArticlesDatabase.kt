package com.petprog.nytimes.mostviewednytimes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.petprog.nytimes.mostviewednytimes.model.Article


/**
 * Abstract NY  Article database.
 * It provides DAO [ArticlesDao] by using method [getArticlesDao]
 */
@Database(
    entities = [Article::class],
    version = DatabaseMigrations.DB_VERSION
)
abstract class ArticlesDatabase : RoomDatabase() {

    /**
     * @return [ArticleDao] NY Articles Data Access Object.
     */
    abstract fun getArticlesDao(): ArticlesDao

    companion object {
        const val DB_NAME = "ny_articles_database"

        @Volatile
        private var INSTANCE: ArticlesDatabase? = null

        fun getInstance(context: Context): ArticlesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticlesDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
