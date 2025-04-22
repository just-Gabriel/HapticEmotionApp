package fr.maloof.hapticemotionapp.network

import fr.maloof.hapticemotionapp.ApiService
import fr.maloof.hapticemotionapp.network.FakeApiService
import fr.maloof.hapticemotionapp.RetrofitInstance

object ServiceLocator {

    // Change cette variable pour basculer entre vrai et faux backend
    private val useFakeApi = true

    val apiService: ApiService by lazy {
        if (useFakeApi) {
            FakeApiService()
        } else {
            RetrofitInstance.api
        }
    }
}
