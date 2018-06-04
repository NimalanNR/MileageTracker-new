package com.example.tnfdev.mileagetracker;

import com.example.tnfdev.mileagetracker.model.Vehicles;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

//    String Base_Url = "http://192.168.1.7:443/api/";
    String Base_Url = "https://api.androidhive.info/";

    @GET("values")
    Call<User> getUser();

    /*@GET("contacts")
    Call<Contacts> getContacts();*/

    @GET("contacts")
    Call<String> getContacts();

    /*@GET("findall")
    Call<Vehicles> getAllVehicles();*/

    @GET("findall")
    Call<String> getAllVehicles();

    @GET("mileagecomparison")
    Call<String> getCompare(@Query("bike_id") String bike_id1, @Query("bike_id2") String bike_id2);

//    http://172.20.10.9:45455/api/vehicle/

    @GET("vehicle/{mileagecomparison}/")

    Call<String> getCompare(@Path("mileagecomparison") String user);


}
