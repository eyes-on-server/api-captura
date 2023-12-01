package com.eyesonserver.looca.memoria;

public class MemoriaUso extends Memoria{
    @Override
    public Double executar() {
        Double memoriaEmUso = (double) super.lucas.getEmUso();
        Double memoriaTotal = (double) super.lucas.getTotal();

        return (double) Math.round((memoriaEmUso / memoriaTotal * 100));
    }
}


