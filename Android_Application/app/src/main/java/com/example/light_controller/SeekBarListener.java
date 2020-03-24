package com.example.light_controller;

import android.graphics.Color;
import android.widget.Button;
import android.widget.SeekBar;

public class SeekBarListener implements SeekBar.OnSeekBarChangeListener
{

    Button  addColorButton;
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;

    public SeekBarListener(Button addColorButton, SeekBar redSeekBar, SeekBar greenSeekBar, SeekBar blueSeekBar)
    {
        this.addColorButton = addColorButton;
        this.redSeekBar     = redSeekBar;
        this.greenSeekBar   = greenSeekBar;
        this.blueSeekBar    = blueSeekBar;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        this.addColorButton.setBackgroundColor(Color.rgb(this.redSeekBar.getProgress(), this.greenSeekBar.getProgress(), this.blueSeekBar.getProgress()));

        /* should probably tune this later, but text should be white when sufficiently dark, and black when sufficiently light */
        if(this.redSeekBar.getProgress() < 100 || this.greenSeekBar.getProgress() < 100 || this.blueSeekBar.getProgress() < 100)
        {
            this.addColorButton.setTextColor(Color.rgb(255,255,255));
        }
        else
        {
            this.addColorButton.setTextColor(Color.rgb(0,0,0));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {

    }
}
