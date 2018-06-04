package com.example.tnfdev.mileagetracker;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.tnfdev.mileagetracker.databinding.ActivityMileageTrackerBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class MileageTracker extends AppCompatActivity implements View.OnClickListener {

    ActivityMileageTrackerBinding binding;
    String url = "http://172.20.10.9:45455/api/User/check";
    String str_username, str_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mileage_tracker);
        binding.btnSignup.setOnClickListener(this);
        binding.btnSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_signup:
                Intent intent = new Intent(MileageTracker.this, SignUpActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_signin:
                str_username = binding.inputUsername.getText().toString();
                str_password = binding.inputPassword.getText().toString();

                if (TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_password)) {
                    Toast.makeText(MileageTracker.this, getString(R.string.err_emptyfields), Toast.LENGTH_SHORT).show();
                } else {
                    UserLogin();
                }
                break;
        }
    }
    public void UserLogin()
    {
        RequestQueue queue = Volley.newRequestQueue(MileageTracker.this);

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("Mobile_No",str_username);
            jsonObject.put("Nepassword", str_password);
        } catch (JSONException e) {
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url , jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("Response0", "response str "+response.toString());
                Log.i("OtpValidation","userRegister onResponse "+response.toString());

                try {
                    String nameText =response.getString("Username");
                    Toast.makeText(getApplicationContext(),"Login SucessFully",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(MileageTracker.this, HomeActivity.class);
                    startActivity(intent1);
                    finish();

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
}
