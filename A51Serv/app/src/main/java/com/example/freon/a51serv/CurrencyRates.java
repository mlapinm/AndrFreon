package com.example.freon.a51serv;

import android.app.ListActivity;
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

public class CurrencyRates extends ListActivity {

    private final static String KEY_CHAR_CODE = "CharCode";
    private final static String KEY_VALUE = "Value";
    private final static String KEY_NOMINAL = "Nominal";
    private final static String KEY_NAME = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        try {
// Создаем объект URL
            URL url = new URL(getString(R.string.rates_url));
// Соединяемся
            HttpURLConnection httpConnection =
                    (HttpURLConnection) url.openConnection();
// Получаем от сервера код ответа
            int responseCode = httpConnection.getResponseCode();
// Если код ответа хороший, парсим поток(ответ сервера),
// устанавливаем дату в заголовке приложения и
// заполняем list нужными Map'ами
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConnection.getInputStream();
                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document dom = db.parse(in);
                Element docElement = dom.getDocumentElement();
                String date = docElement.getAttribute("Date");
                setTitle(getTitle() + " на " + date);
                NodeList nodeList = docElement
                        .getElementsByTagName("Valute");
                int count = nodeList.getLength();
                if (nodeList != null && nodeList.getLength() > 0) {
                    for (int i = 0; i < count; i++) {
                        Element entry = (Element) nodeList
                                .item(i);
                        m = new HashMap<String, String>();
                        String charCode = entry
                                .getElementsByTagName(KEY_CHAR_CODE)
                                .item(0).getFirstChild()
                                .getNodeValue();
                        String value = entry
                                .getElementsByTagName(KEY_VALUE)
                                .item(0).getFirstChild()
                                .getNodeValue();
                        String nominal = "за " + entry
                                .getElementsByTagName(KEY_NOMINAL)
                                .item(0).getFirstChild()
                                .getNodeValue();
                        String name = entry
                                .getElementsByTagName(KEY_NAME)
                                .item(0).getFirstChild()
                                .getNodeValue();
                        m.put(KEY_CHAR_CODE, charCode);
                        m.put(KEY_VALUE, value);
                        m.put(KEY_NOMINAL, nominal);
                        m.put(KEY_NAME, name);
                        list.add(m);
                    }
                }
            } else {
                Toast.makeText(this,"OOPS! Somthing went wrong :(",Toast.LENGTH_LONG).show();
// Сделать извещения об ошибках, если код ответа
// нехороший
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return list;
    }

}

