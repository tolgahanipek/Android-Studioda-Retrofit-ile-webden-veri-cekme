package com.nexis.retrofit_ile_webden_veri_cekme_islemi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TimeApi {
    @GET("Europe/Istanbul")
    Call<TimeTurkey> getTime();
}
