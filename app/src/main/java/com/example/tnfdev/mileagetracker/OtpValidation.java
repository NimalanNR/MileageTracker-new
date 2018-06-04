package com.example.tnfdev.mileagetracker;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tnfdev.mileagetracker.databinding.ActivityOtpValidationBinding;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OtpValidation extends AppCompatActivity implements View.OnClickListener{

    ActivityOtpValidationBinding binding;
    String otp;
    String name,password,repassword,mobile;
    String  webAddress = "http://172.20.10.9:45455/api/User/create";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_otp_validation);
        binding.btnVerify.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        otp = bundle.getString("otp");
        name = bundle.getString("uname");
        password = bundle.getString("password");
        repassword = bundle.getString("repassword");
        mobile  = bundle.getString("mobile");

        Log.d("otp", otp);
        //addUserDetails();
    }
    public void onClick(View v)
    {
        int id = v.getId();
        switch (id)
        {
            case R.id.btn_verify:
                Log.d("onclick()","at onclick");
                boolean flag = checkValidNumber();
                Log.d("onclick()","at onclick1");
               // validateUser(flag);
                UserRegister();

                break;
        }
    }
    public void validateUser(boolean flag)
    {
        if(flag)
        {
            Toast.makeText(getApplicationContext(),"Register SucessFully",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(OtpValidation.this,MileageTracker.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"OOPS!!Invalid Password",Toast.LENGTH_SHORT).show();
        }

    }
    public boolean checkValidNumber()
    {
        Log.d("generatedString","OTP");
        String userOtp = binding.inputOtp.getText().toString();
        Log.d("User OTP",userOtp);
        if(otp.equals(userOtp))
        {
            return true;
        }
        Toast.makeText(getApplicationContext(),"OOPS!!Invalid OTP",Toast.LENGTH_SHORT).show();
        return  false;
    }
    public void UserRegister()
    {
        RequestQueue queue = Volley.newRequestQueue(OtpValidation.this);

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("Username", name);
            jsonObject.put("Nepassword", password);
            jsonObject.put("Repassword",repassword);
            jsonObject.put("Mobile_No",mobile);
        } catch (JSONException e) {
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                webAddress , jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("Response0", "response str "+response.toString());
                Log.i("OtpValidation","userRegister onResponse "+response.toString());

                try {
                    String nameText =response.getString("Username");
                    Toast.makeText(getApplicationContext(),"Register SucessFully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OtpValidation.this,MileageTracker.class);
                    startActivity(intent);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Response", "Error: " + error.getMessage());
                Log.i("OtpValidation","userRegister onError "+error.getMessage());

            }
        });
        int socketTimeout = 50000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjReq.setRetryPolicy(policy);
        queue.add(jsonObjReq);
    }

    public void addUserDetails()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(new ToStringConverterFactory())
//                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        Call<String> call = api.getContacts();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                /*User user = response.body();
                Log.d("otpvalidation"," success "+response.isSuccessful());
                Log.d("otpvalidation"," code "+response.code());
                Log.d("otpvalidation"," response.body() "+response.body().toString());
                Log.d("name",user.getName()+"");
                Log.d("passwd",user.getMobileno()+"");
                Log.d("mobileno",user.getPassword()+"");*/

                /*Contacts contacts = new Contacts();
                contacts = response.body();
                Log.i("otpvalidation","contacts size "+contacts.getContacts().size());
                Log.i("otpvalidation","contacts data 0 "+contacts.getContacts().get(0).getName() + contacts.getContacts().get(0).getEmail());*/


                Log.i("otpvalidation"," "+String.valueOf(response.body()));


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("error",t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}
