package com.example.freon.a56dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class DialogFragment1 extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle("select")
                .setItems(MainActivity.ENGINE_NAMES,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //which содержит индекс выбранного элемента списка
                                Intent intent = new Intent(MainActivity.ACTION_ENGINE_SELECTED);
                                intent.putExtra(MainActivity.KEY_IDX, which);
                                getActivity().sendBroadcast(intent);

                                /*
                                Activity activity = getActivity();
                                if (activity instanceof BrowserCall){
                                    ((BrowserCall)activity).callBrowser(which);
                                }else {
                                    Toast.makeText(activity, "Sory, unable to call Browser ",Toast.LENGTH_LONG).show();
                                }*/
                            }
                        });
        return builder.create();
    }


}
