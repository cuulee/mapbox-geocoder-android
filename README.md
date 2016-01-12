[![Build Status](https://www.bitrise.io/app/3d3b6f8bee5f91a7.svg?token=rGXK_UEmSffrdjTLeig5Uw&branch=master)](https://www.bitrise.io/app/3d3b6f8bee5f91a7)

# Mapbox geocoder client for Android

[Mapbox Geocoder](https://www.mapbox.com/developers/api/geocoding) client for Android.

This library is also a full drop-in replacement for the standard Android
[Geocoder](http://developer.android.com/reference/android/location/Geocoder.html).

![Screenshot](https://raw.githubusercontent.com/mapbox/mapbox-directions-android/master/screenshot.png?token=AAAbNIrfSwudsPYrJL6ZTkW_aCde1-edks5WmCjCwA%3D%3D)

## Installation

We recommend installing with Gradle. This will automatically install the necessary dependencies and pull the library binaries from the Mapbox Android repository on Maven Central.

To install the current _stable version_ add this to your `build.gradle`:

```
repositories {
    mavenCentral()
}

dependencies {
    compile ('com.mapbox.mapboxsdk:mapbox-android-geocoder:1.0.0@aar'){
        transitive=true
    }
}
```

To install the current _SNAPSHOT_ version add this to your `build.gradle`:

```
repositories {
    mavenCentral()
    maven { url "http://oss.sonatype.org/content/repositories/snapshots/" }
}

dependencies {
    compile ('com.mapbox.mapboxsdk:mapbox-android-geocoder:1.1.0-SNAPSHOT@aar'){
        transitive=true
    }
}
```

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

### Samples

See the `samples` folder for two complete apps that implement the Geocoder library:

1. `LocationAddress` shows how to replace the stock Android geocoder with the Mapbox geocoder library.

2. `GeocoderSamples` includes both forward and reverse geocoder activities, including code to implement an autocomplete widget.

Both projects require that you supply your own `MAPBOX_ACCESS_TOKEN`.

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

## Autocomplete widget

If you want to implement an autocomplete widget like the one pictured above,
check the [Geocoder Samples](https://github.com/mapbox/mapbox-geocoder-android/tree/master/samples/GeocoderSamples) folder.
The `MainActivity` shows how to implement a `GeocoderAdapter` than you could pass to an Android `AutoCompleteTextView`
to create a geocoder autocomplete widget.
