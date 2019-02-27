package org.pandec.simplesql.entity;

public class Data {
    private int id;
    private String nama;
    private String status;

    public Data(int id, String nama, String status) {
        this.id = id;
        this.nama = nama;
        this.status = status;
    }

    public Data() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
