package com.eyesonserver.model.maquina;

public class ComponenteMedida {
    private Integer idComponenteMedida;
    private String nomeComponenteMedida;
    private String tipo;
    private Integer fkComponente;
    private Integer fkMedida;

    public ComponenteMedida(Integer idComponenteMedida, String nomeComponenteMedida, String tipo, Integer fkComponente, Integer fkMedida) {
        this.idComponenteMedida = idComponenteMedida;
        this.nomeComponenteMedida = nomeComponenteMedida;
        this.tipo = tipo;
        this.fkComponente = fkComponente;
        this.fkMedida = fkMedida;
    }

    public Integer getIdComponenteMedida() {
        return idComponenteMedida;
    }

    public void setIdComponenteMedida(Integer idComponenteMedida) {
        this.idComponenteMedida = idComponenteMedida;
    }

    public String getNomeComponenteMedida() {
        return nomeComponenteMedida;
    }

    public void setNomeComponenteMedida(String nomeComponenteMedida) {
        this.nomeComponenteMedida = nomeComponenteMedida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getFkComponente() {
        return fkComponente;
    }

    public void setFkComponente(Integer fkComponente) {
        this.fkComponente = fkComponente;
    }

    public Integer getFkMedida() {
        return fkMedida;
    }

    public void setFkMedida(Integer fkMedida) {
        this.fkMedida = fkMedida;
    }
}
