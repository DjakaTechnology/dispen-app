package com.lutungkamarsung.dispen.connection

import com.lutungkamarsung.dispen.model.*
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
//sort by popular
//@GET("movie/popular?api_key=e4b2e71c4f49a96f4a45715fa770a341&language=en-US&page=1")
//Call<ModelListMovie> getMoviePopular();

    @POST("permission/insert")
    @FormUrlEncoded
    fun sickInsert(@Field("title") title:String,
                   @Field("days") days:Int,
                   @Field("description")desc:String,
                   @Field("img")img:String,
                   @Field("permission_type_id")id:Int): Deferred<Response<GenericModel>>

    @POST("permission/insert")
    @FormUrlEncoded
    fun permissionInsert(@Field("title") title:String,
                         @Field("days") days:Int,
                         @Field("description")desc:String,
                         @Field("permission_type_id")id:Int): Deferred<Response<GenericModel>>

    @POST("permission/insert")
    @FormUrlEncoded
    fun dispenInsert(@Field("title") title:String,
                     @Field("start_hour") start_hour:Int,
                     @Field("end_hour")end_hour:Int,
                     @Field("description")desc:String,
                     @Field("permission_type_id")id:Int): Deferred<Response<GenericModel>>

    @Multipart
    @POST("img/upload")
    fun uploadImg(@Part img: MultipartBody.Part): Deferred<Response<GenericModel>>

    @GET("permission/me")
    fun getPermissionMe():Deferred<Response<ArrayList<PermissionModel>>>

    @GET("permission/me/child/unconfirmed")
    fun getPermissionMyChild():Deferred<Response<ArrayList<PermissionModel>>>

    @GET("permission/me/child")
    fun getPermissionMyChildHistory():Deferred<Response<ArrayList<PermissionModel>>>

    @GET("school/{id}/class")
    fun getClasses(@Path("id")id:Int):Deferred<Response<ArrayList<Classes>>>

    @GET("class/{id}/absent")
    fun getSubClasses(@Path("id")id:Int):Deferred<Response<ArrayList<SubClass>>>

    @GET("subclass/{id}/absent")
    fun getAbsentSubClass(@Path("id")id:Int):Deferred<Response<ArrayList<PermissionModel>>>

    @GET("subclass/dispen")
    fun getDispenSubClass():Deferred<Response<ArrayList<SubClass>>>

    @POST("permission/{id}/confirm")
    @FormUrlEncoded
    fun confirmPermission(@Path("id")id:Int, @Field("status")status:Int):Deferred<Response<GenericModel>>

    @POST("login")
    @FormUrlEncoded
    fun login(@Field("email")email:String,
              @Field("password")password:String,
              @Field("device_key")device_key:String):Deferred<Response<UserModel>>

    @GET("child")
    fun child():Deferred<Response<UserDetail>>




}