package uz.blog.uzpay.screen.model.request

data class PaymentRequest(
    val service_id: Int,
    val account_number: String,
    val summa: Double,
    val card_id: Int
)
