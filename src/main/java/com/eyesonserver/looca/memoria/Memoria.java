package com.eyesonserver.looca.memoria;

import com.eyesonserver.interfaces.Executavel;
import com.github.britooo.looca.api.core.Looca;

public abstract class Memoria implements Executavel {
    protected final com.github.britooo.looca.api.group.memoria.Memoria lucas = new Looca().getMemoria();

}
