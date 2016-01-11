package com.mapbox.geocoder.samples;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.mapbox.geocoder.GeocoderCriteria;
import com.mapbox.geocoder.MapboxGeocoder;
import com.mapbox.geocoder.service.models.GeocoderFeature;
import com.mapbox.geocoder.service.models.GeocoderResponse;

import java.io.IOException;
import java.util.List;

import retrofit.Response;

/**
 * Created by antonio on 11/25/15.
 */
public class GeocoderAdapter extends BaseAdapter implements Filterable {

    private final Context context;

    private GeocoderFilter geocoderFilter;

    private List<GeocoderFeature> features;

    public GeocoderAdapter(Context context) {
        this.context = context;
    }

    /*
     * Required by BaseAdapter
     */

    @Override
    public int getCount() {
        return features.size();
    }

    @Override
    public GeocoderFeature getItem(int position) {
        return features.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * Get a View that displays the data at the specified position in the data set.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view
        View view;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        } else {
            view = convertView;
        }

        // It always is a textview
        TextView text = (TextView) view;

        // Set the place name
        GeocoderFeature feature = getItem(position);
        text.setText(feature.getPlaceName());

        return view;
    }

    /*
     * Required by Filterable
     */

    @Override
    public Filter getFilter() {
        if (geocoderFilter == null) {
            geocoderFilter = new GeocoderFilter();
        }

        return geocoderFilter;
    }

    private class GeocoderFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            // No constraint
            if (TextUtils.isEmpty(constraint)) {
                return results;
            }

            // The geocoder client
            MapboxGeocoder client = new MapboxGeocoder.Builder()
                    .setAccessToken(Constants.MAPBOX_ACCESS_TOKEN)
                    .setLocation(constraint.toString())
                    .setType(GeocoderCriteria.TYPE_POI)
                    .build();

            Response<GeocoderResponse> response;
            try {
                response = client.execute();
            } catch (IOException e) {
                e.printStackTrace();
                return results;
            }

            features = response.body().getFeatures();
            results.values = features;
            results.count = features.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results != null && results.count > 0) {
                features = (List<GeocoderFeature>) results.values;
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
