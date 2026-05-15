package com.luu.tpinmobiliaria.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment implements OnMapReadyCallback {

    private FragmentInicioBinding binding;
    private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInicioBinding.inflate(inflater, container, false);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return binding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng ulp = new LatLng(-33.150783, -66.320339);

        mMap.addMarker(new MarkerOptions()
                .position(ulp)
                .title("Universidad de La Punta"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ulp, 17));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}