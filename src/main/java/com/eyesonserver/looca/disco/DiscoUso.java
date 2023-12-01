package com.eyesonserver.looca.disco;

import com.github.britooo.looca.api.group.discos.Volume;

public class DiscoUso extends Disco{
    @Override
    public Double executar() {
        Double totalDisco = (double) super.lucas.getTamanhoTotal();
        Double discoDisponivel = 0.0;

        for(Volume v : super.lucas.getVolumes()) {
            discoDisponivel += (double) v.getDisponivel();
        }

       return (double) Math.round(100 - (discoDisponivel / totalDisco * 100));
    }
}
