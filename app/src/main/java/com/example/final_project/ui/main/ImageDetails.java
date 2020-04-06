package com.example.final_project.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.NASAImage;
import com.example.final_project.R;

public class ImageDetails  extends AppCompatActivity {
 //  public static String imageDate;
//public static String API;
  // public static String ImageURL;
 public static ImageView nasaImage;
 public static TextView description;
 public static TextView date;
 public static TextView title;
    public static TextView url;
    private static String textDate;

    SearchFragment obj = new SearchFragment();

    public static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)   	{
        super.onCreate(savedInstanceState);
 textDate = getIntent().getStringExtra("Date");
        LoadImage objj = new LoadImage( textDate);
        //date= (TextView)  findViewById(R.id.date);

            objj.execute();




        setContentView(R.layout.image_show);

        //  imageDate= getIntent().getStringExtra("Date");
        //API="https://api.nasa.gov/planetary/apod?api_key=f9ByT6nJjwNNU2gAVBWWX6if2EJQ3VOmQNhAmtYH&date="+imageDate;

      //  TextView info = (TextView) findViewById(R.id.information);
     //   String photo= obj.getImageUrl();
        description= (TextView)  findViewById(R.id.information);

        title= (TextView)  findViewById(R.id.titlez);
url = (TextView) findViewById(R.id.url);

        nasaImage = findViewById(R.id.nasaImage);

        //Loading image using Picasso (Reference) = https://inducesmile.com/android-programming/how-to-display-image-on-imageview-with-image-url-in-android/
      //  Picasso.get().load(photo).into(nasaImage);



    }

public void save(View v){

    SavedImagesFragment object = new SavedImagesFragment();

  object.data.add(textDate);

            Toast.makeText(getApplicationContext(),"Image Saved",Toast.LENGTH_SHORT).show();
        }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, NASAImage.class));
    }






}














