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

    private String id;
    private String type;
    private String text;
    @SerializedName("place_name") private String placeName;
    private double relevance;
    private List<Double> bbox;
    private List<Double> center;
    private FeatureGeometry geometry;
    private List<FeatureContext> context;

    /*
     * We leave properties as a generic object because at this moment Carmen makes no
     * specifications nor guarantees about the properties of each feature object. Feature
     * properties are passed directly from indexes and may vary by feature and datasource.
     */

    public Object properties;

    public GeocoderFeature() {
        this.bbox = new ArrayList<>();
        this.center = new ArrayList<>();
        this.context = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getRelevance() {
        return this.relevance;
    }

    public void setRelevance(double relevance) {
        this.relevance = relevance;
    }

    public List<Double> getBbox() {
        return this.bbox;
    }

    public void setBbox(List<Double> bbox) {
        this.bbox = bbox;
    }

    public List<Double> getCenter() {
        return this.center;
    }

    public void setCenter(List<Double> center) {
        this.center = center;
    }

    public FeatureGeometry getGeometry() {
        return this.geometry;
    }

    public void setGeometry(FeatureGeometry geometry) {
        this.geometry = geometry;
    }

    public List<FeatureContext> getContext() {
        return this.context;
    }

    public void setContext(List<FeatureContext> context) {
        this.context = context;
    }

    public Object getProperties() {
        return this.properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
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
