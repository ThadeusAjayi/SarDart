package com.shopspreeng.sardart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class DetailActivity extends AppCompatActivity {

    int REQUEST_VIDEO_CAPTURE = 10;
    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra(getString(R.string.disasterBundle))) {
            Bundle det = getIntent().getExtras();
            Bundle deta =(Bundle) det.get(getString(R.string.disasterBundle));
            Log.v("Bundle Data",String.valueOf(deta.get(getString(R.string.disasterType))));

            Bitmap imageBitmap = (Bitmap) deta.get("data");
            ImageView capture = (ImageView) findViewById(R.id.emergency_image);
            capture.setImageBitmap(imageBitmap);

            TextView disasterType = (TextView) findViewById(R.id.disaster);
            disasterType.setText(String.valueOf(deta.get(getString(R.string.disasterType))));
        }

        findViewById(R.id.add_media).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);

                }
            }
        });

        findViewById(R.id.location_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(DetailActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_VIDEO_CAPTURE){
            if (resultCode == RESULT_OK) {
                Uri videoUri = data.getData();
                VideoView video =(VideoView) findViewById(R.id.emergency_video);
                video.setVideoURI(videoUri);
            } else {
                Log.v("Problem", "resultCode == RESULT_ERROR");
            }
        }

        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this,data);
                TextView tv = (TextView) findViewById(R.id.location_details);
                tv.setText(place.getName() +"\n" + place.getAddress() + "\n" + place.getLatLng());
            } else {
                Log.v("Problem", "resultCode == RESULT_ERROR");
            }
        }
    }
}
