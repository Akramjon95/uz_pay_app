package uz.blog.uzpay.screen.api

import android.os.Build
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.orhanobut.hawk.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.blog.uzpay.screen.MyApp
import uz.blog.uzpay.screen.utils.Constants
import uz.blog.uzpay.screen.utils.PrefUtils

object NetworkObject {
    private var client: Api? = null
    fun initClient(){
        client = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor{chain->
                    val request = chain.request().newBuilder()
                        .addHeader("Token", PrefUtils.getToken())
                        .addHeader("Key", Constants.DEVELOPER_KEY)
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor(
                    ChuckerInterceptor.Builder(MyApp.app)
                        .collector(ChuckerCollector(MyApp.app))
                        .maxContentLength(250000L)
                        .redactHeaders(emptySet())
                        .alwaysReadResponseBody(false)
                        .build()
                ).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    fun getClientInstance(): Api {
        if (client == null){
            initClient()
        }

        return client!!
    }
}