package jedi.mobi.challenge.trivagostarwars.repository.network

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import jedi.mobi.challenge.trivagostarwars.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

internal object ApiProvider {
    private const val LOG_TAG = "API Log"
    private const val DEFAULT_TIMEOUT_SECONDS = 60L
    private const val BASE_URL = "https://swapi.co/api/"

    internal val api: IApi = createApi(IApi::class.java)

    private fun <T> createApi(service: Class<T>): T = createRetrofit().create(service)

    @Suppress("EXPERIMENTAL_API_USAGE")
    private fun createRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createHttpClient())
            .addConverterFactory(Json.nonstrict.asConverterFactory(contentType))
            .build()
    }

    private fun createHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.i(LOG_TAG, message)
                }
            }
        ).apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

}