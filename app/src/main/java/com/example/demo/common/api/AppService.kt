package com.example.demo.common.api

import android.annotation.SuppressLint
import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


@SuppressLint("StaticFieldLeak")
object AppService {

    var context: Context? = null

    fun createService(context: Context): ApiInterface {
        this.context = context
        return setupRetrofit().create(ApiInterface::class.java)
    }

    private fun setupRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WebConstant.BASE_URL)
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SuppressLint("StaticFieldLeak")
    private fun createOkHttpClient(): OkHttpClient? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return try {
            val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {}
                        override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {}
                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
            )
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
                    .hostnameVerifier(HostnameVerifier { hostname: String?, session: SSLSession? -> true })
                    .addInterceptor(Interceptor { chain ->
                        val original = chain.request()
                        val request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .header("User-Agent", "Android")
                                .method(original.method, original.body)
                                .build()
                        return@Interceptor chain.proceed(request)
                    })
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(BasicAuthInterceptor("Authorization","563492ad6f917000010000010e363249072149a3af729d48d2d0c4dd"))
                    .addInterceptor(interceptor)
                    .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}