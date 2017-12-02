package com.example.apoorva.hospizone;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Apoorva on 17-Aug-17.
 */

public class DownloadUrl {

    public String readUrl(String myUrl){
        String data = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(myUrl);                                   //created url
            urlConnection = (HttpURLConnection) url.openConnection();   //opened connection
            urlConnection.connect();                                    //connected url

            //reading data from url using inputstream and buffer reader
            inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();

            //now we read line one by one using while loop
            String line = "";
            while ((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            //we have read all data
            data = stringBuffer.toString();
            bufferedReader.close();
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }

        Log.d("DownloadURL","Returning data = "+data);

        return data;
    }
}
