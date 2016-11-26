package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.thetopfer.arduino_bluetooth.MainActivity;
import com.thetopfer.arduino_bluetooth.R;

public class OnOffFragment extends Fragment implements BluetoothFragment {

    Button On, Off;
    Switch OnOffSwitch;
    MainActivity mainActivity;
    TextView Status;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_onoff,container,false);
        mainActivity = ((MainActivity)getActivity());

        //call the widgets
        On = (Button) rootView.findViewById(R.id.on);
        Off = (Button) rootView.findViewById(R.id.off);
        OnOffSwitch = (Switch) rootView.findViewById(R.id.onoffswich);
        Status = (TextView) rootView.findViewById(R.id.textViewStatus);

        //commands to be sent to bluetooth
        On.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnOffSwitch.setChecked(true);   // set switch to ON state -> this calls onCheckedChanged listener set up bellow and sends "1" over Bluetooth
            }
        });

        Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnOffSwitch.setChecked(false);  // set switch to ON state -> this calls onCheckedChanged listener set up bellow and sends "0" over Bluetooth
            }
        });

        OnOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    mainActivity.SendBluetoothMessage("1");     //method to turn on
                }
                else
                {
                    mainActivity.SendBluetoothMessage("0");     //method to turn off
                }
            }
        });

        return rootView;
    }

    public void ReceiveBluetoothMessage(String message)
    {
        Status.setText(message);
    }
}
