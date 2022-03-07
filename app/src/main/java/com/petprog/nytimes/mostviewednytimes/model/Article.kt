package com.petprog.nytimes.mostviewednytimes.model

import com.petprog.nytimes.mostviewednytimes.model.Article.Companion.TABLE_NAME

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class for Database entity and Serialization.
 */
@Entity(tableName = TABLE_NAME)
data class Article(

    @PrimaryKey
    val id: Long,
    val imageUrl: String?,
    val title: String?,
    val byline: String?,
    val abstractX: String?,
    val publishedDate: String?,
    val url: String?,
    val coverImageUrl: String?

) {
    companion object {
        const val TABLE_NAME = "news_article"
    }
}