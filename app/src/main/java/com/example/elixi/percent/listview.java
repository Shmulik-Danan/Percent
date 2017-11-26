package com.example.elixi.percent;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import static com.example.elixi.percent.MainActivity.arr;


/**
 * Created by elixi on 28 אוקטובר 2017.
 */

public class listview extends BaseAdapter {
    @Override

    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        //view=getActivity().getLayoutInflater().inflate(R.layout.listview,null);
        TextView tvTotal=(TextView)view.findViewById(R.id.textViewTotall);
        TextView tvP2=(TextView)view.findViewById(R.id.textViewP2);
        TextView tvP1=(TextView)view.findViewById(R.id.textViewP1);
        TextView tvPraice=(TextView)view.findViewById(R.id.textViewPraice);


        tvPraice.setText(arr.get(i).getPrice());
        tvP1.setText(arr.get(i).getP1());
        tvP2.setText(arr.get(i).getP2());
        tvTotal.setText(arr.get(i).getTotall());

        return null;
    }
}
