package com.example.final_project.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.R;

public class ImageDetails  extends AppCompatActivity {
 //  public static String imageDate;
//public static String API;
  // public static String ImageURL;
 public static ImageView nasaImage;
 public static TextView description;
 public static TextView date;
 public static TextView title;
public static ProgressBar progressBar;
 LoadImage obj = new LoadImage();
    @Override
    protected void onCreate(Bundle savedInstanceState)   	{
        super.onCreate(savedInstanceState);

        //date= (TextView)  findViewById(R.id.date);

            obj.execute();




        setContentView(R.layout.image_show);

        //  imageDate= getIntent().getStringExtra("Date");
        //API="https://api.nasa.gov/planetary/apod?api_key=f9ByT6nJjwNNU2gAVBWWX6if2EJQ3VOmQNhAmtYH&date="+imageDate;

      //  TextView info = (TextView) findViewById(R.id.information);
     //   String photo= obj.getImageUrl();
        description= (TextView)  findViewById(R.id.information);

        title= (TextView)  findViewById(R.id.titlez);


        nasaImage = findViewById(R.id.nasaImage);

        //Loading image using Picasso (Reference) = https://inducesmile.com/android-programming/how-to-display-image-on-imageview-with-image-url-in-android/
      //  Picasso.get().load(photo).into(nasaImage);



    }









}














