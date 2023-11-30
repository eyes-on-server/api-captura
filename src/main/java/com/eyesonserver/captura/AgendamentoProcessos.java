package com.eyesonserver.captura;

import java.util.Timer;
import java.util.TimerTask;

public class AgendamentoProcessos implements Runnable{
    private final int sleepTime;

    public AgendamentoProcessos(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {

        Captura captura = new Captura();
        TimerTask tarefaProcessos = new TimerTask() {
            @Override
            public void run() {
                captura.capturarProcessos();
            }
        };

        Timer timerProcessos = new Timer();
        timerProcessos.schedule(tarefaProcessos, 0, 10000);

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
