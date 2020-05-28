package com.example.miniproject;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE = 11;
    private GoogleMap mMap;
    private SupportMapFragment supportMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //check permission
        ActivityCompat.requestPermissions(MapsActivity.this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        GpsApp gpsApp = new GpsApp(getApplicationContext());
        Location location = gpsApp.getLocation();
        if (location != null) {
            double lat = location.getLatitude();
            double longi = location.getLongitude();

            // Add a marker in Current Location and move the camera
            LatLng latLng = new LatLng(lat, longi);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Current location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        }
    }
}