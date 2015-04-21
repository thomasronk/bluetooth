package com.example.ron.bluetoothdscvry;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.datatype.Duration;


public class MainActivity extends ActionBarActivity {

    private String TAG = "MAINACTIVITY";
    private BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
    Timer timer;
    TimerTask timerTask;
    private String serverIPAddress="192.168.0.2";
    private int port=1200;
    Socket socket = null;
    OutputStream otStream;TextView btText;
    rssiHashTable rssiObject;
    rssiHashMap rHMObject;
    MapActivity mapDraw;
    private final List<Integer> rssilist = new ArrayList<Integer>();
    public int map_1;
    public int map_2;
    public int map_3;
    public int map_4;
    public int map_5;
    public ImageView imageview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map_1 = R.drawable.ecss3_map_1;
        map_2 = R.drawable.ecss3_map_2;
        map_3 = R.drawable.ecss3_map_3;
        map_4 = R.drawable.ecss3_map_4;
        map_5 = R.drawable.ecss3_map_5;
        imageview = (ImageView) findViewById(R.id.imageView);
        registerReceiver(receiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

        Button btnStrength = (Button) findViewById(R.id.startDiscovery);
        //rssiObject = new rssiHashTable();
        rHMObject  = new rssiHashMap();
        mapDraw = new MapActivity();
        MyClientTask myClientTask = new MyClientTask(serverIPAddress,port);


        myClientTask.execute();

        btnStrength.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Log.d("Main","Started discovery");
               // BTAdapter.startDiscovery();
                Intent mapIntent = new Intent(getApplicationContext(),MapActivity.class);

                //startActivity(mapIntent);
                // BTAdapter.startDiscovery();
                startTimer();


                /*try {
                    otStream = socket.getOutputStream();
                    PrintWriter output = new PrintWriter(otStream);
                    output.println("Hello from android \n");
                    output.flush();
                    output.close();
                }
                catch (IOException e){
                    Log.
                }*/


            }
        });

    }

    public void startTimer()
    {
        timer = new Timer();
        initializeTimerTask();

        timer.schedule(timerTask, 500, 10); //

    }

    public void initializeTimerTask(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                //Log.d("TimerTask","Rnnings");
               BTAdapter.startDiscovery();

            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) return true;

        return super.onOptionsItemSelected(item);
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView btText = (TextView)findViewById(R.id.btStrength);
            TextView btText2 = (TextView)findViewById(R.id.btStrength2);
            TextView btText3 = (TextView)findViewById(R.id.btStrength3);
            TextView btText4 = (TextView)findViewById(R.id.btStrength4);

            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                String rssiSourceName = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);



                if(rssiSourceName!=null) {
                    Log.d(TAG,rssiSourceName);

                   // rssiObject.updateRssiTable(rssiSourceName, rssi);

                    if(rssiSourceName.equals("PraveenKumar’s iPhone")) {
                        btText.setText(rssiSourceName+rssi);
                    }
                    else if(rssiSourceName.equals("HMSoft")) {
                        btText2.setText(rssiSourceName+rssi);
                    }
                    else if(rssiSourceName.equals("Riti's iPad")) {
                        btText3.setText(rssiSourceName+rssi);
                    }
                    else if(rssiSourceName.equals("Ron’s MacBook Pro")) {
                        btText4.setText(rssiSourceName+rssi);
                    }

                    rHMObject.updateRssiHashMap(rssiSourceName,rssi);
                   switchImage(2);


                }
                else{
                    Log.d(TAG,"Null Sourcename detected");
                }
               // BTAdapter.startDiscovery();
            }
        }
    };

    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String response = "";

        MyClientTask(String addr, int port){
            dstAddress = addr;
            dstPort = port;
            Log.d("MyClientTest","The server address and port are"+dstAddress+dstPort);
        }

        @Override
        protected Void doInBackground(Void... arg0) {



            try {
                socket = new Socket(dstAddress, dstPort);

                ByteArrayOutputStream byteArrayOutputStream =
                        new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];

                int bytesRead;
                InputStream inputStream = socket.getInputStream();


                /*PrintWriter output = new PrintWriter(otStream);
                output.println("Hello from android");
                output.flush();
                output.close();*/
    /*
     * notice:
     * inputStream.read() will block if no data return
     */
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                    response += byteArrayOutputStream.toString("UTF-8");
                }

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            }finally{
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
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
