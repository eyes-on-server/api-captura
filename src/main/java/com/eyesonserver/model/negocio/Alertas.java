package com.eyesonserver.model.negocio;

import java.time.LocalDateTime;

public class Alertas {
    private String tituloAlerta;
    private String descricaoAlerta;
    private String tipoAlerta;
    private LocalDateTime momento;
    private Integer fkComponenteMedida;
    private Integer fkEmpresa;
    private Integer fkServidor;

    public Alertas(String tituloAlerta, String descricaoAlerta, String tipoAlerta, LocalDateTime momento, Integer fkComponenteMedida, Integer fkEmpresa, Integer fkServidor) {
        this.tituloAlerta = tituloAlerta;
        this.descricaoAlerta = descricaoAlerta;
        this.tipoAlerta = tipoAlerta;
        this.momento = momento;
        this.fkComponenteMedida = fkComponenteMedida;
        this.fkEmpresa = fkEmpresa;
        this.fkServidor = fkServidor;
    }

    public String getTituloAlerta() {
        return tituloAlerta;
    }

    public void setTituloAlerta(String tituloAlerta) {
        this.tituloAlerta = tituloAlerta;
    }

    public String getDescricaoAlerta() {
        return descricaoAlerta;
    }

    public void setDescricaoAlerta(String descricaoAlerta) {
        this.descricaoAlerta = descricaoAlerta;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public LocalDateTime getMomento() {
        return momento;
    }

    public void setMomento(LocalDateTime momento) {
        this.momento = momento;
    }

    public Integer getFkComponenteMedida() {
        return fkComponenteMedida;
    }

    public void setFkComponenteMedida(Integer fkComponenteMedida) {
        this.fkComponenteMedida = fkComponenteMedida;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public Integer getFkServidor() {
        return fkServidor;
    }

    public void setFkServidor(Integer fkServidor) {
        this.fkServidor = fkServidor;
    }
}
