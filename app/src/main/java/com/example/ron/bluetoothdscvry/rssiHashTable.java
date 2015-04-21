package com.example.ron.bluetoothdscvry;

import android.util.Log;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ron on 4/13/15.
 */
public class rssiHashTable {
    String TAG = "RSSIHASHTABLE";
    private String[] btSource;
    int A = -73;
    private Hashtable btTable;
    indoorCordinates indoorObject;
    float[] distances = new float[3];

    public rssiHashTable(){
        Log.d(TAG,"Constructor");
        btSource = new String[]{"HMSource","Ron’s MacBook Pro","Riti"};
        btTable = new Hashtable();
        indoorObject = new indoorCordinates();
        initialiseHashTable();
        printHash();
        distances[0]=0;
        distances[1]=0;
        distances[2]=0;
    }

    public void initialiseHashTable(){
        /*for(int i=0;i<btSource.length;i++){
            btTable.put(btSource[i],"0");
        }*/

        btTable.put(btSource[0],"-62");
        btTable.put(btSource[1],"-68");
        btTable.put(btSource[2],"-89");
    }

    public void printHash(){
        Set entrySet = btTable.entrySet();
        Iterator it = entrySet.iterator();

        System.out.println("Hashtable entries : ");

        while(it.hasNext())
            Log.d(TAG,it.next().toString());

    }

    public void updateRssiTable(String sourceName,int rssiStrength){
        //Log.d("RssiHash",sourceName);
        boolean keyExists = btTable.containsKey(sourceName);
        if(keyExists) {
            //Log.d("RssiHash", keyExists + "Found Key");
            Log.d(TAG,"Updating the hashtable with "+sourceName+rssiStrength);
            btTable.put(sourceName, rssiStrength);
            printHash();

        }
        //else
            //Log.d("RssiHash",keyExists+"No such key exists");
        //calcStrongestSignal();
    }


    public void calcStrongestSignal(){
    int i=0;
        //Set entrySet = btTable.entrySet();
        Iterator it = btTable.entrySet().iterator();
        while (it.hasNext()) {

           // Map.Entry pair = (Map.Entry)it.next();
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            //Log.d("TAG","distance is "+ calcDistance(Integer.parseInt(pair.getValue().toString())));
            distances[i] = calcDistance(Integer.parseInt(pair.getValue().toString()));
            i++;
        }

        for (int j=0;j<distances.length;j++){
            Log.d(TAG,"Distance "+j+" is "+ distances[j]);
        }

        indoorObject.calcPosition(distances);

    }

    public float calcDistance(int rssi){

        float distance=0;
        float exp =  (float)((A - rssi)/(10*2.1));
        distance = (float)Math.pow(10, exp);
        return distance;
    }
}
