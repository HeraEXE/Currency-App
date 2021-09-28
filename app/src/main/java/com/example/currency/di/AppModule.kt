package com.example.currency.di

import android.content.Context
import androidx.room.Room
import com.example.currency.data.database.CurrencyDao
import com.example.currency.data.database.CurrencyDatabase
import com.example.currency.data.network.CurrencyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideCurrencyApi(): CurrencyApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl("http://api.exchangeratesapi.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CurrencyApi::class.java)
    }


    @Provides
    @Singleton
    fun provideCurrencyDao(
        @ApplicationContext context: Context
    ): CurrencyDao {
        return Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java,
            "Currency.db"
        ).build().getCurrencyDao()
    }
}