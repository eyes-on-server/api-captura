package com.eyesonserver.model.maquina;

public class ComponenteMedida {
    private Integer idComponenteMedida;
    private String nomeComponenteMedida;
    private String tipo;
    private Integer valorAlertaEmergencia;
    private Integer valorAlertaPerigo;
    private Integer valorAlertaPrevencao;
    private Integer fkComponente;
    private Integer fkMedida;

    public ComponenteMedida(Integer idComponenteMedida, String nomeComponenteMedida, String tipo, Integer valorAlertaEmergencia, Integer valorAlertaPerigo, Integer valorAlertaPrevencao, Integer fkComponente, Integer fkMedida) {
        this.idComponenteMedida = idComponenteMedida;
        this.nomeComponenteMedida = nomeComponenteMedida;
        this.tipo = tipo;
        this.valorAlertaEmergencia = valorAlertaEmergencia;
        this.valorAlertaPerigo = valorAlertaPerigo;
        this.valorAlertaPrevencao = valorAlertaPrevencao;
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

    public Integer getValorAlertaEmergencia() {
        return valorAlertaEmergencia;
    }

    public void setValorAlertaEmergencia(Integer valorAlertaEmergencia) {
        this.valorAlertaEmergencia = valorAlertaEmergencia;
    }

    public Integer getValorAlertaPerigo() {
        return valorAlertaPerigo;
    }

    public void setValorAlertaPerigo(Integer valorAlertaPerigo) {
        this.valorAlertaPerigo = valorAlertaPerigo;
    }

    public Integer getValorAlertaPrevencao() {
        return valorAlertaPrevencao;
    }

    public void setValorAlertaPrevencao(Integer valorAlertaPrevencao) {
        this.valorAlertaPrevencao = valorAlertaPrevencao;
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
