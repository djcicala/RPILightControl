/*************************************************************************/
/*  File Name:  SendToRPIButtonListener.java                             */
/*  Purpose:    Listener for the "send to RPI" button.                   */
/*  Created by: Darren Cicala on 21/03/20.                               */
/*  Copyright © 2020 Darren Cicala, Joe Benczarski, Dan Beier.           */
/*  All rights reserved.                                                 */
/*************************************************************************/
package com.example.light_controller;

/******************************* imports *********************************/
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SendToRPIButtonListener implements Button.OnClickListener
{
    OutputStream os;  // output bluetooth stream, sent over from the main activity
    InputStream  is;  // input bluetooth stream, also sent over from the main activity

    final int BUFFER_SIZE = 1024;           // maximum return string size
    byte[] buffer = new byte[BUFFER_SIZE];  // byte buffer for reading from the bluetooth bus
    BluetoothMessageClass bluetoothMessage; // message to send to the raspberry pi
    Spinner lightType, numLEDs, frequency;

    /* class constructor. Creates local copies of the input and output streams. */
    public SendToRPIButtonListener(OutputStream os, InputStream is, BluetoothMessageClass bluetoothMessage, Spinner lightType, Spinner numLEDs, Spinner frequency)
    {
        this.os = os;
        this.is = is;
        this.bluetoothMessage = bluetoothMessage;

        this.lightType = lightType;
        this.numLEDs   = numLEDs;
        this.frequency = frequency;
    }

    /* this function gets called when the button is pressed  */
    @Override
    public void onClick(View v)
    {
        try
        {
            bluetoothMessage.SetFrequency(Double.valueOf(this.frequency.getSelectedItem().toString()));
            bluetoothMessage.SetLightType(this.lightType.getSelectedItem().toString());
            bluetoothMessage.SetNumLEDs(Integer.valueOf(this.numLEDs.getSelectedItem().toString()));

            bluetoothMessage.ConstructCSVStr();
            this.write(bluetoothMessage.stringToTransmit);
        }
        /* error handling */
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /* write a string to the output stream (bluetooth socket) */
    public void write(String s) throws IOException
    {
        byte[] x = s.getBytes();
        os.write(x);
    }

    /* read a string from the input stream (bluetooth socket) */
    public void read()
    {
        int bytes = 0;
        int b = BUFFER_SIZE;
        while (true)
        {
            try
            {
                bytes = is.read(buffer, bytes, BUFFER_SIZE - bytes);
                //break;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
