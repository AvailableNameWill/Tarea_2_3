package com.example.tarea_2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAfoto, btnAVfot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chargeObj();

        btnAfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Take_Foto_Activity.class);
                startActivity(intent);
            }
        });

        btnAVfot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), View_Foto_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void chargeObj(){
        btnAfoto = (Button) findViewById(R.id.btnAfoto);
        btnAVfot = (Button) findViewById(R.id.btnAVfot);
    }
}