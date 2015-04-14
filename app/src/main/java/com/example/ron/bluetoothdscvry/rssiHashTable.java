package com.example.ron.bluetoothdscvry;

import android.util.Log;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ron on 4/13/15.
 */
public class rssiHashTable {

    private String[] btSource;
    private Hashtable btTable;

    public rssiHashTable(){
        Log.d("RssiHashTable","Constructor");
        btSource = new String[]{"HMSource","Ron’s MacBook Pro","Riti"};
        btTable = new Hashtable();
        initialiseHashTable();
        printHash();
    }

    public void initialiseHashTable(){
        for(int i=0;i<btSource.length;i++){
            btTable.put(btSource[i],"0");
        }
    }

    public void printHash(){
        Set entrySet = btTable.entrySet();
        Iterator it = entrySet.iterator();

        System.out.println("Hashtable entries : ");

        while(it.hasNext())
            Log.d("RssiHashTable",it.next().toString());

    }

    public void updateRssiTable(String sourceName,int rssiStrength){
        //Log.d("RssiHash",sourceName);
        boolean keyExists = btTable.containsKey(sourceName);
        if(keyExists) {
            //Log.d("RssiHash", keyExists + "Found Key");
            btTable.put(sourceName, rssiStrength);
            printHash();
        }
        else
            Log.d("RssiHash",keyExists+"No such key exists");

    }

}
