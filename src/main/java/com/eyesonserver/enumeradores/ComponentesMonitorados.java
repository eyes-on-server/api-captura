package com.eyesonserver.enumeradores;


import com.eyesonserver.interfaces.Executavel;
import com.eyesonserver.looca.cpu.CpuFrequencia;
import com.eyesonserver.looca.cpu.CpuUso;
import com.eyesonserver.looca.disco.DiscoUso;
import com.eyesonserver.looca.memoria.MemoriaUso;
import com.eyesonserver.looca.rede.RedeBytesEnviados;
import com.eyesonserver.looca.rede.RedeBytesRecebidos;
import com.eyesonserver.looca.temperatura.CpuTemperatura;

public enum ComponentesMonitorados {
    USO_PORCENTAGEM_CPU("Uso da CPU (%)", new CpuUso()),
    FREQUENCIA_CPU("Frequência da CPU (Htz)", new CpuFrequencia()),
    USO_MEMORIA_PORCENTAGEM("Uso da Memória (%)", new MemoriaUso()),
    USO_DISCO_PORCENTAGEM( "Uso do Disco (%)", new DiscoUso()),
    BYTES_ENVIADOS_REDE( "Bytes Enviados", new RedeBytesEnviados()),
    BYTES_RECEBIDOS_REDE( "Bytes Recebidos", new RedeBytesRecebidos()),
    TEMPERATURA_CPU("Temperatura Cpu (ºC)", new CpuTemperatura());

    private final String nome;
    private final Executavel metodo;

    ComponentesMonitorados(String nome, Executavel metodo) {
        this.nome = nome;
        this.metodo = metodo;
    }

    public String getNome() {
        return nome;
    }

    public Executavel getMetodo() {
        return metodo;
    }
}
