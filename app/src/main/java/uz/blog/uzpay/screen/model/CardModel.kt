package uz.blog.uzpay.screen.model

data class CardModel(
    val id: Int,
    val app_user_id: Int,
    val name: String,
    val card_number: String,
    val summa: Double,
    val validity_period: String
)
