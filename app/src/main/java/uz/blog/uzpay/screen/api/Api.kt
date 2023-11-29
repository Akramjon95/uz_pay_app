package uz.blog.uzpay.screen.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import uz.blog.uzpay.screen.model.CardModel
import uz.blog.uzpay.screen.model.CategoryModel
import uz.blog.uzpay.screen.model.PaymentHistoryModel
import uz.blog.uzpay.screen.model.ServicesModel
import uz.blog.uzpay.screen.model.request.CardRequest
import uz.blog.uzpay.screen.model.response.BaseResponse
import uz.blog.uzpay.screen.model.request.LoginRequest
import uz.blog.uzpay.screen.model.request.PaymentRequest
import uz.blog.uzpay.screen.model.request.RegisterRequest
import uz.blog.uzpay.screen.model.response.AuthResponse


interface Api {

    @POST("api/registration")
    suspend fun registration(@Body request: RegisterRequest): Response<BaseResponse<AuthResponse?>>

    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<BaseResponse<AuthResponse>>

    @GET("api/categories")
    suspend fun getCategories(): Response<BaseResponse<List<CategoryModel>>>

    @GET("api/card/list")
    suspend fun getCards(): Response<BaseResponse<List<CardModel>>>

    @POST("api/add/card")
    suspend fun addToCard(@Body request: CardRequest): Response<BaseResponse<CardModel>>

    @GET("api/payment/list")
    suspend fun getPaymentHistory(): Response<BaseResponse<List<PaymentHistoryModel>>>

    @GET("api/category/{category_id}/services")
    suspend fun getPaymentList(@Path("category_id") id: Int): Response<BaseResponse<List<ServicesModel>>>

    @POST("api/payment")
    suspend fun payment(@Body request: PaymentRequest): Response<BaseResponse<Any?>>

    @GET()

    @Multipart
    @POST("api/advertisements/add")
    suspend fun addAds(
        @Part mainImage: MultipartBody.Part,
        @Part("category_id") category_id: RequestBody,
        @Part("region_id") region_id: RequestBody,
        @Part("district_id") district_id: RequestBody,
        @Part("name") name: RequestBody,
        @Part("comment") comment: RequestBody,
        @Part("price") price: RequestBody,
        @Part("address") address: RequestBody,
        @Part("phone") phone: RequestBody,
    ): Response<BaseResponse<Boolean?>>
}