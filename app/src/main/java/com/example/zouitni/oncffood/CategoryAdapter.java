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

public class CategoryAdapter extends ArrayAdapter {

    String[][] categoriess;
    public CategoryAdapter(@NonNull Context context, @NonNull String[][] categories) {
        super(context, R.layout.item,(String[][]) categories);
        categoriess = categories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater khalilsInflater1 = LayoutInflater.from(getContext());
        View category = khalilsInflater1.inflate(R.layout.categorie,parent,false);

        String[] cat = (String[]) getItem(position);

        TextView dossier = (TextView) category.findViewById(R.id.categoryName);
        dossier.setText(cat[0]);

        return category;
    }
}