package com.example.ron.bluetoothdscvry;

import android.util.Log;

/**
 * Created by ron on 4/15/15.
 */
public class indoorCordinates {
    String TAG = "INDOORCORDINATES";
    //D0 - HMSource
    //D1 - Rons Macbook
    //D2 - Riti
    private int[] X1 = new int[]{};
    private int[] X2 = new int[]{};
    private int[] X3 = new int[]{};


    public indoorCordinates() {
        Log.d(TAG, "Constructor");
    }


    public void calcPosition(float[] distance) {

        for (int i = 0; i < distance.length; i++) {
            Log.d(TAG, "Distances are" + distance[i]);
        }


    }
}
