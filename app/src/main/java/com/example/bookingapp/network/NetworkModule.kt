package com.example.bookingapp.network

import dagger.Module
import dagger.Provides
import com.example.bookingapp.BuildConfig.SUPABASE_ANON_KEY
import com.example.bookingapp.BuildConfig.SUPABASE_URL
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val url = SUPABASE_URL
    val apiKey = SUPABASE_ANON_KEY

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = url,
            supabaseKey = apiKey
        ) {
            install(Postgrest)
            install(GoTrue)
            /*install(ComposeAuth) {
                nativeGoogleLogin("WEB_GOOGLE_CLIENT_ID") //Use the Web Client ID, not the Android one!
            }*/

        }

    }

    @Provides
    @Singleton
    fun providePostgrest(client: SupabaseClient): Postgrest {
        return client.pluginManager.getPlugin(Postgrest)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }
}
