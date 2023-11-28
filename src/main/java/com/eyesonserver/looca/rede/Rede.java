package com.eyesonserver.looca.rede;

import com.eyesonserver.interfaces.Executavel;
import com.github.britooo.looca.api.core.Looca;

public abstract class Rede implements Executavel {
    protected final com.github.britooo.looca.api.group.rede.Rede lucas = new Looca().getRede();
}
