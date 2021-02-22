package com.ozioma.data

import com.ozioma.data.api.BitcoinInfoApi
import com.ozioma.data.api.BitcoinInfoService
import com.ozioma.data.api.RemoteBitcoinInfoService
import com.squareup.moshi.Moshi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BitcoinInfoServiceFactory @Inject constructor() {

    fun createBitcoinInfoService(isDebugBuild: Boolean): BitcoinInfoService {
        return RemoteBitcoinInfoService(
            getBitcoinInfoApi(
                getRetrofitClient(
                    getOkhttpClient(
                        isDebugBuild
                    ), getMoshi()
                )
            )
        )
    }

    private fun getBitcoinInfoApi(retrofit: Retrofit): BitcoinInfoApi {
        return retrofit.create(BitcoinInfoApi::class.java)
    }

    private fun getRetrofitClient(
        client: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    private fun getOkhttpClient(isDebug: Boolean): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
        if (isDebug) {
            builder.addInterceptor(getHttpLoggingInterceptor())
        }
        return builder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    companion object {
        private const val BASE_URL = "https://api.blockchain.info/charts/"
    }
}