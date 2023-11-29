package uz.blog.uzpay.screen.model.request

data class RegisterRequest(
    val fullname: String,
    val phone: String,
    val password: String,
)
