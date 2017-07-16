package com.siddhantchadha.rhok_method.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.siddhantchadha.rhok_method.APIUtils;
import com.siddhantchadha.rhok_method.R;
import com.siddhantchadha.rhok_method.data.SOService;
import com.siddhantchadha.rhok_method.models.CreateResponse;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if (getSupportActionBar()!=null)
            getSupportActionBar().hide();

        final EditText name = (EditText)findViewById(R.id.NAME);
        final EditText username = (EditText)findViewById(R.id.UNAME);
        final EditText password = (EditText)findViewById(R.id.PASSWORD);
        final EditText email = (EditText)findViewById(R.id.EMAIL);
        final String key = "priv3";

        final SOService mService = APIUtils.getSOService();


        FancyButton signupButton = (FancyButton)findViewById(R.id.signup_btn);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JsonObject random = new JsonObject();
                random.addProperty("name",name.getText().toString());
                random.addProperty("username",username.getText().toString());
                random.addProperty("password",password.getText().toString());
                random.addProperty("email",email.getText().toString());
                random.addProperty("key",key);

                mService.getSignupResponse(random).enqueue(new Callback<CreateResponse>() {
                    @Override
                    public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                            if (response.isSuccessful()){
                                startActivity(new Intent(SignUp.this,SignIn.class));
                            }
                    }

                    @Override
                    public void onFailure(Call<CreateResponse> call, Throwable throwable) {

                    }
                });
            }
        });

    }
}
