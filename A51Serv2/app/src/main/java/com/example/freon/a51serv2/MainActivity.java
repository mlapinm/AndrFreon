package com.example.freon.a51serv2;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends ListActivity {

    private final static String KEY_CHAR_CODE = "CharCode";
    private final static String KEY_VALUE = "Value";
    private final static String KEY_NOMINAL = "Nominal";
    private final static String KEY_NAME = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        populate();
    }

    private void populate() {
        ArrayList<Map<String, String>> data = getData();
        String[] from = {KEY_CHAR_CODE, KEY_VALUE, KEY_NOMINAL,
                KEY_NAME};
        int[] to = {R.id.charCodeView, R.id.valueView,
                R.id.nominalView, R.id.nameView};
        SimpleAdapter sa = new SimpleAdapter(this, data,
                R.layout.item_view, from, to);
        setListAdapter(sa);

    }

    private ArrayList<Map<String, String>> getData() {
        ArrayList<Map<String, String>> list =
                new ArrayList<Map<String, String>>();
        Map<String, String> m;
        String string22=getString(R.string.rates_url);
        try {
// Создаем объект URL
            URL url = new URL(getString(R.string.rates_url));
// Соединяемся
            HttpURLConnection httpConnection =
                    (HttpURLConnection) url.openConnection();
// Получаем от сервера код ответа
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                string22="HttpURLConnection.HTTP_OK";
            }
// Если код ответа хороший, парсим поток(ответ сервера),
// устанавливаем дату в заголовке приложения и
// заполняем list нужными Map'ами
        }
        catch (Exception e){

        }
        m = new HashMap<String, String>();
        m.put(KEY_CHAR_CODE, "AUD");
        m.put(KEY_VALUE, "43,8528");
        m.put(KEY_NOMINAL, "1");

        m.put(KEY_NAME, string22);
        list.add(m);
        list.add(m);
        return list;

    }


    private ArrayList<Map<String, String>> getData2() {
        ArrayList<Map<String, String>> list =
                new ArrayList<Map<String, String>>();
        Map<String, String> m;
        m = new HashMap<String, String>();
        m.put(KEY_CHAR_CODE, "AUD");
        m.put(KEY_VALUE, "43,8528");
        m.put(KEY_NOMINAL, "1");

        m.put(KEY_NAME, "Австралийский доллар");
        list.add(m);
        list.add(m);
        return list;
    }

    private ArrayList<Map<String, String>> getData3() {
        ArrayList<Map<String, String>> list =
                new ArrayList<Map<String, String>>();
        Map<String, String> m;
        m = new HashMap<String, String>();
        m.put(KEY_CHAR_CODE, "AUD");
        m.put(KEY_VALUE, "43,8528");
        m.put(KEY_NOMINAL, "1");

        m.put(KEY_NAME, "Австралийский доллар");
        list.add(m);
        list.add(m);
        return list;
    }
}
