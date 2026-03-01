package android.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("your_endpoint_here")
    fun getYourData(@Query("parameter") param: String): Call<YourDataType>
}