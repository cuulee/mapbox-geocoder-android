package com.mapbox.geocoder.service.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 11/6/15.
 */
public class FeatureGeometry {

    public String type;
    public List<Double> coordinates;

    public FeatureGeometry() {
        coordinates = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

}
