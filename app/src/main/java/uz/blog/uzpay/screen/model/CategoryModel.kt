package uz.blog.uzpay.screen.model

import java.io.Serializable

data class CategoryModel(
    val id: Int,
    val title: String,
    val image: String
): Serializable
