package com.example.tnfdev.mileagetracker;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.telephony.SmsManager;


import com.example.tnfdev.mileagetracker.databinding.ActivitySignUpBinding;
import com.telerivet.Project;
import com.telerivet.TelerivetAPI;
import com.telerivet.Util;

import java.io.IOException;
import java.util.Random;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySignUpBinding binding;
    public String generatedPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        binding.btnSignup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signup:
                String str_username,str_password,str_repassword;
                String mobile;
                str_username = binding.inputUsername.getText().toString();
                str_password = binding.inputPassword.getText().toString();
                str_repassword = binding.inputRetypePassword.getText().toString();
                mobile = binding.inputMobileNo.getText().toString();
                if(TextUtils.isEmpty(str_username)||TextUtils.isEmpty(str_password)||TextUtils.isEmpty(mobile)||TextUtils.isEmpty(str_repassword))
                {
                    Toast.makeText(SignUpActivity.this,getString(R.string.err_emptyfields),Toast.LENGTH_SHORT).show();
                }else {
                    if(!str_password.equals(str_repassword)){
                        Toast.makeText(SignUpActivity.this,getString(R.string.err_pwd),Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(mobile.length()==10){
                         //intent to OTP page
                            Log.d("att","uyuyu");
                            try {
                                sendSms(binding.inputMobileNo.getText().toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(SignUpActivity.this,OtpValidation.class);
                            Log.d("Signup","generated OTP"+generatedPassword);
                            intent.putExtra("otp", generatedPassword);
                            intent.putExtra("uname", str_username);
                            intent.putExtra("password", str_password);
                            intent.putExtra("repassword", str_repassword);
                            intent.putExtra("mobile", mobile);

                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,getString(R.string.err_mobile),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }
    public String generateOTP()
    {
        Random random = new Random();
        generatedPassword = String.format("%04d", random.nextInt(10000));
        return  generatedPassword;
    }
    public void sendSms(String mobile_no) throws IOException {
        try {
            SmsManager sms = SmsManager.getDefault();
            String generatedPassword = generateOTP();
            sms.sendTextMessage(mobile_no, null, "Your One Time Password is:"+generatedPassword, null, null);
        }
        catch (Exception e )
        {

        }

    }
}
