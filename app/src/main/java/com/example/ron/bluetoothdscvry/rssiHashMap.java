package com.example.ron.bluetoothdscvry;

import android.util.Log;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by ron on 4/19/15.
 */
public class rssiHashMap {
    private String TAG = "RSSIHASHMAP";
    private HashMap<String, Integer> rssiHashMap;
    private String[] btSource;

    public rssiHashMap() {
        Log.d(TAG, "Constructor");
        btSource = new String[]{"HMSoft", "Ron’s MacBook Pro", "Riti's iPad", "PraveenKumar’s iPhone", "Praveens-Ipad"};
        rssiHashMap = new HashMap<>();
        initialiseHashTable();
        printHash();

       // getHighest();

    }

    public String getHighest(){
        int temp = Integer.MIN_VALUE;
        String str = "";

        Set set = rssiHashMap.entrySet();

        // Get an iterator
        Iterator i = set.iterator();

        // Display elements
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
           /* Log.d(TAG, "SortedHashMap");
            Log.d(TAG, me.getKey() + ": ");
            Log.d(TAG, me.getValue()+"");*/
            if((int)me.getValue() > temp) {
                temp = (int) me.getValue();
                str = me.getKey().toString();
            }
        }

        return str;
    }

 /*   public void getHighest() {
        Map sortedMap = sortByValues(rssiHashMap);

        Set set = sortedMap.entrySet();

        // Get an iterator
        Iterator i = set.iterator();

        // Display elements
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            Log.d(TAG, "SortedHashMap");
            Log.d(TAG, me.getKey() + ": ");
            Log.d(TAG, me.getValue()+"");
        }
    }*/

/*    public static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k1, K k2) {
                        int compare =
                                map.get(k1).compareTo(map.get(k2));
                        if (compare == 0)
                            return 1;
                        else
                            return compare;
                    }
                };

        Map<K, V> sortedByValues =
                new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }*/

    public void initialiseHashTable() {
        /*for(int i=0;i<btSource.length;i++){
            btTable.put(btSource[i],"0");
        }*/

        rssiHashMap.put(btSource[0], Integer.MIN_VALUE);
        rssiHashMap.put(btSource[1], Integer.MIN_VALUE);
        rssiHashMap.put(btSource[2], Integer.MIN_VALUE);
        rssiHashMap.put(btSource[3], Integer.MIN_VALUE);
        rssiHashMap.put(btSource[4], Integer.MIN_VALUE);
    }

    public void printHash() {
        Set entrySet = rssiHashMap.entrySet();
        Iterator it = entrySet.iterator();

        System.out.println("HashMap entries : ");

        while (it.hasNext())
            Log.d(TAG, it.next().toString());

    }

    public void updateRssiHashMap(String sourceName, int rssiStrength) {
        //Log.d("RssiHash",sourceName);
        boolean keyExists = rssiHashMap.containsKey(sourceName);
        if (keyExists) {
            //Log.d("RssiHash", keyExists + "Found Key");
            Log.d(TAG, "Updating the hashtable with " + sourceName + rssiStrength);
            rssiHashMap.put(sourceName, rssiStrength);
            printHash();

        }

        //else
        //Log.d("RssiHash",keyExists+"No such key exists");
        //calcStrongestSignal();
    }

}
