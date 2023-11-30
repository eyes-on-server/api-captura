package com.eyesonserver.model.metrica;


public class Registro {
    private Integer id;
    private Integer fkComponenteServidor;
    private Object valor;

    public Registro(Integer fkComponenteServidor, Object valor) {
        this.fkComponenteServidor = fkComponenteServidor;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getFkComponenteServidor() {
        return fkComponenteServidor;
    }

    public void setFkComponenteServidor(Integer fkComponenteServidor) {
        this.fkComponenteServidor = fkComponenteServidor;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

}