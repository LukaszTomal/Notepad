package com.example.notepad;

public class Notepad {
    private long nr;
    private String Data;
    private String Note;

    public Notepad(String data, String note) {
        Data = data;
        Note = note;
    }

    public Notepad() {
    }

    public long getNr() {
        return nr;
    }

    public void setNr(long nr) {
        this.nr = nr;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    @Override
    public String toString() {
        return "Notepad{" +
                "nr=" + nr +
                ", Data='" + Data + '\'' +
                ", Note='" + Note + '\'' +
                '}';
    }
}
