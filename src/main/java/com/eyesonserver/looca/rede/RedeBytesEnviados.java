package com.eyesonserver.looca.rede;

import com.github.britooo.looca.api.group.rede.RedeInterface;

public class RedeBytesEnviados extends Rede{
    @Override
    public Long executar() {
        Long sentBytes = 0L;

        for(RedeInterface i : lucas.getGrupoDeInterfaces().getInterfaces()) {
            if (!i.getEnderecoIpv4().isEmpty()) {
                sentBytes = i.getBytesEnviados();
                break;
            }
        }

        return sentBytes;
    }
}
