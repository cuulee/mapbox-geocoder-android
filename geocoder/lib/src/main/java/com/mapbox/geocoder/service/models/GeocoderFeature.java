package com.mapbox.geocoder.service.models;

import android.location.Address;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by antonio on 11/6/15.
 */
public class GeocoderFeature {

    public String id;
    public String type;
    public String text;
    @SerializedName("place_name") public String placeName;
    public double relevance;
    public List<Double> bbox;
    public List<Double> center;
    public FeatureGeometry geometry;
    public List<FeatureContext> context;

    /*
     * We leave properties as a generic object because at this moment Carmen makes no
     * specifications nor guarantees about the properties of each feature object. Feature
     * properties are passed directly from indexes and may vary by feature and datasource.
     */

    public Object properties;

    public GeocoderFeature() {
        bbox = new ArrayList<>();
        center = new ArrayList<>();
        context = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getPlaceName() {
        return placeName;
    }

    public double getRelevance() {
        return relevance;
    }

    public Object getProperties() {
        return properties;
    }

    public List<Double> getBbox() {
        return bbox;
    }

    public List<Double> getCenter() {
        return center;
    }

    public FeatureGeometry getGeometry() {
        return geometry;
    }

    public List<FeatureContext> getContext() {
        return context;
    }

    /*
     * Additional API
     */

    public double getLatitude() {
        return getCenter().get(1);
    }

    public double getLongitude() {
        return getCenter().get(0);
    }

    /*
     * Export as an Android Address
     */

    public Address toAddress(Locale locale) {
        Address address = new Address(locale);
        address.setAddressLine(0, getPlaceName());
        address.setFeatureName(getText());
        address.setLongitude(getLongitude());
        address.setLatitude(getLatitude());
        return address;
    }

}
