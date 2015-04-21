package com.example.ron.bluetoothdscvry;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MapActivity extends ActionBarActivity {
    String TAG = "MAPACTIVITY";
    public ImageView imageview;
    public int map_1;
    public int map_2;
    public int map_3;
    public int map_4;
    public int map_5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        map_1 = R.drawable.ecss3_map_1;
        map_2 = R.drawable.ecss3_map_2;
        map_3 = R.drawable.ecss3_map_3;
        map_4 = R.drawable.ecss3_map_4;
        map_5 = R.drawable.ecss3_map_5;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void switchImage(int range){
        imageview = (ImageView) findViewById(R.id.imageView);
        Log.d(TAG,"Setting image view");
        switch (range){
            case 1:
                imageview.setImageResource(map_1);
                break;
            case 2:
                imageview.setImageResource(map_2);
                break;
            case 3:
                imageview.setImageResource(map_3);
                break;
            case 4:
                imageview.setImageResource(map_4);
                break;
            case 5:
                imageview.setImageResource(map_5);
                break;
            default:
                Log.d("SwitchImage", "Invalid Range in SwitchImage");
        }
        imageview.invalidate();
    }
}
