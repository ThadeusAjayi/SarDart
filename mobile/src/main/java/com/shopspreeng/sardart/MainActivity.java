package com.shopspreeng.sardart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.shopspreeng.sardart.Data.GridAdapter;

public class MainActivity extends AppCompatActivity {

    int REQUEST_IMAGE_CAPTURE = 2;

    String disasterType;

    GridView grid;
    String[] web = {
            "First Aid",
            "Domestic Accident",
            "Traffic Accident",
            "Terrorist Attack",
            "Crime Scene",
            "Natural Disaster"

    } ;
    int[] imageId = {
            R.drawable.police,
            R.drawable.ic_rowing_black_24dp,
            R.drawable.police,
            R.drawable.ic_rowing_black_24dp,
            R.drawable.police,
            R.drawable.ic_rowing_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*findViewById(R.id.place).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(MainActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
*/
        GridAdapter adapter = new GridAdapter(MainActivity.this, web, imageId);
        grid=(GridView)findViewById(R.id.em_list);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                disasterType = web[position];
                Toast.makeText(MainActivity.this, disasterType, Toast.LENGTH_SHORT).show();

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE){
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                extras.putString(getString(R.string.disasterType),disasterType);
               /* Bitmap imageBitmap = (Bitmap) extras.get("data");
                Bundle sendExtras = new Bundle();*/
                Intent intent = new Intent(this,DetailActivity.class);
                intent.putExtra(getString(R.string.disasterBundle),extras);

                startActivity(intent);

                //ImageView capture = (ImageView) findViewById(R.id.image_capture);
                //capture.setImageBitmap(imageBitmap);
            }
        }
    }
}
