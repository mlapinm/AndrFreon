package com.example.freon.a86clstorefile;

//Класс Store для записи текста в файл инициализируется (контекстом и) именем файла
//mStore = new Store(this, mFileName);
//для чтения: string = mStore.getString();
//для записи: mStore.storeString(string);

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;


public class Store {
    Context mContext;
    String mFileName;

    public Store(Context context, String fileName){
        mContext = context;
        mFileName = fileName;
    }

    String getString(){
        String string2 = "";
        try {
            InputStream inputStream = mContext.openFileInput(mFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String string1;
            StringBuilder stringBuilder = new StringBuilder();
            while ((string1 = bufferedReader.readLine()) != null) {
                stringBuilder.append(string1 + "\n");
                Toast.makeText(mContext,"123  " + string1, Toast.LENGTH_LONG).show();
            }
            inputStream.close();
            string2 = stringBuilder.toString();
       } catch (Throwable throwable) {
            Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_LONG).show();
        }

        return string2;
    }

    void  storeString(String string1){
        if (string1 != null) {
            try {
                OutputStream outputStream = mContext.openFileOutput(mFileName, MODE_PRIVATE);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                outputStreamWriter.write(string1);
                outputStreamWriter.close();
                Toast.makeText(mContext, "write file1.txt.", Toast.LENGTH_LONG).show();
            } catch (Throwable throwable) {
                Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
