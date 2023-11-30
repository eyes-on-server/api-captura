package com.eyesonserver.looca.temperatura;

import com.eyesonserver.looca.cpu.Cpu;
import com.github.britooo.looca.api.core.Looca;

public class CpuTemperatura extends Temperatura {

    @Override
    public Object executar() {
        return lucas.getTemperatura();
    }
}
