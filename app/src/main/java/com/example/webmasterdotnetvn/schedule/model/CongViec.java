package com.example.webmasterdotnetvn.schedule.model;

public class CongViec {
   private String id;
   private String id_user;
   private String noidung;
   private  String id_thu;
   private  String gioBatDau;
   private  String gioKetThuc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_thu() {
        return id_thu;
    }

    public void setId_thu(String id_thu) {
        this.id_thu = id_thu;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public String getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(String gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }
}
