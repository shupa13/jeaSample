package com.seoho.loginsample;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;

    private EditText etEmail;
    private EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        Button btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                UserService userService = retrofit.create(UserService.class);
                Call<UserModel> call = userService.loginAnother(new LoginCommand(email,password));
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful()){
                            Log.v("TAG", response.body().toString());
                            Toast.makeText(MainActivity.this, response.body().getName(), Toast.LENGTH_SHORT).show();
                        }else {
                            Log.v("TAG", "response error");
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {

                    }
                });
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.105:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    interface UserService{

        @FormUrlEncoded
        @POST("auth/login")
        Call<UserModel> login(@Field("email") String email, @Field("password") String password);

        @POST("auth/login-another")
        Call<UserModel> loginAnother(@Body LoginCommand command);
    }
}
