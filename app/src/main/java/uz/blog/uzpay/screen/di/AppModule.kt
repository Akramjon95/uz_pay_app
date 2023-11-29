package uz.blog.uzpay.screen.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.blog.uzpay.screen.MyApp
import uz.blog.uzpay.screen.api.Api
import uz.blog.uzpay.screen.repository.AuthRepository
import uz.blog.uzpay.screen.repository.CardRepository
import uz.blog.uzpay.screen.repository.MainRepository
import uz.blog.uzpay.screen.utils.Constants
import uz.blog.uzpay.screen.utils.PrefUtils
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(api: Api): AuthRepository{
        return AuthRepository(api)
    }

    @Provides
    @Singleton
    fun provideMainRepository(api: Api): MainRepository{
        return MainRepository(api)
    }

    @Provides
    @Singleton
    fun provideCardRepository(api: Api): CardRepository{
        return CardRepository(api)
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api{
        return retrofit.create(Api::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
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
    }
}