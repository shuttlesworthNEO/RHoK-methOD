package com.siddhantchadha.rhok_method.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.siddhantchadha.rhok_method.APIUtils;
import com.siddhantchadha.rhok_method.R;
import com.siddhantchadha.rhok_method.data.SOService;
import com.siddhantchadha.rhok_method.models.CreateResponse;

import java.util.ArrayList;
import java.util.Locale;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String key = "nonpriv";

    private TextView txtSpeechInput;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar()!=null)
            getSupportActionBar().hide();

        ImageView micImg = (ImageView)findViewById(R.id.microphone);
        micImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        txtSpeechInput = (TextView)findViewById(R.id.speech);

        FancyButton signUpButton = (FancyButton)findViewById(R.id.signup_btn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
            }
        });



        FancyButton loginButton = (FancyButton)findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignIn.class));
            }
        });



    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech prompt");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    final JsonObject random = new JsonObject();
                    random.addProperty("key",key);
                    random.addProperty("phone","9958069697");
                    random.addProperty("query",result.get(0));

                    final SOService mService = APIUtils.getSOService();
                    mService.getResponses(random).enqueue(new Callback<CreateResponse>() {
                        @Override
                        public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                            if (response.isSuccessful()){
                                startActivity(new Intent(MainActivity.this,QueryTaken.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<CreateResponse> call, Throwable throwable) {

                        }
                    });
                }
                break;
            }

        }
    }

}
