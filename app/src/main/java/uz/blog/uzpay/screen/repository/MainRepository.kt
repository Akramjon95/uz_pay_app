package uz.blog.uzpay.screen.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.blog.uzpay.screen.api.Api
import uz.blog.uzpay.screen.model.CardModel
import uz.blog.uzpay.screen.model.CategoryModel
import uz.blog.uzpay.screen.model.PaymentHistoryModel
import uz.blog.uzpay.screen.model.request.RegisterRequest
import uz.blog.uzpay.screen.model.response.AuthResponse
import uz.blog.uzpay.screen.repository.selead.DataResult

class MainRepository(private val api: Api): BaseRepository() {

    suspend fun getCategories(): DataResult<List<CategoryModel>> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.getCategories()
                if (response.isSuccessful){
                    return@withContext DataResult.Success(response.body()?.data!!)
                }else {
                    return@withContext DataResult.Error(response.message())
                }
            } catch (e: Exception){
                return@withContext DataResult.Error(e.localizedMessage ?: "Error not found")
            }
        }
    }

    suspend fun getCards(): DataResult<List<CardModel>> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.getCards()
                if (response.isSuccessful){
                    return@withContext DataResult.Success(response.body()?.data!!)
                }else {
                    return@withContext DataResult.Error(response.message())
                }
            } catch (e: Exception){
                return@withContext DataResult.Error(e.localizedMessage ?: "Error not found")
            }
        }
    }

    suspend fun getPaymentHistory(): DataResult<List<PaymentHistoryModel>> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.getPaymentHistory()
                if (response.isSuccessful){
                    return@withContext DataResult.Success(response.body()?.data!!)
                }else {
                    return@withContext DataResult.Error(response.message())
                }
            } catch (e: Exception){
                return@withContext DataResult.Error(e.localizedMessage ?: "Error not found")
            }
        }
    }

}