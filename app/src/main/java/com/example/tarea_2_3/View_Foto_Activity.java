package com.example.tarea_2_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tarea_2_3.Clases.Photograph;
import com.example.tarea_2_3.Clases.Transacs;
import com.example.tarea_2_3.Clases.cAdapter;
import com.example.tarea_2_3.Config.Conexion;

import java.util.ArrayList;

public class View_Foto_Activity extends AppCompatActivity {

    private ListView imgList;
    private ArrayList<Photograph> foto;
    private Conexion con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_foto);

        con = new Conexion(this, Transacs.dbName, null, 1);

        foto = con.getFoto(foto);

        cAdapter adp = new cAdapter(this, foto);

        imgList = (ListView) findViewById(R.id.imgList);

        imgList.setAdapter(adp);

    }


}