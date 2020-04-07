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

public class LoadImage extends AsyncTask<Void, Void, Void> {
    public static String imageDate;
    public static String imageUrl;
    public static String imagedate;
    public static String Description;
    public static String title;


    /**
     * constructor
     *
     * @param DateOfImage = date to be passed from date picker and from saved image dates
     */
    LoadImage(String DateOfImage) {
        imageDate = DateOfImage;
    }

    /**
     * AsyncTask method to scrap date, url, explanation etc. from JASON
     *
     * @param voids
     * @return
     */
    @Override
    protected Void doInBackground(Void... voids) {

        try {


            String urrl = "https://api.nasa.gov/planetary/apod?api_key=DgPLcIlnmN0Cwrzcg3e9NraFaYLIDI68Ysc6Zh3d&date=" + imageDate;
            URL UVurl = new URL(urrl);
            HttpURLConnection UVConnection = (HttpURLConnection) UVurl.openConnection();
            InputStream input = UVConnection.getInputStream();

            //create a JSON object from the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
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

    /**
     * To set the scrapped data to the widgets of {@link ImageDetails } class in order to show
     * the required image
     *
     * @param aVoid
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        String photo = getImageUrl();
        ImageDetails objj = new ImageDetails();


        String t = getTitle();
        String d = getDescription();
        objj.title.setText(t);
        objj.description.setText(d + "\n\n Date: " + getImagedate());
        objj.url.setText("Image Url: " + getImageUrl());

        //Loading image using Picasso (Reference) = https://inducesmile.com/android-programming/how-to-display-image-on-imageview-with-image-url-in-android/
        Picasso.get().load(photo).into(objj.nasaImage);


    }

    /**
     * getter for image url
     *
     * @return url
     */
    public static String getImageUrl() {
        return imageUrl;
    }

    /**
     * setter for image url
     *
     * @param imageUrl passes image url
     */
    public static void setImageUrl(String imageUrl) {
        LoadImage.imageUrl = imageUrl;
    }

    /**
     * getter for Date of the image
     * @return Date
     */
    public static String getImagedate() {
        return imagedate;
    }

    /**
     * stetter for image date
     * @param imagedate
     */
    public static void setImagedate(String imagedate) {
        LoadImage.imagedate = imagedate;
    }

    /**
     * getter for explanation
     * @return
     */
    public static String getDescription() {
        return Description;
    }

    /**
     * setter for Description
     * @param description
     */
    public static void setDescription(String description) {
        Description = description;
    }

    /**
     * getter for title
     * @return title
     */
    public static String getTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title
     */
    public static void setTitle(String title) {
        LoadImage.title = title;
    }

}