package com.example.light_controller;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class AddColorButtonListener implements Button.OnClickListener
{
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    BluetoothMessageClass bluetoothMessage;
    public AddColorButtonListener(SeekBar redSeekBar, SeekBar greenSeekBar, SeekBar blueSeekBar, BluetoothMessageClass bluetoothMessage)
    {
        this.redSeekBar   = redSeekBar;
        this.greenSeekBar = greenSeekBar;
        this.blueSeekBar  = blueSeekBar;
        this.bluetoothMessage = bluetoothMessage;
    }

    @Override
    public void onClick(View v)
    {
        this.bluetoothMessage.AddColor(this.redSeekBar.getProgress(), this.greenSeekBar.getProgress(), this.blueSeekBar.getProgress());
        this.redSeekBar.setProgress(0);
        this.greenSeekBar.setProgress(0);
        this.blueSeekBar.setProgress(0);
    }
}
