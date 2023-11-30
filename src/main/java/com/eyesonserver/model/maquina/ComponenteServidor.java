package com.eyesonserver.model.maquina;

public class ComponenteServidor {
    private String empresa;
    private String servidor;
    private String sistemaOperacional;
    private String macAddress;
    private String local;
    private String tipo;
    private String componente;
    private String medida;
    private Integer idComponenteServidor;

    public ComponenteServidor(String empresa, String servidor, String sistemaOperacional, String macAddress, String local, String tipo, String componente, String medida, Integer idComponenteServidor) {
        this.empresa = empresa;
        this.servidor = servidor;
        this.sistemaOperacional = sistemaOperacional;
        this.macAddress = macAddress;
        this.local = local;
        this.tipo = tipo;
        this.componente = componente;
        this.medida = medida;
        this.idComponenteServidor = idComponenteServidor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Integer getIdComponenteServidor() {
        return idComponenteServidor;
    }

    public void setIdComponenteServidor(Integer idComponenteServidor) {
        this.idComponenteServidor = idComponenteServidor;
    }
}
