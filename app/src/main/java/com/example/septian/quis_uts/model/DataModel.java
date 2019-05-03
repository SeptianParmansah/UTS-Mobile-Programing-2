package com.example.septian.quis_uts.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DataModel {
    @SerializedName("id")
    private String Id;
    @SerializedName("kode_toko")
    private String Kode_toko;
    @SerializedName("nama_toko")
    private String Nama_toko;
    @SerializedName("alamat_toko")
    private String Alamat_toko;
    @SerializedName("pemilik_toko")
    private String Pemilik_toko;

    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getKode_toko() {
        return Kode_toko;
    }
    public void setKode_toko(String kode_toko) {
        Kode_toko = kode_toko;
    }
    public String getNama_toko() {
        return Nama_toko;
    }
    public void setNama_toko(String nama_toko) {
        Nama_toko = nama_toko;
    }
    public String getAlamat_toko() {
        return Alamat_toko;
    }
    public void setAlamat_toko(String alamat_toko) {
        Alamat_toko = alamat_toko;
    }
    public String getPemilik_toko() {
        return Pemilik_toko;
    }
    public void setPemilik_toko(String pemilik_toko) {
        Pemilik_toko = pemilik_toko;
    }
}
