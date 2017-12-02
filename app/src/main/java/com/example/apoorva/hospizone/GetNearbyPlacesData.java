package com.example.apoorva.hospizone;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Apoorva on 17-Aug-17.
 */

public class GetNearbyPlacesData extends AsyncTask<Object,String,String> {

    private String googlePlacesData;
    private GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... params) {

        mMap = (GoogleMap)params[0];
        url = (String)params[1];

        DownloadUrl downloadUrl = new DownloadUrl();
        googlePlacesData = downloadUrl.readUrl(url);
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        // super.onPostExecute(s);
        DataParser dataParser = new DataParser();
        List<HashMap<String,String>> nearbyPlacesData =null;
        nearbyPlacesData = dataParser.parse(s);
        Log.d("nearbyplacesdata","called parse method");
        showNearByPlaces(nearbyPlacesData);
    }

    public void showNearByPlaces(List<HashMap<String,String>> placesList){

        for (int i=0;i<placesList.size();i++){
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String,String> googlePlace = placesList.get(i);
            String placename = googlePlace.get("placename");
            String vicinity = googlePlace.get("vicinity");
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));

            LatLng latLng = new LatLng(lat,lng);
            markerOptions.position(latLng);
            markerOptions.title(placename+" : "+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

            mMap.addMarker(markerOptions);
            //to move camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        }
    }
}
