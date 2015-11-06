package com.mapbox.geocoder;

import android.util.Log;

import com.mapbox.geocoder.service.GeocoderService;
import com.mapbox.geocoder.service.models.GeocoderResponse;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by antonio on 11/6/15.
 */
public class MapboxGeocoder {

    private final static String LOG_TAG = "MapboxGeocoder";

    private final static String BASE_URL = "https://api.mapbox.com";

    private Call<GeocoderResponse> _call;

    public MapboxGeocoder(Builder builder) {
        GeocoderService service = getService();
        _call = service.geocode(
                builder._geocodingDataset,
                builder._query,
                builder._accessToken,
                builder._proximity,
                builder._geocodingType);
    }

    /*
     * Retrofit API
     */

    public Response<GeocoderResponse> execute() throws IOException {
        return _call.execute();
    }

    public void enqueue(Callback<GeocoderResponse> callback) {
        _call.enqueue(callback);
    }

    public void cancel() {
        _call.cancel();
    }

    public Call<GeocoderResponse> clone() {
        return _call.clone();
    }

    GeocoderService getService() {
        // Log the URL for debugging purposes
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.d(LOG_TAG, String.format("Mapbox URL: %s", request.url()));
                com.squareup.okhttp.Response response = chain.proceed(request);
                return response;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GeocoderService service = retrofit.create(GeocoderService.class);
        return service;
    }

    /*
     * Builder
     */

    public static class Builder {

        /*
         * Required
         */

        private String _accessToken;
        private String _query;

        @GeocoderCriteria.GeocoderDataset
        private String _geocodingDataset;

        /*
         * Optional (Retrofit will omit these from the request if they remain null)
         */

        private String _proximity = null;

        @GeocoderCriteria.GeocoderType
        private String _geocodingType = null;

        public Builder() {
            // Defaults
            _geocodingDataset = GeocoderCriteria.DATASET_PLACES;
        }

        public Builder setAccessToken(String accessToken) {
            _accessToken = accessToken;
            return this;
        }

        public Builder setDataset(@GeocoderCriteria.GeocoderDataset String geocodingDataset) {
            _geocodingDataset = geocodingDataset;
            return this;
        }

        public Builder setLocation(String location) {
            _query = location;
            return this;
        }

        public Builder setCoordinates(double longitude, double latitude) {
            _query = String.format("%f,%f", longitude, latitude);
            return this;
        }

        public Builder setProximity(double longitude, double latitude) {
            _proximity = String.format("%f,%f", longitude, latitude);
            return this;
        }

        public Builder setType(@GeocoderCriteria.GeocoderType String geocodingType) {
            _geocodingType = geocodingType;
            return this;
        }

        public MapboxGeocoder build() {
            return new MapboxGeocoder(this);
        }

    }

}
