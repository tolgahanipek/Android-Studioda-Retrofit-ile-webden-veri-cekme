package com.nexis.retrofit_ile_webden_veri_cekme_islemi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button btnGettime;
    private TextView txtTime;

    private Retrofit retrofit;
    private TimeApi timeApi;
    private String baseUrl="http://worldtimeapi.org/api/timezone/";
    private Call<TimeTurkey> timeTurkeyCall;
    private TimeTurkey timeTurkey;

    private void init()
    {
        btnGettime=findViewById(R.id.main_activity_btnGetTime);
        txtTime=findViewById(R.id.main_activity_txtTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnGettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRetrofitSettings();
            }
        });
    }

    private void setRetrofitSettings()
    {
    retrofit=new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    timeApi=retrofit.create(TimeApi.class);
    timeTurkeyCall=timeApi.getTime();

    timeTurkeyCall.enqueue(new Callback<TimeTurkey>() {
        @Override
        public void onResponse(Call<TimeTurkey> call, Response<TimeTurkey> response) {
         if(response.isSuccessful())
         {
            timeTurkey=response.body();

            if(timeTurkey !=null)
            {
                txtTime.setText(timeTurkey.getDateTime().split("T")[0]);
                txtTime.setText(String.valueOf(timeTurkey.getDayOfWeek()));
                txtTime.setText(String.valueOf(timeTurkey.getWeekNumber()));
            }
         }
        }

        @Override
        public void onFailure(Call<TimeTurkey> call, Throwable t) {
           System.out.println(t.toString());
        }
    });
    }
}