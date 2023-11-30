package com.eyesonserver.looca.memoria;

public class MemoriaUso extends Memoria{
    @Override
    public Double executar() {
        return (double) ((super.lucas.getEmUso() / super.lucas.getTotal()) * 100);
    }
}
