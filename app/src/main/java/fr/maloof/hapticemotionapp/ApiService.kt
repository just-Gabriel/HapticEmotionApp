package fr.maloof.hapticemotionapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/ld+json")
    @POST("users")
    fun createUser(@Body user: DataModel.User): Call<DataModel.User>


    @Headers("Content-Type: application/ld+json")
    @POST("telephones")
    fun createTelephone(@Body telephone: DataModel.Telephone): Call<DataModel.Telephone>

}


