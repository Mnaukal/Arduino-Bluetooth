package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import com.thetopfer.arduino_bluetooth.MainActivity;
import com.thetopfer.arduino_bluetooth.R;

public class OnOffFragment extends Fragment {

    Button On, Off;
    Switch OnOffSwitch;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_onoff,container,false);
        mainActivity = ((MainActivity)getActivity());

        //call the widgets
        On = (Button) rootView.findViewById(R.id.on);
        Off = (Button) rootView.findViewById(R.id.off);
        OnOffSwitch = (Switch) rootView.findViewById(R.id.onoffswich);

        //commands to be sent to bluetooth
        On.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.turnOnLed();      //method to turn on
                OnOffSwitch.setChecked(true);
            }
        });

        Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.turnOffLed();   //method to turn off
                OnOffSwitch.setChecked(false);
            }
        });

        OnOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    mainActivity.turnOnLed();
                }
                else
                {
                    mainActivity.turnOffLed();
                }
            }
        });

        return rootView;
    }
}
