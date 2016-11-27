package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
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
    long lastSentTime = 0;
    int TIMEBETWEENSENDS = 100;

    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rgb,container,false);
        mainActivity = ((MainActivity)getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("settings", 0);
        TIMEBETWEENSENDS = sharedPreferences.getInt("refresh_rate", 100);

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
                long currentTime = System.currentTimeMillis();
                if(currentTime - lastSentTime >= TIMEBETWEENSENDS) {
                    lastSentTime = currentTime;
                    mainActivity.SendBluetoothMessage(progress + "R");
                }
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
                long currentTime = System.currentTimeMillis();
                if(currentTime - lastSentTime >= TIMEBETWEENSENDS) {
                    lastSentTime = currentTime;
                    mainActivity.SendBluetoothMessage(progress + "G");
                }
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
                long currentTime = System.currentTimeMillis();
                if(currentTime - lastSentTime >= TIMEBETWEENSENDS) {
                    lastSentTime = currentTime;
                    mainActivity.SendBluetoothMessage(progress + "B");
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
