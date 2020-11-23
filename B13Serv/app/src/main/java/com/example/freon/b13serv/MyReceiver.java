package com.example.freon.b13serv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent coolIntent = new Intent(context, MyCoolService.class);
        coolIntent.putExtra(MyCoolService.KEY_TEXT, "from broadcast");
        context.startService(coolIntent);

    }
}
