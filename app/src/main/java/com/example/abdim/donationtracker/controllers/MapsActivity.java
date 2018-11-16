package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * MapsActivity
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final String TAG = "MapsActivity";

    private static final double LAT = 33.749007;
    private static final double LONG = -84.387632;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        //        LatLng sydney = new LatLng(-34, 151);
        //        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng atlanta = new LatLng(LAT, LONG);
        // Create a float variable called zoom and set the variable to your desired
        // initial zoom level. The following list gives you an idea of what level of detail
        // each level of zoom shows:
        //1: World
        //5: Landmass/continent
        //10: City
        //15: Streets
        //20: Buildings
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atlanta, 10));

        DatabaseReference locationsRef = FirebaseDatabase.getInstance().getReference("locations");
        locationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot locationShot : dataSnapshot.getChildren()) {
                    Location location = locationShot.getValue(Location.class);

                    double latitude = Objects.requireNonNull(location).getLatitude();
                    double longitude = Objects.requireNonNull(location).getLongitude();
                    // do something with the location
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(latitude, longitude))
                            .title(Objects.requireNonNull(location).getName())
                            .snippet("Phone: " + location.getPhoneNumber()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Failed to read value" + error.toException());
            }
        });


    }
    @Override
    public void onBackPressed()  {
        startActivity(new Intent(MapsActivity.this, LoggedInActivity.class));
        finish();
    }


}
