package com.eyesonserver.looca.disco;

import com.github.britooo.looca.api.group.discos.Volume;

public class DiscoUso extends Disco{
    @Override
    public void executar() {
        Long totalDisco = super.lucas.getTamanhoTotal();
        Long discoDisponivel = 0L;

        for(Volume v : super.lucas.getVolumes()) {
            discoDisponivel += v.getDisponivel();
        }

        Double discoUso = (double) (discoDisponivel / totalDisco * 100);
    }
}
