package com.mapbox.geocoder.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mapbox.geocoder.MapboxGeocoder;
import com.mapbox.geocoder.service.models.GeocoderResponse;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = "MainActivity";

    private final static String MAPBOX_ACCESS_TOKEN = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample
        simpleSample();
    }

    private void simpleSample() {
        MapboxGeocoder client = new MapboxGeocoder.Builder()
                .setAccessToken(MAPBOX_ACCESS_TOKEN)
                .setLocation("The White House")
                .build();

        client.enqueue(new Callback<GeocoderResponse>() {
            @Override
            public void onResponse(Response<GeocoderResponse> response, Retrofit retrofit) {
                String placeName = response.body().getFeatures().get(0).getPlaceName();
                Log.d(LOG_TAG, "Place name: " + placeName);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG, "Error: " + t.getMessage());
            }
        });
    }
}
