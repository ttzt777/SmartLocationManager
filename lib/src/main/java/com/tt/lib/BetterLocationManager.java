package com.tt.lib;

import android.content.Context;
import android.location.LocationManager;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tt on 2017/11/28.
 */

public class BetterLocationManager {
    private static final String GPS_PROVIDER_NAME = LocationManager.GPS_PROVIDER;
    private static final String NETWORK_PROVIDER_NAME = LocationManager.NETWORK_PROVIDER;

    public static BetterLocationManager mDefault;

    private LocationManager mLocationManager;

    private WeakReference<Context> mContext;

    private Set<ILocationListener> mListeners;

    // 默认优先采用网络定位
    private boolean isNetworkFirst = true;

    private long minTime;
    private float minDistance;

    public static BetterLocationManager getDefault(Context context) {
        if (mDefault == null) {
            synchronized (BetterLocationManager.class) {
                if (mDefault == null) {
                    mDefault = new BetterLocationManager(context.getApplicationContext());
                }
            }
        }

        return mDefault;
    }

    public BetterLocationManager(Context context) {
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

    }

    public void setGPSLocationFirst() {
        isNetworkFirst = false;
    }

    public void setNetworkLocationFirst() {
        isNetworkFirst = true;
    }

    public void addListener(ILocationListener listener) {
        if (mListeners == null) {
            mListeners = new HashSet<>();
        }

        mListeners.add(listener);
    }

    public void removeListener(ILocationListener listener) {
        if (mListeners != null) {
            mListeners.remove(listener);
        }
    }

    public void setMinTime(long minTime) {
        if (minTime < 0) {
            minTime = 0;
        }
        this.minTime = minTime;
    }

    public void setMinDistance(float minDistance) {
        if (minDistance < 0) {
            minDistance = 0;
        }
        this.minDistance = minDistance;
    }

    public void start() {
        if (isNetworkFirst) {

        }
    }
}
