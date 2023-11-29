package uz.blog.uzpay.screen.repository

import retrofit2.Response
import uz.blog.uzpay.screen.model.response.BaseResponse
import uz.blog.uzpay.screen.repository.selead.DataResult


open class BaseRepository {
    fun <T>wrapResponse(response: Response<BaseResponse<T>>): DataResult<T> {
        return if (response.isSuccessful){
            if (response.body()?.success == true){
                DataResult.Success(response.body()?.data!!)
            } else{
                if (response.body()?.error_code == 1){
                    //DataResult.Error(response.body()?.message ?: "")
                }
                DataResult.Error(response.body()?.message ?: "")
            }
        } else {
            DataResult.Error(response.message())
        }
    }
}