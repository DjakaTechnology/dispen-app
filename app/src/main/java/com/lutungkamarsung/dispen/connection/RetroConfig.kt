package com.lutungkamarsung.dispen.connection

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lutungkamarsung.dispen.key.SharedKey
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetroConfig {
    private fun getRetrofit(c: Context): Retrofit {
        val pref = c.getSharedPreferences(SharedKey.Session.SESSION, MODE_PRIVATE)
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + pref.getString(SharedKey.Session.TOKEN, "")!!)
                .build()
            chain.proceed(newRequest)
        }.build()

        return Retrofit.Builder().baseUrl("http://dispen.djaka.id/api/").client(client).addConverterFactory(
            ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        //http://192.168.0.105/api-asistani/htdocs/public/
        //http://asistani.aic.my.id/
    }

    fun getApiServices(c: Context): ApiServices {
        return getRetrofit(c).create(ApiServices::class.java)
    }

    //    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
    //            .readTimeout(30, TimeUnit.SECONDS)
    //            .connectTimeout(30, TimeUnit.SECONDS)
    //            .build();
}
