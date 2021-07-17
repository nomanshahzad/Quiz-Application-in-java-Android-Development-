package com.example.quizapplication.utils;

import com.example.quizapplication.R;

import java.util.Random;

public class IconPicker {
    int[] icons = {R.drawable.ic_icon_1,
            R.drawable.ic_icon_2,
            R.drawable.ic_icon_3,
            R.drawable.ic_icon_4,
            R.drawable.ic_icon_5,
            R.drawable.ic_icon_6,
            R.drawable.ic_icon_7,
            R.drawable.ic_icon_8,};

    int currentIconIndex = 0;

    public int getIcons() {
        Random rand = new Random();
        currentIconIndex = rand.nextInt(icons.length-1);
//        currentIconIndex = (currentIconIndex+1) % icons.length;
        return icons[currentIconIndex];
    }
}
