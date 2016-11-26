package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.thetopfer.arduino_bluetooth.MainActivity;
import com.thetopfer.arduino_bluetooth.R;

public class rgbLedFragment extends Fragment implements BluetoothFragment {

    SeekBar Rbar, Gbar, Bbar;
    TextView Status;
    // used for sending less messages to Arduino from onProgressChanged on seekBars
    int limitR = 0, limitG = 0, limitB = 0;
    int LIMIT = 9;

    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rgb,container,false);
        mainActivity = ((MainActivity)getActivity());

        //call the widgets
        Rbar = (SeekBar) rootView.findViewById(R.id.seekBarR);
        Gbar = (SeekBar) rootView.findViewById(R.id.seekBarG);
        Bbar = (SeekBar) rootView.findViewById(R.id.seekBarB);
        Status = (TextView) rootView.findViewById(R.id.textViewStatus);

        //commands to be sent to bluetooth
        Rbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mainActivity.SendBluetoothMessage(seekBar.getProgress() + "R");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(limitR >= LIMIT) {
                    limitR = 0;
                    mainActivity.SendBluetoothMessage(progress + "R");
                }
                else
                    limitR++;
            }
        });

        Gbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mainActivity.SendBluetoothMessage(seekBar.getProgress() + "G");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(limitG >= LIMIT) {
                    limitG = 0;
                    mainActivity.SendBluetoothMessage(progress + "G");
                }
                else
                    limitG++;
            }
        });

        Bbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mainActivity.SendBluetoothMessage(seekBar.getProgress() + "B");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(limitB >= LIMIT) {
                    limitB = 0;
                    mainActivity.SendBluetoothMessage(progress + "B");
                }
                else
                    limitB++;
            }
        });

        return rootView;
    }

    public void ReceiveBluetoothMessage(String message)
    {
        Status.setText(message);
    }
}
