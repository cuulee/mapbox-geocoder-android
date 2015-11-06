package com.mapbox.geocoder.service.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 11/6/15.
 */
public class GeocoderResponse {

    public String type;
    public List<String> query;
    public List<GeocoderFeature> features;
    public String attribution;

    public GeocoderResponse() {
        query = new ArrayList<>();
        features = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public List<String> getQuery() {
        return query;
    }

    public List<GeocoderFeature> getFeatures() {
        return features;
    }

    public String getAttribution() {
        return attribution;
    }
}
