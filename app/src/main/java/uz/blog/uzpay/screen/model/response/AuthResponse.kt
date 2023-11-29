package uz.blog.uzpay.screen.model.response

data class AuthResponse(
    val id: Int,
    val token: String,
    val phone: String,
    val status: String,
)
