package com.eyesonserver.captura;

import com.eyesonserver.enumeradores.ComponentesMonitorados;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AgendamentoRegistros implements Runnable{

    private final int sleepTime;
    private final ObterComponentes obterComponentes = new ObterComponentes();
    private int ticks = 0;
    public AgendamentoRegistros(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        Captura captura = new Captura();

        TimerTask tarefaRegistros = new TimerTask() {
            @Override
            public void run() {
                captura.capturarRegistros(obterComponentes.obterExecutaveis());
            }
        };

        Timer timerRegistros = new Timer();
        timerRegistros.schedule(tarefaRegistros, 0, 10000);

        try {
            Thread.sleep (sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
