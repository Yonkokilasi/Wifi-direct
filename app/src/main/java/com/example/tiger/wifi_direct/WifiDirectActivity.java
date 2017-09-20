package com.example.tiger.wifi_direct;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WifiDirectActivity extends AppCompatActivity implements WifiP2pManager.ChannelListener{
    public static final String TAG = "wifidirectdemo";
    private WifiP2pManager manager;
    private boolean isWifiP2pEnabled = false;
    private boolean retryChannel = false;

    private final IntentFilter intentFilter = new IntentFilter();
    private WifiP2pManager.Channel channel;
    private BroadcastReceiver receiver = null;
    public void setWifiP2pEnabled(boolean isWifiP2pEnabled){
        this.isWifiP2pEnabled = isWifiP2pEnabled;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_direct);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);
    }
    @Override
    public void onResume(){
        super.onResume();
        receiver = new WifiDirectBroadcastReceiver(manager,channel,this);
        registerReceiver(receiver,intentFilter);
    }
    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onChannelDisconnected() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
