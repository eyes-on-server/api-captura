package com.eyesonserver.model.maquina;

import com.eyesonserver.model.metrica.Registro;

import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private Integer id;
    private Integer fkEmpresa;
    private String nome;
    private String local;
    private String ipv6;
    private String so;
    private String macAdress;

    public Servidor(Integer id, Integer fkEmpresa, String nome, String local, String ipv6, String so, String macAdress) {
        this.id = id;
        this.fkEmpresa = fkEmpresa;
        this.nome = nome;
        this.local = local;
        this.ipv6 = ipv6;
        this.so = so;
        this.macAdress = macAdress;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public Integer getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    @Override
    public String toString () {
        return """
                
                Nome: %s
                Local: %s
                IPV6: %s
                MacAddress: %s
                """.formatted (nome, local, ipv6, macAdress);
    }
}
