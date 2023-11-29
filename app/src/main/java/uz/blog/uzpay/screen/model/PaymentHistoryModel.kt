package uz.blog.uzpay.screen.model

data class PaymentHistoryModel(
    val id: Int,
    val pay_id: Int,
    val user_id: Int,
    val card_id: Int,
    val account_number: String,
    val from_card: String,
    val to_service: String,
    val date: String,
    val summa: Double
)

