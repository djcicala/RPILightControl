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

    /* class constructor. Creates local copies of the input and output streams. */
    public SendToRPIButtonListener(OutputStream os, InputStream is)
    {
        this.os = os;
        this.is = is;
    }

    /* this function gets called when the button is pressed  */
    @Override
    public void onClick(View v)
    {
        try
        {
            this.write("ACK");   // send an ack bit to see if the signal is acknowledged on the raspberry pi's end
            this.read();            // read from the bus, ensuring that we received an "ACK" in response

            String response = new String(buffer); // convert the response to a string so we can compare it
            if(response.equals("ACK") == true)
            { // an ACK was received
                this.write("probe");           // send the data to the raspberry pi TODO: add the actual bluetooth message here
            }
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
