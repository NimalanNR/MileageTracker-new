package com.example.tnfdev.mileagetracker.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tnfdev.mileagetracker.Api;
//import com.example.tnfdev.mileagetracker.ContactAdapter;
import com.example.tnfdev.mileagetracker.R;
//import com.example.tnfdev.mileagetracker.model.ContactModel;
import com.example.tnfdev.mileagetracker.VehicleAdapter;
import com.example.tnfdev.mileagetracker.model.Vehicles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MileageListFragment extends Fragment {


    private View mView;
    String webAddress = "http://172.20.10.9:45455/api/vehicle/";

    VehicleAdapter vehicleAdapter;
    ArrayList<Vehicles> arr_vehichles = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_mileage_list, container, false);
        RecyclerView recList = (RecyclerView) mView.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        vehicleAdapter = new VehicleAdapter(arr_vehichles);
        recList.setAdapter(vehicleAdapter);

        /*ContactAdapter ca = new ContactAdapter(createList(4));
        recList.setAdapter(ca);*/

//        webServiceCallMethod();

        getList();

        return mView;

    }

   /* private void webServiceCallMethod() {


        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JSONObject jsonObject = new JSONObject();
        final JSONArray jsonArray = new JSONArray();
        try {
            jsonObject.put("webkey", "my valu");
            jsonObject.put("webkey", "my value");
        } catch (JSONException e) {
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                webAddress, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("Response0", response.toString());

                try {
                    String nameText = response.getString("name");
//                    jsonArray = response.
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Response", "Error: " + error.getMessage());

            }
        });
        int socketTimeout = 50000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjReq.setRetryPolicy(policy);
        queue.add(jsonObjReq);

    }*/

    public void getList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(webAddress)
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(new ToStringConverterFactory())
//                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        Call<String> call = api.getAllVehicles();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                Log.i("MileageList", " getList onResponse code " + response.code() + " isSuccessful " + response.isSuccessful());
                Log.i("MileageList", " getList onResponse body before " + response.body());
                String response_body = response.body();
                response_body = response_body.replace("vehicle ","vehicle");
                Log.i("MileageList", " getList onResponse body after " + response.body());
//String Users_Id;
//    String Bike_Id;
//    String Bike_Model;
//    String Existing_Fuel;
//    String Fuel_Refil;
//    String Fuel_Left;
//    String Amount_Paid;
//    String Initial_Odometer_Reading;
//    String Current_Odometer_Reading;
                try {
                    JSONObject jsonObject = new JSONObject(response_body);
                    JSONArray jsonArray = jsonObject.getJSONArray("vehicle");
                    if (jsonArray != null) {
                        if (jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject vehicle = jsonArray.getJSONObject(i);
                                Vehicles vehicles = new Vehicles(vehicle.optString("Users_Id"),
                                        vehicle.optString("Bike_Id"), vehicle.optString("Bike_Model")
                                        , vehicle.optString("Existing_Fuel"), vehicle.optString("Fuel_Refil")
                                        , vehicle.optString("Fuel_Left") , vehicle.optString("Amount_Paid")
                                        , vehicle.optString("Initial_Odometer_Reading"), vehicle.optString("Current_Odometer_Reading"));
                                arr_vehichles.add(vehicles);
//                                vehicles.setUsers_Id(jsonArray.optString(0));


                            }
                            Log.i("MileageListFragment"," getList "+" onResponse arr_vehichles size "+arr_vehichles.size());
                            if (arr_vehichles.size() > 0) {

                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("MileageList", " getList onFailure err msg " + t.getMessage());
            }
        });
    }

    /*private List<ContactModel> createList(int size) {

        List<ContactModel> result = new ArrayList<ContactModel>();
        for (int i = 1; i <= size; i++) {
            ContactModel ci = new ContactModel();
            ci.name = ContactModel.NAME_PREFIX + i;
            ci.surname = ContactModel.SURNAME_PREFIX + i;
            ci.email = ContactModel.EMAIL_PREFIX + i + "@test.com";

            result.add(ci);

        }

        return result;
    }*/


}
