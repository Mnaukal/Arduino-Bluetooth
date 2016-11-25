package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.thetopfer.arduino_bluetooth.R;

/**
 * Created by Topfer on 05.03.2016.
 */
public class SettingsFragment extends Fragment {

    SeekBar decimals;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings,container,false);

        sharedPreferences = getActivity().getSharedPreferences("prefs", 0);

        /*decimals = (SeekBar) rootView.findViewById(R.id.seekBar_settings_decimals);

        int decimalsInt = sharedPreferences.getInt("decimals", 2);

        decimals.setProgress(decimalsInt);

        decimals.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                int decimalsInt = progress;

                editor.putInt("decimals", decimalsInt);
                editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/

        return rootView;
    }
}
