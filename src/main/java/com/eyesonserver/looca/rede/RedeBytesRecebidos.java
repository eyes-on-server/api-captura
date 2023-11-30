package com.eyesonserver.looca.rede;

import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.util.List;

public class RedeBytesRecebidos extends Rede{
    @Override
    public Long executar() {
        Long receivedBytes = 0L;

        List<RedeInterface> interfacesRede = super.lucas.getGrupoDeInterfaces().getInterfaces();

        for (RedeInterface redeInterface : interfacesRede) {
            if(redeInterface.getNome().startsWith("e")) {
                receivedBytes = redeInterface.getBytesRecebidos();
                break;
            }
        }

        return receivedBytes;
    }
}
