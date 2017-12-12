package com.example.user.appforrouting;

import
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class AddNewRoute extends AppCompatActivity implements OnClickListener {

    Button build;
    public static GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        build = (Button) findViewById(R.id.build);

        setContentView(R.layout.activity_add_new_route);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.build)
        {
            PolylineOptions line = new PolylineOptions();
            line.width(4f).color(R.color.indigo_900);
            LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();
            for (int i = 0; i < mPoints.size(); i++) {
                if (i == 0) {
                    MarkerOptions startMarkerOptions = new MarkerOptions()
                            .position(mPoints.get(i))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_a));
                    mMap.addMarker(startMarkerOptions);
                } else if (i == mPoints.size() - 1) {
                    MarkerOptions endMarkerOptions = new MarkerOptions()
                            .position(mPoints.get(i))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_b));
                    mMap.addMarker(endMarkerOptions);
                }
                line.add(mPoints.get(i));
                latLngBuilder.include(mPoints.get(i));
            }
            mMap.addPolyline(line);
            int size = getResources().getDisplayMetrics().widthPixels;
            LatLngBounds latLngBounds = latLngBuilder.build();
            CameraUpdate track = CameraUpdateFactory.newLatLngBounds(latLngBounds, size, size, 25);
            mMap.moveCamera(track);

        }

    }
}
