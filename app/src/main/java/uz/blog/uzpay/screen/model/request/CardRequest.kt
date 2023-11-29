package uz.blog.uzpay.screen.model.request

data class CardRequest(
    val name: String,
    val card_number: String,
    val validity_period: String,
)
