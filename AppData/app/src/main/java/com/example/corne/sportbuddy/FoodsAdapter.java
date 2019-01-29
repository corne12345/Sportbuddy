package com.example.corne.sportbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodsAdapter extends ArrayAdapter<String> {
    private ArrayList<String> food;
    private ArrayList<String> servingUnit;


    // Constructor
    public FoodsAdapter(Context context, int resource, ArrayList<String> objects, ArrayList<String> objects2) {
        super(context, resource, objects);
        this.food = objects;
        this.servingUnit = objects2;
    }

    // getView method to inflate adapter into ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.linear_item, parent, false);
        }

        // Select TextViews and insert foods and serving sizes on the right places
        TextView itemName = convertView.findViewById(R.id.textView23);
        String name = food.get(position);
        itemName.setText(name);

        TextView servingUnitView = convertView.findViewById(R.id.textView24);
        String servingUnitString = servingUnit.get(position);
        servingUnitView.setText(servingUnitString);

        return convertView;
    }
}
