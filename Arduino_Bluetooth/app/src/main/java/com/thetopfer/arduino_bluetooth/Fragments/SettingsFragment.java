package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.thetopfer.arduino_bluetooth.R;

public class SettingsFragment extends Fragment {

    EditText RefreshRate;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings,container,false);

        sharedPreferences = getActivity().getSharedPreferences("settings", 0);

        RefreshRate = (EditText) rootView.findViewById(R.id.edittext_refresh_rate);

        int refreshRate = sharedPreferences.getInt("refresh_rate", 100);
        RefreshRate.setText(String.valueOf(refreshRate));

        /*RefreshRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {*/


        RefreshRate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // When focus is lost check that the text field has valid values.
                if (!hasFocus) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    int decimalsInt = Integer.parseInt(RefreshRate.getText().toString());
                    decimalsInt = Math.max(Math.min(decimalsInt, 10000), 0);
                    RefreshRate.setText(String.valueOf(decimalsInt));
                    editor.putInt("refresh_rate", decimalsInt);
                    editor.apply();
                }
            }
        });

            /*}
            @Override
            public void afterTextChanged(Editable s) {}
        });*/

        return rootView;
    }
}
