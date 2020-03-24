package com.example.light_controller;

import java.util.ArrayList;
import java.util.List;

public class BluetoothMessageClass
{
    List<ColorStruct> colors;
    String lightType;
    int frequency;
    String direction;
    int numLEDs, numColors;
    String stringToTransmit;
    public BluetoothMessageClass()
    {
        this.colors = new ArrayList<ColorStruct>();

        this.lightType = "solid";
        this.direction = "left";
        this.numLEDs   = 3;
        this.numColors = 1;

        ConstructCSVStr();
    }

    public void AddColor(int r, int g, int b)
    {
        ColorStruct localColor = new ColorStruct(r, g, b);
        this.colors.add(localColor);

    }

    public void SetLightType(String type)
    {
        this.lightType = type;
    }

    public void SetNumLEDs(int num)
    {
        this.numLEDs = num;
    }

    public void SetDirection(String direction)
    {
        this.direction = direction;
    }

    private void ConstructCSVStr()
    {
        /* first clear the old string */
        this.stringToTransmit = "";

        /* then assign the new values */
        this.stringToTransmit = this.lightType + ";" + this.direction + ";" + String.valueOf(this.numLEDs) + ";" + String.valueOf(this.colors.size()) + ";";
        for(int i =0; i < this.colors.size(); i++)
        {
            this.stringToTransmit += String.valueOf(this.colors.get(i).r) + "," + String.valueOf(this.colors.get(i).g) + "," + String.valueOf(this.colors.get(i).b) + ";";
        }

        this.stringToTransmit += "\n";
    }
}
