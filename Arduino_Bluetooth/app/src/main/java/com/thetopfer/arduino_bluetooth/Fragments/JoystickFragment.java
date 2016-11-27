package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.thetopfer.arduino_bluetooth.JoyStickClass;
import com.thetopfer.arduino_bluetooth.MainActivity;
import com.thetopfer.arduino_bluetooth.R;

public class JoystickFragment extends Fragment implements BluetoothFragment {

    MainActivity mainActivity;
    TextView Status, Joystick;
    RelativeLayout layout_joystick;
    JoyStickClass js;
    // used for sending less messages to Arduino from onProgressChanged on seekBars
    long lastSentTime = 0;
    int TIMEBETWEENSENDS = 100;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joystick,container,false);
        mainActivity = ((MainActivity)getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("settings", 0);
        TIMEBETWEENSENDS = sharedPreferences.getInt("refresh_rate", 100);

        //call the widgets
        Status = (TextView) rootView.findViewById(R.id.textViewStatus);
        Joystick = (TextView) rootView.findViewById(R.id.textViewJoystick);
        layout_joystick = (RelativeLayout) rootView.findViewById(R.id.layout_joystick);

        js = new JoyStickClass(getActivity().getApplicationContext()
                , layout_joystick, R.drawable.joystick_thumb);
        js.setStickSize(220, 220);
        js.setLayoutSize(500, 500);
        js.setLayoutAlpha(255);
        js.setStickAlpha(255);
        js.setOffset(90);
        js.setMinimumDistance(20);

        layout_joystick.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                //if(arg1.getAction() == MotionEvent.ACTION_DOWN || arg1.getAction() == MotionEvent.ACTION_MOVE)

                Joystick.setText(String.format("%.3f", getClampedX()) + "," + String.format("%.3f", getClampedY()) + "\n" + String.format("%.0f", js.getAngle()) + ";" + String.format("%.3f", getClampedDistance()));

                long currentTime = System.currentTimeMillis();
                if(currentTime - lastSentTime >= TIMEBETWEENSENDS) {
                    lastSentTime = currentTime;
                    // TODO send message via bluetooth
                    //mainActivity.SendBluetoothMessage("UP");
                }

                return true;
            }
        });

        return rootView;
    }

    private float getClampedX()    {
        float x = js.getX();
        x = Math.max(Math.min(x, 200), -200);
        x /= 200f;
        return x;
    }

    private float getClampedY()    {
        float y = -js.getY();
        y = Math.max(Math.min(y, 200), -200);
        y /= 200f;
        return y;
    }

    private float getClampedDistance()    {
        float p = js.getDistance();
        p = Math.max(Math.min(p, 200), 0);
        p /= 200f;
        return p;
    }

    public void ReceiveBluetoothMessage(String message)
    {
        Status.setText(message);
    }
}
