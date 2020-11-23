package com.example.freon.b25lstdt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MyAdapter extends BaseAdapter {
    String[] data = new String[0];
    LayoutInflater inflater;


    public void setData(String[] newData){
        data = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(data != null)
            return data.length;
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        View itemView;
        MyHolder myHolder;
        if(convertView == null){
            itemView = inflater.inflate(R.layout.item, parent, false);
            TextView data1 = (TextView) itemView.findViewById(R.id.data1);
            TextView data2 = (TextView) itemView.findViewById(R.id.data2);
            final TextView data3 = (TextView) itemView.findViewById(R.id.data3);
            data3.setVisibility(View.GONE);
            myHolder = new MyHolder(data1 , data2 , data3);

            data1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(data3.getVisibility() == View.VISIBLE){
                        data3.setVisibility(View.GONE);
                    }else {
                        data3.setVisibility(View.VISIBLE);
                    }
                }
            });

            itemView.setTag(myHolder);
        } else {
            itemView = convertView;
            myHolder = (MyHolder)convertView.getTag();
        }
        String item = (String)getItem(position);
        myHolder.data1.setText("" + (position + 1));
        myHolder.data2.setText(item);
        String string = "/-\\|";
        for(int i=0;i<8;i++){
            string += string;
        }
        myHolder.data3.setText(string);
        itemView.setTag(myHolder);
        return itemView;
    }

    public static final class MyHolder{
        TextView data1;
        TextView data2;
        TextView data3;
        public MyHolder(TextView data1, TextView data2, TextView data3 ){
            this.data1 = data1;
            this.data2 = data2;
            this.data3 = data3;
        }
    }
}
