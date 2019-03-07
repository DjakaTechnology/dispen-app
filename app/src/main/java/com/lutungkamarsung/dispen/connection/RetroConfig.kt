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
                .addHeader("Authorization", "Bearer " + pref.getString(SharedKey.Session.TOKEN, "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjM4MTVmOTJiZjhkZmZjOTVkOTMxNWIzYmQ1YjVjMTc0YTU0ODY2YzQ3MjZkMGNiMjZlMzg5MGZmOGVkMzhkNmYwMjIxOTBiY2E5MWViZWQ2In0.eyJhdWQiOiIxIiwianRpIjoiMzgxNWY5MmJmOGRmZmM5NWQ5MzE1YjNiZDViNWMxNzRhNTQ4NjZjNDcyNmQwY2IyNmUzODkwZmY4ZWQzOGQ2ZjAyMjE5MGJjYTkxZWJlZDYiLCJpYXQiOjE1NTE5ODI3MDQsIm5iZiI6MTU1MTk4MjcwNCwiZXhwIjoxNTgzNjA1MTA0LCJzdWIiOiIxMSIsInNjb3BlcyI6W119.biZah1hh9PsH-qCl_Sgfw6Hv5RJhu37nVQGDr_gAV85CP6I0unHvJ6jXBItUf20QyBCX6swxR0LC_ZsuK49R0TdBb28Ns_itKSNZLVSaNaJ_YpmxFAoNJsJbbII2fPYXVrUN3gv6z0X34iYTTJCe-HMGyiJlzFo0fRu3PB4eWhlcXr4Nbfhpajl9PWx0f6VRq-BROkkGEoPSH_UMfnA0_3MywCCpQtfEn8BEMG5QemZ8cFeI0cEmifVkpI11hqASXwNUpVU2z2cbyHfqSF46rpHkwNIWpdSxf1nwj2zsCXxPyU-eBCu4jccDb2JgvnxoUuD6Q2TZ5JjqXmxDePi4YsUp3zqjApi2R1zBMEpoXAB926SgQoJirbpcfwrcTNKNcxVnL9cHPt-6Yg4CD51A55O9RB5flTTAu7uIcJzTuSnXvA_vibSum2NXcj06c6ok0O4w-_sbLnl2w0ac31-HCKevj8Th_CANO8A_cTEL3CbmKA0E9tvOGvhejXEaFz_oyHhF0AIRprWXQZaLL8sq1VJoQ0dx68lcC0mPzrZcSAGt1QMYrscQV27dJcfQrLoGQPeO3x5h3iGz0KkX5Nurq6Sa5vhmnO2NmRkEMJ08xEYCMszk5DieNOy4yNCy9ZOTPlZlHLmC8qLIOcPuWAvSM8Vgh_ePk0p0aTxGg_ooSKo")!!)
                .build()
            chain.proceed(newRequest)
        }.build()

        return Retrofit.Builder().baseUrl("http://192.168.0.105/api/").client(client).addConverterFactory(
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
