package com.example.tnfdev.mileagetracker.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tnfdev.mileagetracker.R;
import com.example.tnfdev.mileagetracker.databinding.FragmentAddVehicleBinding;


public class AddVehicleFragment extends Fragment {

    private View mView;

    FragmentAddVehicleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DataBindingUtil.inflate(inflater,R.layout.fragment_add_vehicle, container, false);

        Button button=(Button)mView.findViewById(R.id.btn_add);
         Log.e("view","view");
        return  binding.getRoot();
    }


}
