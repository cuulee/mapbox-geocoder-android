package com.mapbox.geocoder.service;

import com.mapbox.geocoder.service.models.GeocoderResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by antonio on 11/6/15.
 */
public interface GeocoderService {

    @GET("/geocoding/v5/{dataset}/{query}.json")
    Call<GeocoderResponse> geocode(
            @Path("dataset") String dataset,
            @Path("query") String query,
            @Query("access_token") String accessToken,
            @Query("proximity") String proximity,
            @Query("types") String types);

}
