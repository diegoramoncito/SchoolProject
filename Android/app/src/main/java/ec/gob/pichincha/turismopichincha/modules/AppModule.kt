package ec.gob.pichincha.turismopichincha.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ec.gob.pichincha.turismopichincha.data.remote.MainApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideClient():OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()


    @Singleton
    @Provides
    fun provideMainApi(
        client: OkHttpClient
    ): MainApi{
        return Retrofit.Builder()
            .baseUrl("http://appturismo.pichincha.gob.ec")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MainApi::class.java)
    }

}