package com.example.tarea_2_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tarea_2_3.Clases.Transacs;
import com.example.tarea_2_3.Config.Conexion;

import java.io.ByteArrayOutputStream;

public class Take_Foto_Activity extends AppCompatActivity {

    static final int pet_IMg_Capture = 100;
    static final int pet_Camera_Access = 101;

    private Button btnSave, btnTakeF;
    private ImageView imgView;
    private EditText txtDesc;

    private Bitmap bImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_foto);
        chargeObj();

        btnTakeF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty()) saveFoto();
            }
        });
    }

    private void permisos(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, pet_Camera_Access);
        }else{
            takePhoto();
        }
    }

    private void takePhoto(){
        Intent intentTake = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentTake.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intentTake, pet_IMg_Capture);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == pet_IMg_Capture && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bImg = (Bitmap) extras.get("data");
            imgView.setImageBitmap(bImg);
        }
    }

    private void saveFoto(){
        try{
            Conexion con = new Conexion(this, Transacs.dbName, null, 1);
            String desc = txtDesc.getText().toString();
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            bImg.compress(Bitmap.CompressFormat.JPEG, 25, ba);
            byte[] byImg = ba.toByteArray();

            boolean insert = con.SaveData(byImg, desc);

            System.out.println(getExternalCacheDir());

            if(insert==true) Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Hubo un error", Toast.LENGTH_SHORT).show();

            clean();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean isEmpty(){
        if(txtDesc.getText().toString().isEmpty()){
            Toast.makeText(this, "!!!Aviso \n No puede dejar campos vacios: Descripcion", Toast.LENGTH_SHORT).show();
            return true;
        }else if(bImg == null){
            Toast.makeText(this, "!!!Aviso \n Debe tomar una foto", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void chargeObj(){
        btnSave = (Button) findViewById(R.id.btnSave);
        btnTakeF = (Button) findViewById(R.id.btnTakeF);
        imgView = (ImageView) findViewById(R.id.imgView);
        txtDesc = (EditText) findViewById(R.id.txtDesc);
    }

    private void clean(){
        txtDesc.setText("");
        bImg = null;
        imgView.setImageBitmap(null);
    }
}