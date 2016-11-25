package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.thetopfer.arduino_bluetooth.MainActivity;
import com.thetopfer.arduino_bluetooth.R;

import java.util.UUID;

public class OnOffFragment extends Fragment {

    // Button btnOn, btnOff, btnDis;
    ImageButton On, Off, Discnt, Abt, Drawer;
    SeekBar Rbar, Gbar, Bbar;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_onoff,container,false);
        mainActivity = ((MainActivity)getActivity());

        //call the widgets
        On = (ImageButton) rootView.findViewById(R.id.on);
        Off = (ImageButton) rootView.findViewById(R.id.off);
        Rbar = (SeekBar) rootView.findViewById(R.id.seekBarR);
        Gbar = (SeekBar) rootView.findViewById(R.id.seekBarG);
        Bbar = (SeekBar) rootView.findViewById(R.id.seekBarB);

        //commands to be sent to bluetooth
        On.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.turnOnLed();      //method to turn on
            }
        });

        Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.turnOffLed();   //method to turn off
            }
        });

        Rbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mainActivity.SendBTmessage(progress + "R");
            }
        });

        Gbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mainActivity.SendBTmessage(progress + "G");
            }
        });

        Bbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mainActivity.SendBTmessage(progress + "B");
            }
        });

        return rootView;
    }
}
