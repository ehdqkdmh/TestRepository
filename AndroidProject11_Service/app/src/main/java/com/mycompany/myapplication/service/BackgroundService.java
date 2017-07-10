package com.mycompany.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class BackgroundService extends Service {
    private static final String TAG = "BackgroundService";
    private Thread thread;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //최초로 startService()를 호출할 때 실행
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    //startService()를 호출할 때 마다 실행
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");

        if(thread == null || !thread.isAlive()) {
            thread = new Thread() {
                private int count;
                @Override
                public void run() {
                    while(true) {
                        Log.i(TAG, "count: " + count++);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    stopSelf();
                }
            };
            thread.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    //작업 관리자에서 앱을 종료시켰을 때 실행
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestory()");
        if(thread != null && thread.isAlive()) {
            thread.interrupt();
            thread = null;
        }
    }
}
