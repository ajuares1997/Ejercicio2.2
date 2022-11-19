package com.aplicacion.ejercicio22.Configuraciones;

public class Objeto {
    public int userId;
    public int id;
    public String title;
    public String bodycompleted;

    public String getBodycompleted() {
        return bodycompleted;
    }

    public void setBodycompleted(String bodycompleted) {
        this.bodycompleted = bodycompleted;
    }

    public Objeto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
