package com.eyesonserver.looca.temperatura;

import com.eyesonserver.interfaces.Executavel;
import com.github.britooo.looca.api.core.Looca;

public abstract class Temperatura implements Executavel {

    protected com.github.britooo.looca.api.group.temperatura.Temperatura lucas = new Looca().getTemperatura();

}
