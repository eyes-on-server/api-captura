package com.eyesonserver.looca.disco;

import com.eyesonserver.interfaces.Executavel;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;

public abstract class Disco implements Executavel {
    protected final DiscoGrupo lucas = new Looca().getGrupoDeDiscos();
}
