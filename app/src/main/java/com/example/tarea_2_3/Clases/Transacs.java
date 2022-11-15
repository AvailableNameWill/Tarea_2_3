package com.example.tarea_2_3.Clases;

public class Transacs {
    public static final String dbName = "tarea_2_3";
    public static final String tblName = "fotos";

    public static final String id = "id";
    public static final String img = "img";
    public static final String desc = "desc";

    public static final String createTblFoto = "CREATE TABLE fotos (id INTEGER PRIMARY KEY AUTOINCREMENT" +
                                               ", img BLOB, desc TEXT)";

    public static final String getFotos = "SELECT * FROM " + Transacs.tblName;

    public static final String dropFotos ="DROP TABLE IF EXISTS fotos";
}
