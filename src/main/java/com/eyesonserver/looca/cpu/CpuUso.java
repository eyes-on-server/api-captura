package com.eyesonserver.looca.cpu;

public class CpuUso extends Cpu{
    @Override
    public Double executar() {
        return (double) Math.round(super.lucas.getUso());
    }
}
