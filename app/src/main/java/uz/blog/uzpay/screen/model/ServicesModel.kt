package uz.blog.uzpay.screen.model

import java.io.Serializable

data class ServicesModel(
    val id: Int,
    val category_id: Int,
    val name: String,
    val image: String
): Serializable
