package com.example.final_project.ui.main;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.final_project.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImage  extends AsyncTask<Void, Void, Void> {
    public static String imageDate;
    public static String API;
    public static String ImageURL;
 public static String imageUrl;
 public static String imagedate;
    public static String Description;
    public static String title;



LoadImage(String DateOfImage){
    imageDate=DateOfImage;
}

    @Override
    protected Void doInBackground(Void... voids) {

        try {


            String urrl = "https://api.nasa.gov/planetary/apod?api_key=DgPLcIlnmN0Cwrzcg3e9NraFaYLIDI68Ysc6Zh3d&date="+imageDate;
            URL UVurl = new URL(urrl);
            HttpURLConnection UVConnection = (HttpURLConnection) UVurl.openConnection();
            InputStream input = UVConnection.getInputStream();

            //create a JSON object from the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            String result = sb.toString();
            JSONObject jObject1 = new JSONObject(result);
            imageUrl = jObject1.getString("url");


            JSONObject jObject2 = new JSONObject(result);
            imageDate = jObject2.getString("date");


            JSONObject jObject3 = new JSONObject(result);
            Description = jObject3.getString("explanation");

            JSONObject jObject4 = new JSONObject(result);
            title = jObject4.getString("title");

setImageUrl(imageUrl);
setImagedate(imageDate);
setDescription(Description);
setTitle(title);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        String photo= getImageUrl();
        ImageDetails objj= new ImageDetails();


String t = getTitle();
String d = getDescription();
        objj.title.setText(t);
        objj.description.setText(d+"\n\n Date: "+getImagedate());
        objj.url.setText("Image Url: "+getImageUrl());
      //  objj.date.setText(getImagedate());

        //Loading image using Picasso (Reference) = https://inducesmile.com/android-programming/how-to-display-image-on-imageview-with-image-url-in-android/
        Picasso.get().load(photo).into(objj.nasaImage);

        //  ImageDetails s = new ImageDetails();

       // ImageURL= imageUrl;
    }
    public static String getImageUrl() {
        return imageUrl;
    }

    public static void setImageUrl(String imageUrl) {
        LoadImage.imageUrl = imageUrl;
    }
    public static String getImagedate() {
        return imagedate;
    }

    public static void setImagedate(String imagedate) {
        LoadImage.imagedate = imagedate;
    }
    public static String getDescription() {
        return Description;
    }

    public static void setDescription(String description) {
        Description = description;
    }
    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        LoadImage.title = title;
    }

}