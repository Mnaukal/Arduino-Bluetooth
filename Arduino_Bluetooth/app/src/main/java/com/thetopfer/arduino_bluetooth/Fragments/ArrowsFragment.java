package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.thetopfer.arduino_bluetooth.MainActivity;
import com.thetopfer.arduino_bluetooth.R;

public class ArrowsFragment extends Fragment implements BluetoothFragment {

    ImageButton Up, Down, Left, Right;
    MainActivity mainActivity;
    TextView Status;
    // used for sending less messages to Arduino from onProgressChanged on seekBars
    long lastSentTime = 0;
    int TIMEBETWEENSENDS = 100;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arrows,container,false);
        mainActivity = ((MainActivity)getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("settings", 0);
        TIMEBETWEENSENDS = sharedPreferences.getInt("refresh_rate", 100);

        //call the widgets
        Up = (ImageButton) rootView.findViewById(R.id.button_up);
        Down = (ImageButton) rootView.findViewById(R.id.button_down);
        Left = (ImageButton) rootView.findViewById(R.id.button_left);
        Right = (ImageButton) rootView.findViewById(R.id.button_right);
        Status = (TextView) rootView.findViewById(R.id.textViewStatus);

        ((FloatingActionButton) rootView.findViewById(R.id.fab_a)).setOnTouchListener(onTouchListener);
        ((FloatingActionButton) rootView.findViewById(R.id.fab_b)).setOnTouchListener(onTouchListener);
        ((FloatingActionButton) rootView.findViewById(R.id.fab_c)).setOnTouchListener(onTouchListener);
        ((FloatingActionButton) rootView.findViewById(R.id.fab_x)).setOnTouchListener(onTouchListener);
        ((FloatingActionButton) rootView.findViewById(R.id.fab_y)).setOnTouchListener(onTouchListener);
        ((FloatingActionButton) rootView.findViewById(R.id.fab_z)).setOnTouchListener(onTouchListener);

        Up.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                long currentTime = System.currentTimeMillis();
                if(currentTime - lastSentTime >= TIMEBETWEENSENDS) {
                    lastSentTime = currentTime;
                    mainActivity.SendBluetoothMessage("U");
                }

                return true;
            }
        });

        Down.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                long currentTime = System.currentTimeMillis();
                if(currentTime - lastSentTime >= TIMEBETWEENSENDS) {
                    lastSentTime = currentTime;
                    mainActivity.SendBluetoothMessage("D");
                }

                return true;
            }
        });

        Left.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                long currentTime = System.currentTimeMillis();
                if(currentTime - lastSentTime >= TIMEBETWEENSENDS) {
                    lastSentTime = currentTime;
                    mainActivity.SendBluetoothMessage("L");
                }

                return true;
            }
        });

        Right.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                long currentTime = System.currentTimeMillis();
                if(currentTime - lastSentTime >= TIMEBETWEENSENDS) {
                    lastSentTime = currentTime;
                    mainActivity.SendBluetoothMessage("R");
                }

                return true;
            }
        });

        return rootView;
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastSentTime >= TIMEBETWEENSENDS) {
                lastSentTime = currentTime;
                mainActivity.SendBluetoothMessage(v.getTag().toString());
            }

            return true;
        }
    };

    public void ReceiveBluetoothMessage(String message)
    {
        Status.setText(message);
    }
}
