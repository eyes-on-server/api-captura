package com.eyesonserver.model.metrica;

import java.time.LocalDateTime;

public class ViewRegistro {
    private String momento;

    public ViewRegistro(String momento) {
        this.momento = momento;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }
}