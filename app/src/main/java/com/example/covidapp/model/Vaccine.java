package com.example.covidapp.model;

import java.io.Serializable;

public class Vaccine implements Serializable {
    private int id;
    private int dose;
    private String type;
    private String date;
    private int idUser;
    
    public Vaccine() {
        this.id = 0;
    }

    public Vaccine(int id, int dose, String type, String date, int idUser) {
        this.id = id;
        this.dose = dose;
        this.type = type;
        this.date = date;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
