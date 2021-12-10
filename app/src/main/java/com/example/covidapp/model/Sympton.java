package com.example.covidapp.model;

public class Sympton {

    private int id;
    private String answer;
    private String date;
    private int idUser;

    public Sympton() {
    }

    public Sympton(int id, String answer, String date, int idUser) {
        this.id = id;
        this.answer = answer;
        this.date = date;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
