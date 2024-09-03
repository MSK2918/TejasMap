package com.example.ezmapactivity;


import android.os.Bundle;
import android.widget.Toast;
import android.widget.ZoomControls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.id_map);
        ZoomControls zoomControls = findViewById(R.id.zoomControls);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        zoomControls.setOnZoomInClickListener(v -> {
            if (googleMap != null) {
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        zoomControls.setOnZoomOutClickListener(v -> {
            if (googleMap != null) {
                googleMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {

        googleMap = map;

        // Navigate to a specific location
        LatLng location = new LatLng(15.596411015652633, 73.80524902152423);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));

        // Add a marker
        googleMap.addMarker(new MarkerOptions().position(location).title("Saraswat College"));

        // Enable zoom controls
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Set default map view type (e.g., satellite)
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Capture and display the location touched by the user
        googleMap.setOnMapClickListener(latLng -> {
            googleMap.addMarker(new MarkerOptions().position(latLng).title("Touched Location"));
            Toast.makeText(MainActivity.this, "Lat: " + latLng.latitude + ", Lng: " + latLng.longitude, Toast.LENGTH_SHORT).show();
        });
    }

}
