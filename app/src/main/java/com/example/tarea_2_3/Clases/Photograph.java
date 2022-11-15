package com.example.tarea_2_3.Clases;

import android.graphics.Bitmap;

public class Photograph {

    private Integer id;
    private Bitmap img;
    private String desc;

    public Photograph(){}

    public Photograph(Integer _id, Bitmap _img, String _desc){
        id = _id;
        img = _img;
        desc = _desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
