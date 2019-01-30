package com.example.corne.sportbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted;
    private GoogleMap mMap;
    private Location mLastKnownLocation;
    private Location mPreviousKnownLocation;
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085); //start value
    private static final int DEFAULT_ZOOM = 15;
    private List<LatLng> coordinatesList = new ArrayList<>();
    private int distance;
    long time = 30000;
    CountDownTimer timer = null;
    long millileft;
    private int counter = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Get intent from previous activities
        Intent gotIntent = getIntent();
        int duration = (int) gotIntent.getSerializableExtra("duration");
        duration = 60000 * duration;
        time = (long) duration;

        final TextView textField = findViewById(R.id.textView7);

        // Create a mapFragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Set startButton to influence timer
        final Button button = findViewById(R.id.button5);
        final float finalDuration = duration;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().equals("Start")){
                        button.setText("Pause");

                        // Create a timer every time timer is started or resumed
                        timer = new CountDownTimer(time, 1000) {

                            // Update the timer every second to display current time in hours, minutes and seconds
                            public void onTick(long millisUntilFinished) {
                                if (millisUntilFinished < 60000){
                                    textField.setText(millisUntilFinished / 1000 + "s Left");
                                } else if (millisUntilFinished < 3600000) {
                                    textField.setText(millisUntilFinished / 60000 + "m:" + (millisUntilFinished % 60000) / 1000 +"s Left");
                                } else {
                                    textField.setText(millisUntilFinished / 3600000 + "h:" + (millisUntilFinished % 3600000) / 60000 + "m:" + (millisUntilFinished % 60000) / 1000 + "s Left");
                                }
                                millileft = millisUntilFinished;
                            }

                            // Vibrate the phone and change the text when timer reaches 0
                            public void onFinish() {
                                button.setText("Overview");
                                textField.setText("done!");
                                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                v.vibrate(2000);

                                // Update the start/pause button
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(MapsActivity.this, OverviewActivity.class);
                                        intent.putExtra("Duration", finalDuration);
                                        intent.putExtra("Distance", distance);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }.start();

                    // To get the timer paused
                    } else if (button.getText().equals("Pause")){
                        button.setText("Resume");
                        timer.cancel();

                    // To get the timer resumed, set it back as started an perform virtualClick
                    } else if(button.getText().equals("Resume")){
                    button.setText("Start");
                    button.performClick();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        final Button button = findViewById(R.id.button5);

        // Create handler to update location every 5 seconds
        final Handler handler = new Handler();
        final int delay = 5000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                    // Turn on the My Location layer and the related control on the map.
                    updateLocationUI();

                    // Get the current location of the device and set the position of the map.
                    getDeviceLocation();
                    handler.postDelayed(this, delay);
            }
        }, delay);
    }

    // method that checks for permission and asks if it is not granted yet
    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    // Method that updates location
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    // Method that searches location and updates setting
    private void getDeviceLocation() {
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {

                            // Set the map's camera position to the current location of the device.
                            counter ++;

                            // updates location and keep previous
                            if (counter > 1){
                                mPreviousKnownLocation = mLastKnownLocation;
                                mLastKnownLocation = (Location) task.getResult();

                            // Sets both to the same location on first cycle
                            } else {
                                mPreviousKnownLocation = (Location) task.getResult();
                                mLastKnownLocation = mPreviousKnownLocation;
                            }

                            // Update location and calculate total distance by adding difference to sub
                            distance += mPreviousKnownLocation.distanceTo(mLastKnownLocation);
                            LatLng temp = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                            coordinatesList.add(temp);
                            if (coordinatesList.size() > 1) {
                                distance += mLastKnownLocation.distanceTo(mPreviousKnownLocation);
                                mPreviousKnownLocation = mLastKnownLocation;
                            }

                            // Add a marker to the current location to show movement and give distance in description
                            mMap.addMarker(new MarkerOptions().position(temp).title(String.valueOf(distance)));

                            // Move the camera to current location the first time it is updated
                            if(counter < 2){
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(mLastKnownLocation.getLatitude(),
                                                mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            }
                        } else {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}
