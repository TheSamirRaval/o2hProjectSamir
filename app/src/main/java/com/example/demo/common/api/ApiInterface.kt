package com.example.demo.common.api

import com.example.demo.common.model.ImagesResponse
import io.reactivex.Single
import retrofit2.http.*

/**
 * This class contains all the api calls
 */
interface ApiInterface {

    @GET(WebConstant.ENDPOINT_URL)
    fun getStatus(@Query("page") page : Int,@Query("per_page") per_page: Int
    ): Single<ImagesResponse>


}