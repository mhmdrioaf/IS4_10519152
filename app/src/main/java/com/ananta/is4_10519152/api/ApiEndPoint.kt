package com.ananta.is4_10519152.api

import com.ananta.is4_10519152.data.Contacts
import com.ananta.is4_10519152.data.Delete
import com.ananta.is4_10519152.data.Insert
import com.ananta.is4_10519152.data.Update
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @GET("read.php")
    fun read(): Call<Contacts>

    @FormUrlEncoded
    @POST("create.php")
    fun create(
        @Field("contact_name") contact_name: String,
        @Field("contact_number") contact_number: String
    ): Call<Insert>

    @FormUrlEncoded
    @POST("update.php")
    fun update(
        @Field("contact_id") contact_id: Int,
        @Field("contact_name") contact_name: String,
        @Field("contact_number") contact_number: String
    ): Call<Update>

    @FormUrlEncoded
    @POST("delete.php")
    fun delete(
        @Field("contact_id") contact_id: Int
    ): Call<Delete>
}