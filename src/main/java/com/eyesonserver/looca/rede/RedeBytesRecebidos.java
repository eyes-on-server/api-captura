package com.eyesonserver.looca.rede;

import com.github.britooo.looca.api.group.rede.RedeInterface;

public class RedeBytesRecebidos extends Rede{
    @Override
    public Long executar() {
        Long receivedBytes = 0L;

        for(RedeInterface i : lucas.getGrupoDeInterfaces().getInterfaces()) {
            if (!i.getEnderecoIpv4().isEmpty()) {
                receivedBytes = i.getBytesRecebidos();
                break;
            }
        }

        return receivedBytes;
    }
}
