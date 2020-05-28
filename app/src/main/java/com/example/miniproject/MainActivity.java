package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private static final int REQUEST_CODE = 11;
    private TextView tvLocation;
    private Button btnLatlng, btnCrrentLocation;
    private LocationManager locationManager;
    private double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLocation = (TextView) findViewById(R.id.tvLocation);
        btnLatlng = (Button) findViewById(R.id.btn_Location);
        btnCrrentLocation = (Button) findViewById(R.id.btn_CurrentLocation);

        //check permission
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        btnCrrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        btnLatlng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new project
                GpsApp gpsApp = new GpsApp(getApplicationContext());
                Location location = gpsApp.getLocation();
                if (location != null) {
                    double lat = location.getLatitude();
                    double longi = location.getLongitude();
                    tvLocation.setText("Latitude: " + lat + "\n" + "Longitude: " + longi);
                }

            }
        });
    }

}
