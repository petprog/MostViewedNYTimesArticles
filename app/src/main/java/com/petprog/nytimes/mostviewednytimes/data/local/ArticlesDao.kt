package  com.petprog.nytimes.mostviewednytimes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petprog.nytimes.mostviewednytimes.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO)
 */
@Dao
interface ArticlesDao {

    /**
     * Inserts [articles] into the [Article.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param articles Posts
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(articles: List<Article>)

    /**
     * Deletes all the articles from the [Article.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${Article.TABLE_NAME}")
    suspend fun deleteAllArticles()

    /**
     * Fetches the article from the [Article.TABLE_NAME] table whose id is [id].
     * @param id Unique ID of [Article]
     * @return [Flow] of [Article] from database table.
     */
    @Query("SELECT * FROM ${Article.TABLE_NAME} WHERE ID = :id")
    fun getArticleById(id: Long): Flow<Article>

    /**
     * Fetches all the articles from the [Post.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Article.TABLE_NAME}")
    fun getAllArticles(): Flow<List<Article>>
}
