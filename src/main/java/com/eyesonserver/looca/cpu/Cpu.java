package com.eyesonserver.looca.cpu;

import com.eyesonserver.interfaces.Executavel;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

public abstract class Cpu implements Executavel {
    protected final Processador lucas = new Looca().getProcessador();
}
