package com.thetopfer.arduino_bluetooth;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.thetopfer.arduino_bluetooth.Fragments.AboutFragment;
import com.thetopfer.arduino_bluetooth.Fragments.ArrowsFragment;
import com.thetopfer.arduino_bluetooth.Fragments.BluetoothFragment;
import com.thetopfer.arduino_bluetooth.Fragments.JoystickFragment;
import com.thetopfer.arduino_bluetooth.Fragments.OnOffFragment;
import com.thetopfer.arduino_bluetooth.Fragments.SettingsFragment;
import com.thetopfer.arduino_bluetooth.Fragments.TerminalFragment;
import com.thetopfer.arduino_bluetooth.Fragments.rgbLedFragment;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;

    // Tag for logging
    private static final String TAG = "MainActivity_Bluetooth";

    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    int currentFragmentId = 0;

    // The thread that does all the work
    BluetoothThread btt;

    // Handler for writing messages to the Bluetooth connection
    Handler writeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS); //receive the address of the bluetooth device

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //new ConnectBT().execute(); //Call the class to connect
        ConnectBluetooth(); // Connect to bluetooth device specified in global address String

        // set default fragment
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new OnOffFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_onoff);
        currentFragmentId = R.id.nav_onoff;
    }

    // Bluetooth
    /*public void Disconnect() {
        if (btSocket != null) //If the btSocket is busy
        {
            try {
                btSocket.close(); //close connection
            } catch (IOException e) {
                msg("Error");
            }
        }
        finish(); //return to the first layout
    }*/

    private void msg(String s) { // fast way to call Toast
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    // Menu and Navigation
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            // TODO add settings here (create settings fragment and start it here)
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, new SettingsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_settings);
            return true;
        }
        if (id == R.id.action_disconnect) {
            //Disconnect();
            DisconnectBluetooth();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
       FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if(currentFragmentId != id) // don't replace with same fragment
        {
            if (id == R.id.nav_onoff) {
                fm.beginTransaction().replace(R.id.content_frame, new OnOffFragment()).commit();
            } else if (id == R.id.nav_rgbled) {
                fm.beginTransaction().replace(R.id.content_frame, new rgbLedFragment()).commit();
            } else if (id == R.id.nav_arrows) {
                fm.beginTransaction().replace(R.id.content_frame, new ArrowsFragment()).commit();
            }else if (id == R.id.nav_joystick) {
                fm.beginTransaction().replace(R.id.content_frame, new JoystickFragment()).commit();
            } else if (id == R.id.nav_terminal) {
                fm.beginTransaction().replace(R.id.content_frame, new TerminalFragment()).commit();
            }else if (id == R.id.nav_settings) {
                fm.beginTransaction().replace(R.id.content_frame, new SettingsFragment()).commit();
            } else if (id == R.id.nav_about) {
                fm.beginTransaction().replace(R.id.content_frame, new AboutFragment()).commit();
            }
        }
        currentFragmentId = id;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Bluetooth thread
    public void ConnectBluetooth()
    {
        Log.v(TAG, "Connect BT: " + address);
        progress = ProgressDialog.show(MainActivity.this, "Connecting...", "Please wait!");  //show a progress dialog

        // Only one thread at a time
        if (btt != null) {
            Log.w(TAG, "Already connected!");
            return;
        }

        // Initialize the Bluetooth thread, passing in a MAC address
        // and a Handler that will receive incoming messages
        btt = new BluetoothThread(address, new Handler() {
            @Override
            public void handleMessage(Message message) {
                String s = (String) message.obj;
                Log.d(TAG, s);

                // Do something with the message
                if (s.equals("CONNECTED")) {
                    progress.dismiss();
                    msg("Connected");
                } else if (s.equals("DISCONNECTED")) {
                    msg("Disconnected");
                    finish();
                } else if (s.equals("CONNECTION FAILED")) {
                    btt = null;
                    msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                    finish();
                } else {
                    Fragment frag = getFragmentManager().findFragmentById(R.id.content_frame);
                    if(frag instanceof BluetoothFragment) {
                        BluetoothFragment fragment = (BluetoothFragment) frag;
                        fragment.ReceiveBluetoothMessage(s);
                    }
                }
            }
        });

        // Get the handler that is used to send messages
        writeHandler = btt.getWriteHandler();

        // Run the thread
        btt.start();
    }

    public void DisconnectBluetooth() {
        Log.v(TAG, "Disconnect BT.");

        if(btt != null) {
            btt.interrupt();
            btt = null;
        }
        finish(); //return to the first layout
    }

    public void SendBluetoothMessage(String message) {
        Log.v(TAG, "Send BT Message: " + message);

        Message msg = Message.obtain();
        msg.obj = message;
        writeHandler.sendMessage(msg);
    }

    // Kill the thread when we leave the activity.
    protected void onPause() {
        super.onPause();

        if(btt != null) {
            btt.interrupt();
            btt = null;
        }
    }
}
