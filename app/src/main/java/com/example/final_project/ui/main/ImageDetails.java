package com.example.final_project.ui.main;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.NASAImage;
import com.example.final_project.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashSet;

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
    private int progressStatus = 0;
    private Handler myHandler = new Handler();
    SavedImagesFragment object = new SavedImagesFragment();


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
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
new Thread(new Runnable() {
    @Override
    public void run() {
        while(progressStatus<100){
            progressStatus++;
            android.os.SystemClock.sleep(10);
            myHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setProgress(progressStatus);
                    
                }
            });

        }

    }
}).start();

        description= (TextView)  findViewById(R.id.information);

        title= (TextView)  findViewById(R.id.titlez);
url = (TextView) findViewById(R.id.url);

        nasaImage = findViewById(R.id.nasaImage);

        //Loading image using Picasso (Reference) = https://inducesmile.com/android-programming/how-to-display-image-on-imageview-with-image-url-in-android/
      //  Picasso.get().load(photo).into(nasaImage);



    }

public void save(View v){
    PhotoDatabase DBObject = new PhotoDatabase(this);
    final   SQLiteDatabase db = DBObject.getWritableDatabase();
    object.data.add(textDate);
    ContentValues newRowsValues = new ContentValues();
    newRowsValues.put(PhotoDatabase.COLUMN_DATE, textDate);
   db.insert(PhotoDatabase.TableName, null, newRowsValues);
    if(checkDuplicate(object.data)==false){
    Snackbar snackbar = Snackbar
            .make(v, "Image Already Saved", Snackbar.LENGTH_LONG);
    snackbar.show();
object.data.remove(textDate);
        db.delete(PhotoDatabase.TableName,PhotoDatabase.COLUMN_DATE+"= ?",new String[]{textDate});
        ContentValues addAgain = new ContentValues();
        addAgain.put(PhotoDatabase.COLUMN_DATE, textDate);
        db.insert(PhotoDatabase.TableName, null, addAgain);
        object.newList.setAdapter(object.adapter);

        object.adapter.notifyDataSetChanged();

}else if (checkDuplicate(object.data)==true){


            Toast.makeText(getApplicationContext(),"Image Saved",Toast.LENGTH_SHORT).show();}

        }



    @Override
    //ON back pressed refresh listView to see added item
    public void onBackPressed() {

        finish();
        object.adapter.notifyDataSetChanged();


    }
//To check if the Image is saved or not already.......
    public static boolean checkDuplicate(ArrayList list) {
        HashSet set = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            boolean val = set.add(list.get(i));
            if (val == false) {
                return val;
            }
        }
        return true;
    }




}














