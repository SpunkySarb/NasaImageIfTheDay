package com.example.final_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.final_project.ui.main.PhotoDatabase;
import com.example.final_project.ui.main.SavedImagesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.ui.main.TabsAdapter;

public class NASAImage extends AppCompatActivity {
    /**
     * Main activity with tabbed layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasaimage);
        TabsAdapter sectionsPagerAdapter = new TabsAdapter(getSupportFragmentManager(),2, this);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Instructions");
                builder.setMessage("Search:\n1) Choose a date in Present/Past\n2)Click on Search\n3)Save Image by Clicking 'SAVE IMAGE' Button.\n\n" +
                        "Saved Images:\n1) Swipe to left to access saved Image List\n2) Click to View Image\n" +
                        "3) Long Press item to Delete");

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        });


    }
}