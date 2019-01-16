package com.example.corne.sportbuddy;

import android.content.Context;
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
import android.widget.Toast;

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
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted;
    private GoogleMap mMap;
    private Location mLastKnownLocation;
    private Location mPreviousKnownLocation;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private List<LatLng> coordinatesList = new ArrayList<LatLng>();
    private int distance;
    long time = 30000;
    CountDownTimer timer = null;
    long millileft;
    private int counter = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        final TextView textField = findViewById(R.id.textView7);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Create timer which can start, pause and resume and warns user when done
        final Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().equals("Start Activity")){
                        button.setText("Pause");
                        timer = new CountDownTimer(time, 1000) {
                            public void onTick(long millisUntilFinished) {
                                textField.setText(millisUntilFinished / 60000 + ":" + (millisUntilFinished % 60000) / 1000 +" Left");
                                millileft = millisUntilFinished;
                            }

                            public void onFinish() {
                                button.setVisibility(View.INVISIBLE);
                                textField.setText("done!");
                                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                v.vibrate(2000);
                                //Toast.makeText(MapsActivity.this, String.valueOf(distance), Toast.LENGTH_LONG).show();
                            }
                        }.start();

                    } else if (button.getText().equals("Pause")){
                        button.setText("Resume");
                        timer.cancel();

                    } else if(button.getText().equals("Resume")){
                        button.setText("Pause");
                        timer = new CountDownTimer(millileft, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                textField.setText(millisUntilFinished / 60000 + ":" + (millisUntilFinished % 60000) / 1000 + " Left");
                                millileft = millisUntilFinished;
                            }

                            @Override
                            public void onFinish() {
                                button.setVisibility(View.INVISIBLE);
                                textField.setText("done!");
                                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                v.vibrate(2000);
                                //Toast.makeText(MapsActivity.this, String.valueOf(distance), Toast.LENGTH_LONG).show();
                            }
                        }.start();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        final Button button = findViewById(R.id.button5);

        // Create handler to keep updating location
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

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
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

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            counter ++;
                            if (counter > 1){
                                mPreviousKnownLocation = mLastKnownLocation;
                                mLastKnownLocation = (Location) task.getResult();
                            } else {
                                mPreviousKnownLocation = (Location) task.getResult();
                                mLastKnownLocation = mPreviousKnownLocation;
                            }
                            distance += mPreviousKnownLocation.distanceTo(mLastKnownLocation);
                            LatLng temp = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                            coordinatesList.add(temp);
                            if (coordinatesList.size() > 1) {
                                distance += mLastKnownLocation.distanceTo(mPreviousKnownLocation);
                                mPreviousKnownLocation = mLastKnownLocation;
                            }
                            //ToDo: Connect all the different markers to get route
                            String printableCoordinates = temp.toString();
                            Log.e("Developers", String.valueOf(coordinatesList.size()));
                            Log.e("Developers", printableCoordinates);
                            Log.e("Developers", String.valueOf(distance));

                            mMap.addMarker(new MarkerOptions().position(temp).title(String.valueOf(distance)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
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
