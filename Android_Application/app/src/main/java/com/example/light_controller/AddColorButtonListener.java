/*************************************************************************/
/*  File Name:  AddColorButtonListener.java                              */
/*  Purpose:    Listener for the "add color to list" button.             */
/*  Created by: Darren Cicala on 21/03/20.                               */
/*  Copyright © 2020 Darren Cicala, Joe Benczarski, Dan Beier.           */
/*  All rights reserved.                                                 */
/*************************************************************************/
package com.example.light_controller;

/******************************* imports *********************************/
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class AddColorButtonListener implements Button.OnClickListener
{
    SeekBar redSeekBar, greenSeekBar, blueSeekBar; // local references of the color picker seekbars
    BluetoothMessageClass bluetoothMessage;        // local reference of the bluetooth message to send

    /* constructor, creates local references of passed-in variables */
    public AddColorButtonListener(SeekBar redSeekBar, SeekBar greenSeekBar, SeekBar blueSeekBar, BluetoothMessageClass bluetoothMessage)
    {
        this.redSeekBar   = redSeekBar;
        this.greenSeekBar = greenSeekBar;
        this.blueSeekBar  = blueSeekBar;
        this.bluetoothMessage = bluetoothMessage;
    }

    /* called when the "add color" button is pressed */
    @Override
    public void onClick(View v)
    {
        /* add the color to the list of colors */
        this.bluetoothMessage.AddColor(this.redSeekBar.getProgress(), this.greenSeekBar.getProgress(), this.blueSeekBar.getProgress());

        /* reset the sliders to 0 */
        this.redSeekBar.setProgress(0);
        this.greenSeekBar.setProgress(0);
        this.blueSeekBar.setProgress(0);
    }
}
