package com.example.light_controller;

public class ColorStruct
{
    int r, g, b;
    public ColorStruct()
    {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public ColorStruct(int r, int g, int b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void updateColor(int r, int g, int b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
