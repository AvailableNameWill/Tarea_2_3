package com.example.tarea_2_3.Clases;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.tarea_2_3.R;

import java.util.ArrayList;

public class cAdapter extends ArrayAdapter<Photograph> {

    private final Activity context;
    private final ArrayList<Photograph> foto;

    public cAdapter(Activity context, ArrayList<Photograph> foto){
        super(context, R.layout.fotos, foto);

        this.context = context;
        this.foto = foto;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rView = inflater.inflate(R.layout.fotos, null, true);

        ImageView imgModel = (ImageView) rView.findViewById(R.id.imgModel);

        imgModel.setImageBitmap(foto.get(position).getImg());

        return rView;
    }
}
