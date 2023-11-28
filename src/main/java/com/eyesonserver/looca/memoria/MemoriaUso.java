package com.eyesonserver.looca.memoria;

public class MemoriaUso extends Memoria{
    @Override
    public void executar() {
        Double memoriaEmUso = (double) ((super.lucas.getEmUso() / super.lucas.getTotal()) * 100);
    }
}
