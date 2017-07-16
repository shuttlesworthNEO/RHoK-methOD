package com.siddhantchadha.rhok_method.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.siddhantchadha.rhok_method.APIUtils;
import com.siddhantchadha.rhok_method.Fragments.FeedsFragment;
import com.siddhantchadha.rhok_method.R;
import com.siddhantchadha.rhok_method.data.SOService;
import com.siddhantchadha.rhok_method.models.CreateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        ImageView tick = (ImageView)findViewById(R.id.imageView2);
        ImageView cross = (ImageView)findViewById(R.id.imageView3);

        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PopupActivity.this, MainActivity.class));
                final JsonObject random = new JsonObject();

                final SOService mService = APIUtils.getSOService();
                mService.getResolvedResponse(random).enqueue(new Callback<CreateResponse>() {
                    @Override
                    public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<CreateResponse> call, Throwable throwable) {

                    }
                });
            }
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PopupActivity.this, MainActivity.class));
            }
        });
    }
}
