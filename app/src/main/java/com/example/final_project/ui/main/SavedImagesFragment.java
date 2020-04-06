package com.example.final_project.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.final_project.R;

import java.util.ArrayList;
import java.util.List;

public class SavedImagesFragment extends Fragment {


    public static ArrayList<String> data= new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.saved_images_layout, viewGroup, false);
        // ArrayList<String> data= new ArrayList<>();



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,data);

ListView newList = (ListView) view.findViewById(R.id.lisst);
newList.setAdapter(adapter);
newList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    SavedImagesFragment obj= new SavedImagesFragment();
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Get the selected item text from ListView
        String selectedItem = (String) parent.getItemAtPosition(position);

        // Display the selected item text on TextView
        Intent imageDetail = new Intent(getActivity(), ImageDetails.class);
        imageDetail.putExtra("Date", obj.data.get(position));
        startActivity(imageDetail);
    }
});

 return view;



 }

}











