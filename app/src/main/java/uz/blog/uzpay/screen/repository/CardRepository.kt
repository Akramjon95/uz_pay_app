package uz.blog.uzpay.screen.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.blog.uzpay.screen.api.Api
import uz.blog.uzpay.screen.model.CardModel
import uz.blog.uzpay.screen.model.ServicesModel
import uz.blog.uzpay.screen.model.request.CardRequest
import uz.blog.uzpay.screen.model.request.PaymentRequest
import uz.blog.uzpay.screen.repository.selead.DataResult

class CardRepository(private val api: Api): BaseRepository() {

    suspend fun addToCard(request: CardRequest): DataResult<CardModel> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.addToCard(request)
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

    suspend fun getPaymentList(category_id: Int): DataResult<List<ServicesModel>> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.getPaymentList(category_id)
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

    suspend fun payment(request: PaymentRequest): DataResult<Any> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.payment(request)
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
}