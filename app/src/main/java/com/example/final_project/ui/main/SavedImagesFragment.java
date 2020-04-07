package com.example.final_project.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.final_project.NASAImage;
import com.example.final_project.R;

import java.util.ArrayList;
import java.util.List;

public class SavedImagesFragment extends Fragment {


    public static ArrayList<String> data = new ArrayList<>();
    public static ArrayAdapter<String> adapter;
    public static ListView newList;

    /**
     * Fragment to show listview for the saved images
     *
     * @param inflater
     * @param viewGroup
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.saved_images_layout, viewGroup, false);
        PhotoDatabase DBObject = new PhotoDatabase(getContext());
        final SQLiteDatabase db = DBObject.getWritableDatabase();


        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
        String[] columns = {PhotoDatabase.COLUMN_DATE};
        String tableName = PhotoDatabase.TableName;
        Cursor results = db.query(false, PhotoDatabase.TableName, columns, null, null, null, null, null, null);

        int dateIndex = results.getColumnIndex(PhotoDatabase.COLUMN_DATE);


        while (results.moveToNext()) {

            String dates = results.getString(dateIndex);

            data.add(dates);
        }


        newList = (ListView) view.findViewById(R.id.lisst);
        newList.setAdapter(adapter);
/**
 * click listner for long item press to delete the item from the list view
 */
        newList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                // TODO Auto-generated method stub
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete Image");
                builder.setMessage("Are you sure to Delete this Image?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    SavedImagesFragment object = new SavedImagesFragment();

                    /**
                     * To genetate dialogue if user want to delete the image or not
                     * @param dialog
                     * @param which
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        db.delete(PhotoDatabase.TableName, PhotoDatabase.COLUMN_DATE + "= ?", new String[]{data.get(pos)});
                        object.data.remove(pos);
                        object.newList.setAdapter(object.adapter);

                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(),
                                "Image Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when No button clicked
                        Toast.makeText(getContext(),
                                "Image Not Deleted", Toast.LENGTH_SHORT).show();
                    }

                });
                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();


                return true;
            }
        });
        /**
         * To show the image when the item is clicked
         */
        newList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            SavedImagesFragment obj = new SavedImagesFragment();

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











