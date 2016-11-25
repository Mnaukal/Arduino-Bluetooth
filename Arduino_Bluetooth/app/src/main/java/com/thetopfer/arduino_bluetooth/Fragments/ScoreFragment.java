package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.thetopfer.arduino_bluetooth.R;

public class ScoreFragment extends Fragment {

    RadioButton min_exactly, min_percentage, min_zero, max_exactly, max_percentage;
    Button button;
    SeekBar seekBar_proportion, seekBar_count;
    EditText minExactlyEditText, minPercentageEditText, maxExactlyEditText, maxPercentageEditText;
    TextView proportion;

    TextView[] nameTexts = new TextView[5];

    EditText[] valTexts = new EditText[5];

    TextView[] linTexts = new TextView[5];
    TextView[] propTexts = new TextView[5];
    TextView[] combTexts = new TextView[5];

    float[] linear = new float[5];
    float[] proportional = new float[5];

    int decimals = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_onoff,container,false);

        /*min_exactly = (RadioButton)rootView.findViewById(R.id.radioButton_min_exactly);
        min_percentage = (RadioButton)rootView.findViewById(R.id.radioButton_min_percentage);
        min_zero = (RadioButton)rootView.findViewById(R.id.radioButton_min_zero);
        max_exactly = (RadioButton)rootView.findViewById(R.id.radioButton_max_exactly);
        max_percentage = (RadioButton)rootView.findViewById(R.id.radioButton_max_percentage);

        button = (Button)rootView.findViewById(R.id.button_score);
        seekBar_proportion = (SeekBar)rootView.findViewById(R.id.seekBar_proportion);
        seekBar_count = (SeekBar)rootView.findViewById(R.id.seekBar_count);
        proportion = (TextView)rootView.findViewById(R.id.textView_proportion);

        minExactlyEditText = (EditText)rootView.findViewById(R.id.editText_min_exactly);
        minPercentageEditText = (EditText)rootView.findViewById(R.id.editText_min_percentage);
        maxExactlyEditText = (EditText)rootView.findViewById(R.id.editText_max_exactly);
        maxPercentageEditText = (EditText)rootView.findViewById(R.id.editText_max_percentage);

        valTexts[0] = (EditText)rootView.findViewById(R.id.editText_score_1);
        valTexts[1] = (EditText)rootView.findViewById(R.id.editText_score_2);
        valTexts[2] = (EditText)rootView.findViewById(R.id.editText_score_3);
        valTexts[3] = (EditText)rootView.findViewById(R.id.editText_score_4);
        valTexts[4] = (EditText)rootView.findViewById(R.id.editText_score_5);

        linTexts[0] = (TextView)rootView.findViewById(R.id.text_lin_1);
        linTexts[1] = (TextView)rootView.findViewById(R.id.text_lin_2);
        linTexts[2] = (TextView)rootView.findViewById(R.id.text_lin_3);
        linTexts[3] = (TextView)rootView.findViewById(R.id.text_lin_4);
        linTexts[4] = (TextView)rootView.findViewById(R.id.text_lin_5);

        propTexts[0] = (TextView)rootView.findViewById(R.id.text_prop_1);
        propTexts[1] = (TextView)rootView.findViewById(R.id.text_prop_2);
        propTexts[2] = (TextView)rootView.findViewById(R.id.text_prop_3);
        propTexts[3] = (TextView)rootView.findViewById(R.id.text_prop_4);
        propTexts[4] = (TextView)rootView.findViewById(R.id.text_prop_5);

        combTexts[0] = (TextView)rootView.findViewById(R.id.text_comb_1);
        combTexts[1] = (TextView)rootView.findViewById(R.id.text_comb_2);
        combTexts[2] = (TextView)rootView.findViewById(R.id.text_comb_3);
        combTexts[3] = (TextView)rootView.findViewById(R.id.text_comb_4);
        combTexts[4] = (TextView)rootView.findViewById(R.id.text_comb_5);

        nameTexts[0] = (TextView)rootView.findViewById(R.id.textView_name_1);
        nameTexts[1] = (TextView)rootView.findViewById(R.id.textView_name_2);
        nameTexts[2] = (TextView)rootView.findViewById(R.id.textView_name_3);
        nameTexts[3] = (TextView)rootView.findViewById(R.id.textView_name_4);
        nameTexts[4] = (TextView)rootView.findViewById(R.id.textView_name_5);

        min_exactly.setOnClickListener(min_exactly_listener);
        min_percentage.setOnClickListener(min_percentage_listener);
        min_zero.setOnClickListener(min_zero_listener);
        max_exactly.setOnClickListener(max_exactly_listener);
        max_percentage.setOnClickListener(max_percentage_listener);

        button.setOnClickListener(button_click_listener);
        seekBar_proportion.setOnSeekBarChangeListener(seekBar_proportion_change_listener);
        seekBar_count.setOnSeekBarChangeListener(seekBar_count_change_listener);

        proportion.setText(getString(R.string.proportion) + ": 50%");*/

        return rootView;
    }
