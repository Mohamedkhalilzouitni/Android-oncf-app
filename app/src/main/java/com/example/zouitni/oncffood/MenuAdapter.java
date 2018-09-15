package com.example.zouitni.oncffood;

import android.widget.ArrayAdapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MenuAdapter extends ArrayAdapter<String[]> {

    String[][] alertess;
    public MenuAdapter(@NonNull Context context, @NonNull String[][] alertes) {
        super(context, R.layout.item,(String[][]) alertes);
        alertess = alertes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater khalilsInflater1 = LayoutInflater.from(getContext());
        View alerte = khalilsInflater1.inflate(R.layout.item,parent,false);

        String[] alert = getItem(position);
        /*TextView flag = (TextView) alerte.findViewById(R.id.itemImage);
        flag.setText(alert[0]);*/
        TextView dossier = (TextView) alerte.findViewById(R.id.itemName);
        dossier.setText(alert[1]);
        TextView patient = (TextView) alerte.findViewById(R.id.itemDescription);
        patient.setText(alert[2]);
        TextView date = (TextView) alerte.findViewById(R.id.itemPrice);
        date.setText(alert[3]);

        return alerte;
    }
}
