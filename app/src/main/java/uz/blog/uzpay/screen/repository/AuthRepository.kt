package uz.blog.uzpay.screen.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.blog.uzpay.screen.api.Api
import uz.blog.uzpay.screen.model.request.LoginRequest
import uz.blog.uzpay.screen.model.request.RegisterRequest
import uz.blog.uzpay.screen.model.response.AuthResponse
import uz.blog.uzpay.screen.repository.selead.DataResult

class AuthRepository(private val api: Api): BaseRepository() {

    suspend fun registration(registerRequest: RegisterRequest): DataResult<AuthResponse>{
        return withContext(Dispatchers.IO){
            try {
                val response = api.registration(registerRequest)
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

    suspend fun login(loginRequest: LoginRequest): DataResult<AuthResponse>{
        return withContext(Dispatchers.IO){
            try {
                val response = api.login(loginRequest)
                if (response.isSuccessful){
                    return@withContext DataResult.Success(response.body()?.data!!)
                }else {
                    return@withContext DataResult.Error(response.message())
                }
            } catch (e: Exception){
                return@withContext DataResult.Error(e.localizedMessage)
            }
        }
    }
}