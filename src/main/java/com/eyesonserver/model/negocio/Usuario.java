package com.eyesonserver.model.negocio;

public class Usuario {
    private String nomeUsuario;
    private Integer idEmpresa;
    private String login;
    private String senha;

    public Usuario(String nomeUsuario, Integer idEmpresa, String login, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.idEmpresa = idEmpresa;
        this.login = login;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
