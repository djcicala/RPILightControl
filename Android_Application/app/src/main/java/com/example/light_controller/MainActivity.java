package com.example.light_controller;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SeekBar redSlider;
    SeekBar greenSlider;
    SeekBar blueSlider;
    Button  addColorButton;
    Spinner lightTypeSpinner;
    Spinner frequencySpinner;
    Spinner numLEDSpinner;
    BluetoothMessageClass bluetoothMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redSlider        = (SeekBar) findViewById(R.id.seekBarRed);
        greenSlider      = (SeekBar) findViewById(R.id.seekBarGreen);
        blueSlider       = (SeekBar) findViewById(R.id.seekBarBlue);
        addColorButton   = (Button) findViewById(R.id.addColorButton);
        lightTypeSpinner = (Spinner) findViewById(R.id.lightType);
        frequencySpinner = (Spinner) findViewById(R.id.frequencySpinner);
        numLEDSpinner    = (Spinner) findViewById(R.id.numberLEDs);

        bluetoothMessage = new BluetoothMessageClass();

        /* color picker configuration */
        SeekBarListener localSeekBarListener = new SeekBarListener(addColorButton, redSlider, greenSlider, blueSlider);
        redSlider.setOnSeekBarChangeListener(localSeekBarListener);
        greenSlider.setOnSeekBarChangeListener(localSeekBarListener);
        blueSlider.setOnSeekBarChangeListener(localSeekBarListener);

        /* button press configuration  */
        AddColorButtonListener localButtonListener = new AddColorButtonListener(redSlider, greenSlider, blueSlider, bluetoothMessage);
        addColorButton.setOnClickListener(localButtonListener);

        /* spinner configuration */
        ArrayAdapter<CharSequence> lightTypeAdapter = ArrayAdapter.createFromResource(this, R.array.light_options, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> frequencyAdapter = ArrayAdapter.createFromResource(this, R.array.Frequency, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> numLEDAdapter = ArrayAdapter.createFromResource(this, R.array.numLEDs, android.R.layout.simple_spinner_dropdown_item);

        lightTypeSpinner.setAdapter(lightTypeAdapter);
        frequencySpinner.setAdapter(frequencyAdapter);
        numLEDSpinner.setAdapter(numLEDAdapter);
    }
}
