package com.company.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiStateReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiStateReceiver";

    private native void Java_com_company_app_WIFIConnect_ACTIONreceiver_notifyQt(String data);

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)) {
            NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (networkInfo != null && networkInfo.isConnected()) {
                // Wi-Fi连接成功
                Log.i(TAG, "Wi-Fi连接成功");
                                //返回信息到Qt
                                Java_com_company_app_WIFIConnect_ACTIONreceiver_notifyQt("Wi-Fi连接成功");

            }
        } else if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(action)) {
            int suplState = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, -1);
            if (suplState == WifiManager.ERROR_AUTHENTICATING) {
                // Wi-Fi连接失败（身份验证错误）
                Log.i(TAG, "Wi-Fi连接失败（身份验证错误）");
                                //返回信息到Qt
                                Java_com_company_app_WIFIConnect_ACTIONreceiver_notifyQt("Wi-Fi连接失败（身份验证错误）");
            }
        }
    }
}
