package com.mapbox.geocoder;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by antonio on 11/6/15.
 */
public class GeocoderCriteria {

    /*
     * GeocodingDataset "typedef"
     */

    @StringDef({DATASET_PLACES, DATASET_PLACES_PERMANENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GeocoderDataset {}

    public static final String DATASET_PLACES = "mapbox.places";
    public static final String DATASET_PLACES_PERMANENT = "mapbox.places-permanent";

    /*
     * GeocodingType "typedef"
     */

    @StringDef({TYPE_COUNTRY, TYPE_REGION, TYPE_POSTCODE, TYPE_PLACE, TYPE_NEIGHBORHOOD, TYPE_ADDRESS, TYPE_POI})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GeocoderType {}

    public static final String TYPE_COUNTRY = "country";
    public static final String TYPE_REGION = "region";
    public static final String TYPE_POSTCODE = "postcode";
    public static final String TYPE_PLACE = "place";
    public static final String TYPE_NEIGHBORHOOD = "neighborhood";
    public static final String TYPE_ADDRESS = "address";
    public static final String TYPE_POI = "poi";

}
