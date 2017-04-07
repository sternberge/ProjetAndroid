package com.ece.aurelien.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ece.aurelien.androidproject.Match.MatchView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker pos;
    private TextView latituteField;
    private TextView longitudeField;
    private Button savebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        latituteField = (TextView) findViewById(R.id.latitudeField);
        longitudeField = (TextView) findViewById(R.id.longitudeField);
        savebutton = (Button) findViewById(R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, MatchView.class);
                intent.putExtra("maplatitude",latituteField.getText());
                intent.putExtra("maplongitude",longitudeField.getText());
                startActivity(intent);
            }
        });

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

        // handle
        final LatLng Eceparis = new LatLng(48.85188369999999, 2.2863605999999663);
        final Marker[] pos = {mMap.addMarker(new MarkerOptions().position(Eceparis).title("ECE Paris"))};
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Eceparis));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                pos[0].remove();
                pos[0] = mMap.addMarker(new MarkerOptions().position(point).title("nouveau marqueur"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                latituteField.setText(String.valueOf(point.latitude));
                longitudeField.setText(String.valueOf(point.longitude));
            }
        });
    }
}
