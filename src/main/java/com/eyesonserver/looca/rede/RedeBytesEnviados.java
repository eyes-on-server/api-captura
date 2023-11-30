package com.eyesonserver.looca.rede;

import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.util.List;

public class RedeBytesEnviados extends Rede{
    @Override
    public Long executar() {
        Long sentBytes = 0L;

        List<RedeInterface> interfacesRede = super.lucas.getGrupoDeInterfaces().getInterfaces();

        for (RedeInterface redeInterface : interfacesRede) {
            if(redeInterface.getNome().startsWith("e")) {
                sentBytes = redeInterface.getBytesEnviados();
                break;
            }
        }

        return sentBytes;
    }
}
