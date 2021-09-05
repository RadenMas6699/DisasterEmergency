package com.radenmas.disaster_emergency.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.radenmas.disaster_emergency.R;

public class UserMapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            DatabaseReference dbLoc = FirebaseDatabase.getInstance().getReference().child("Laporan");
            dbLoc.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String lat = snapshot.child("Lattitude").getValue().toString();
                    String lon = snapshot.child("Long").getValue().toString();

//                    String lat = "-5.184407556443631";
//                    String lon = "119.42990521273074";

                    float lattt = Float.parseFloat(lat);
                    float longg = Float.parseFloat(lon);

                    LatLng askara = new LatLng(lattt, longg);
                    float zoomLevel = 14.0f; //This goes up to 21
                    googleMap.addMarker(new MarkerOptions().position(askara).title("ASKARA"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(askara, zoomLevel));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


//            LatLng sydney = new LatLng(-5.184407556443631, 119.42990521273074);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("ASKARA"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}