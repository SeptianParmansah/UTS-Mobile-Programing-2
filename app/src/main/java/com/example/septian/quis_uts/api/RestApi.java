package com.example.septian.quis_uts.api;

import com.example.septian.quis_uts.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by M. Septian Parmansah
 * Created by Server on 03/05/2019.
 */

public interface RestApi {
    //insert
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> sendToko(@Field("kode_toko") String kode_toko,
                                          @Field("nama_toko") String nama_toko,
                                          @Field("alamat_toko") String alamat_toko,
                                          @Field("pemilik_toko") String pemilik_toko);

    //read
    @GET("read.php")
    Call<ResponseModel> getToko();

    //update menggunakan 3 parameter
    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> updateToko( @Field("id") String id,
                                    @Field("kode_toko") String kode_toko,
                                    @Field("nama_toko") String nama_toko,
                                    @Field("alamat_toko") String alamat_toko,
                                    @Field("pemilik_toko") String pemilik_toko);

    //delete menggunakan parameter id
    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> deleteToko(@Field("id") String id);
}
