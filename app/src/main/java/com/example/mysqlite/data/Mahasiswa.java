package com.example.mysqlite.data;

public class Mahasiswa {

    private int no;
    private String npm;
    private String nama;
    private String jurusan;

    //constructor

    public Mahasiswa(int no, String npm, String nama, String jurusan) {
        this.no = no;
        this.npm = npm;
        this.nama = nama;
        this.jurusan = jurusan;
    }

    public Mahasiswa() {
    }

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "no=" + no +
                ", npm='" + npm + '\'' +
                ", nama='" + nama + '\'' +
                ", jurusan='" + jurusan + '\'' +
                '}';
    }

    //getters and setters
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}



