package com.mapbox.geocoder.service.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 11/6/15.
 */
public class GeocoderResponse {

    private String type;
    private List<String> query;
    private List<GeocoderFeature> features;
    private String attribution;

    public GeocoderResponse() {
        this.query = new ArrayList<>();
        this.features = new ArrayList<>();
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getQuery() {
        return this.query;
    }

    public void setQuery(List<String> query) {
        this.query = query;
    }

    public List<GeocoderFeature> getFeatures() {
        return this.features;
    }

    public void setFeatures(List<GeocoderFeature> features) {
        this.features = features;
    }

    public String getAttribution() {
        return this.attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }
}
