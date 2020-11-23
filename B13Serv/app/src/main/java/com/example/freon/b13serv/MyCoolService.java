package com.example.freon.b13serv;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class MyCoolService extends Service {

    private Handler mHandler;
    Runnable runnable;
    private String mString;
    int mCounter = 0;
    PendingIntent mPendingIntent;

    public MyCoolService() {
    }

    public static final String KEY_TEXT = "KEY_TEXT";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("happy","onCreate");
        mHandler = new Handler();

        doSomething();



    }

    private void doSomething() {
        mCounter++;
        Log.d("happy", "doSomething " + mCounter);
        showNotification();
        runnable = new Runnable() {
            @Override
            public void run() {
                doSomething();
            }
        };
        mHandler.postDelayed(runnable, 1000);
        //runnable.run();
    }

    private void showNotification() {
        Intent intent = new Intent(this, SecondaryActivity.class);
        intent.putExtra(KEY_TEXT, "" + mString + " " + mCounter);

        mPendingIntent = PendingIntent.getActivity(this,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Wow!");
        builder.setContentText("" + mString + " " + mCounter);
        builder.setSmallIcon(android.R.drawable.stat_sys_warning);
        builder.setContentIntent(mPendingIntent);
        builder.addAction(android.R.drawable.sym_action_email,"email",mPendingIntent);
        builder.addAction(android.R.drawable.sym_action_call,"call",mPendingIntent);

        Notification notification = builder.build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        mString = intent.getStringExtra(KEY_TEXT);
        Log.d("happy","onStartCommand "+
                mCounter+ " " + mString);

        stopSelf(startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("happy","onDestroy");
        //mHandler.removeCallbacks(runnable);
    }
}
