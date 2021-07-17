package com.example.quizapplication.utils;

import java.util.ArrayList;
import java.util.Random;

public class ColorPicker {
    String[] color = {"#FF0000", "#00FFFF", "#0000FF", "#00008B", "#FFFF00", "#7FFD4"};
    int currentColorIndex = 0;

    public String getColor() {
        Random rand = new Random();
        currentColorIndex = rand.nextInt(color.length-1);
//        currentColorIndex = (currentColorIndex + 2) % color.length;
        return color[currentColorIndex];
    }
}
