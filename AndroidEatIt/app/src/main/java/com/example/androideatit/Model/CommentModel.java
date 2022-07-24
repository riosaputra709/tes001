package com.example.androideatit.Model;

import java.util.Map;

public class CommentModel {
    private float nilaiRating;
    private String komentar,nama,uid;
    private Map<String,Object> waktuKomentar;

    public CommentModel() {
    }

    public float getNilaiRating() {
        return nilaiRating;
    }

    public void setNilaiRating(float nilaiRating) {
        this.nilaiRating = nilaiRating;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, Object> getWaktuKomentar() {
        return waktuKomentar;
    }

    public void setWaktuKomentar(Map<String, Object> waktuKomentar) {
        this.waktuKomentar = waktuKomentar;
    }
}
