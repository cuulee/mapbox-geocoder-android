# Mapbox geocoder client for Android

[Mapbox Geocoder](https://www.mapbox.com/developers/api/geocoding) client for Android.

This library is also a full drop-in replacement for the standard Android
[Geocoder](http://developer.android.com/reference/android/location/Geocoder.html).

## Installation

For now, compile the `lib` module in the `geocoder` folder and include the
resulting `.aar` file in your project as a new module.

Soon, you'll be able to download the latest version from Maven.

## Usage

To benefit from the full Mapbox Geocoder API, use the `MapboxGeocoder` object.

For example, to do forward geocoding with proximity you could do the following:

```
MapboxGeocoder client = new MapboxGeocoder.Builder()
	.setAccessToken(MAPBOX_ACCESS_TOKEN)
	.setLocation("The White House")
	.setProximity(longitude, latitude)
	.build();
```

For reverse geocoding (turning a pair of lat/lon coordinates into a
meaningful place name), restricting the results to points of interest (POI)
you could do:

```
MapboxGeocoder client = new MapboxGeocoder.Builder()
	.setAccessToken(MAPBOX_ACCESS_TOKEN)
	.setCoordinates(longitude, latitude)
	.setType(GeocoderCriteria.TYPE_POI)
	.build();
```

Once the `client` object has been created, you can launch a syncronous request
(remember not do this in the main UI thread):

```
Response<GeocoderResponse> response = client.execute();
```

Or an asynchronous request (you need to provide your own `Callback<GeocoderResponse>`):

```
client.enqueue(callback)
```

## Android Geocoder drop-in replacement

If your app is currently using the standard Android Geocoder, you can simply switch to Mapbox
swapping `android.location.Geocoder` with `com.mapbox.geocoder.android.AndroidGeocoder`.
You only need to remember to set your access token as well.

For example, the reference sample for the Android Geocoder han an `IntentService` that
doest the following:

```
import android.location.Geocoder;

...

Geocoder geocoder = new Geocoder(context, Locale.getDefault());
addresses = geocoder.getFromLocation(
	location.getLatitude(),
	location.getLongitude(),
	1);
```

The equivalent code, using Mapbox's implementation is simply:

```
import com.mapbox.geocoder.android.AndroidGeocoder;

...

AndroidGeocoder geocoder = new AndroidGeocoder(context, Locale.getDefault());
geocoder.setAccessToken(MAPBOX_ACCESS_TOKEN);
addresses = geocoder.getFromLocation(
	location.getLatitude(),
	location.getLongitude(),
	1);
```

You can see the full code for this app in the `samples/LocationAddress` folder.

Note that, unlike the Android implementation, we don't have any use for the
`Context` in the constructor and you can omit it (passing `null`) when
creating a new instance of `AndroidGeocoder`.
