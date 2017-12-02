package com.example.apoorva.hospizone;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Apoorva on 17-Aug-17.
 */

public class DataParser {
    private HashMap<String,String> getPlaces(JSONObject jsonObject){
        HashMap<String,String>  googlePlacesMap = new HashMap<String, String>();

        String placeName = "NA";
        String vicinity = "NA";
        String latitude = "";
        String longitude = "";
        String reference = "";

        Log.d("DataParser","jsonobject ="+jsonObject.toString());

        try {

            if(!jsonObject.isNull("name")){
                placeName = jsonObject.getString("name");
            }
            if(!jsonObject.isNull("vicinity")){
                vicinity = jsonObject.getString("vicinity");
            }
            latitude = jsonObject.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = jsonObject.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = jsonObject.getString("reference");

            googlePlacesMap.put("placename",placeName);
            googlePlacesMap.put("vicinity",vicinity);
            googlePlacesMap.put("lat",latitude);
            googlePlacesMap.put("lng",longitude);
            googlePlacesMap.put("reference",reference);

        }catch (JSONException e){
            e.printStackTrace();
        }

        return googlePlacesMap;
    }

    //to store all hashmaps in a list

    private List<HashMap<String,String>> getPlaces(JSONArray jsonArray){

        int count = jsonArray.length();
        List<HashMap<String,String>> placesList = new ArrayList<>();
        HashMap<String,String> places = null;
        for(int i=0;i<count;i++){
            try {
                places = getPlaces((JSONObject) jsonArray.get(i));
                placesList.add(places);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placesList;
    }

    //when use dataparse class we will call this method

    public List<HashMap<String,String>> parse(String jsonData){

        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getPlaces(jsonArray);
    }

}