/*
    // MIN radio buttons
    View.OnClickListener min_exactly_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            min_exactly.setChecked(true);
            getView().findViewById(R.id.editText_min_exactly).setEnabled(true);
            min_percentage.setChecked(false);
            getView().findViewById(R.id.editText_min_percentage).setEnabled(false);
            min_zero.setChecked(false);
        }
    };

    View.OnClickListener min_percentage_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            min_percentage.setChecked(true);
            getView().findViewById(R.id.editText_min_percentage).setEnabled(true);
            min_exactly.setChecked(false);
            getView().findViewById(R.id.editText_min_exactly).setEnabled(false);
            min_zero.setChecked(false);
        }
    };

    View.OnClickListener min_zero_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            min_zero.setChecked(true);
            min_percentage.setChecked(false);
            getView().findViewById(R.id.editText_min_percentage).setEnabled(false);
            min_exactly.setChecked(false);
            getView().findViewById(R.id.editText_min_exactly).setEnabled(false);
        }
    };

    // MAX radio buttons
    View.OnClickListener max_exactly_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            max_exactly.setChecked(true);
            getView().findViewById(R.id.editText_max_exactly).setEnabled(true);
            max_percentage.setChecked(false);
            getView().findViewById(R.id.editText_max_percentage).setEnabled(false);
        }
    };

    View.OnClickListener max_percentage_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            max_percentage.setChecked(true);
            getView().findViewById(R.id.editText_max_percentage).setEnabled(true);
            max_exactly.setChecked(false);
            getView().findViewById(R.id.editText_max_exactly).setEnabled(false);
        }
    };

    // button
    Button.OnClickListener button_click_listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                int pocet = seekBar_count.getProgress();

                Value[] values = new Value[pocet];

                for (int i = 0; i < pocet; i++) {
                    values[i] = new Value(Integer.parseInt(valTexts[i].getText().toString()), i);
                }

                Arrays.sort(values);

                float max = Integer.parseInt(maxExactlyEditText.getText().toString());
                float min = Integer.parseInt(minExactlyEditText.getText().toString());

                if (max_percentage.isChecked())
                    max = Integer.parseInt(maxPercentageEditText.getText().toString()) * values[pocet - 1].VALUE / 100f;

                if (min_percentage.isChecked())
                    min = Integer.parseInt(minPercentageEditText.getText().toString()) * max / 100f;

                if (min_zero.isChecked()) {
                    float ratio = max / values[pocet - 1].VALUE;
                    min = values[0].VALUE * ratio;
                    float interval = max - min;
                    float difference = values[pocet - 1].VALUE - values[0].VALUE;

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", 0);
                    decimals = sharedPreferences.getInt("decimals", 2);
                    float roundPower = (float) Math.pow(10, decimals);

                    for (int i = 0; i < pocet; i++) {
                        linear[values[i].INDEX] = Math.round((i * interval / (pocet - 1) + min) * roundPower) / roundPower;
                        proportional[values[i].INDEX] = Math.round(values[i].VALUE * ratio * roundPower) / roundPower;
                    }
                }
                else {
                    float interval = max - min;
                    float difference = values[pocet - 1].VALUE - values[0].VALUE;

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", 0);
                    decimals = sharedPreferences.getInt("decimals", 2);
                    float roundPower = (float) Math.pow(10, decimals);

                    for (int i = 0; i < pocet; i++) {
                        linear[values[i].INDEX] = Math.round((i * interval / (pocet - 1) + min) * roundPower) / roundPower;
                        proportional[values[i].INDEX] = Math.round(((values[i].VALUE - values[0].VALUE) * interval / difference + min) * roundPower) / roundPower;
                    }
                }

                for (int i = 0; i < pocet; i++) {
                    linTexts[i].setText(String.format("%." + decimals + "f", linear[i]));
                    propTexts[i].setText(String.format("%." + decimals + "f", proportional[i]));
                }
                seekBar_proportion.incrementProgressBy(1);
                seekBar_proportion.incrementProgressBy(-1);
            }
            catch (Exception e)
            {
                Snackbar.make(v, getString(R.string.error), Snackbar.LENGTH_LONG)
                        .setAction("Chyba", null)
                        .show();
            }
        }
    };

    //seek bar proportion
    SeekBar.OnSeekBarChangeListener seekBar_proportion_change_listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            for (int i = 0; i < 5; i++) {
                float value = proportional[i] * progress / 100f + linear[i] * (1 - progress / 100f);
                combTexts[i].setText(String.format("%." + decimals + "f", value));
            }
            proportion.setText(getString(R.string.proportion) + ": " + progress + "%");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    //seek bar count
    SeekBar.OnSeekBarChangeListener seekBar_count_change_listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            for (int i = 0; i < progress; i++)
            {
                valTexts[i].setEnabled(true);
                nameTexts[i].setTextColor(getResources().getColor(R.color.material_dark));
                linTexts[i].setTextColor(getResources().getColor(R.color.material_dark));
                propTexts[i].setTextColor(getResources().getColor(R.color.material_dark));
                combTexts[i].setTextColor(getResources().getColor(R.color.material_dark));
            }
            for (int i = progress; i < 5; i++)
            {
                valTexts[i].setEnabled(false);
                nameTexts[i].setTextColor(getResources().getColor(R.color.disabled_text));
                linTexts[i].setTextColor(getResources().getColor(R.color.disabled_text));
                propTexts[i].setTextColor(getResources().getColor(R.color.disabled_text));
                combTexts[i].setTextColor(getResources().getColor(R.color.disabled_text));
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };*/
}

class Value implements Comparable<Value> {
    public float VALUE;
    public int INDEX;

    public Value(float val, int index)
    {
        VALUE = val;
        INDEX = index;
    }

    public Value() {}

    @Override
    public int compareTo(Value other)
    {
        return Float.compare(VALUE, other.VALUE);
    }
}
