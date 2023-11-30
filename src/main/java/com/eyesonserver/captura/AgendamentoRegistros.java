package com.eyesonserver.captura;

import com.eyesonserver.enumeradores.ComponentesMonitorados;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AgendamentoRegistros implements Runnable{

    private final int sleepTime;

    public AgendamentoRegistros(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        List<ComponentesMonitorados> executaveis = new ObterComponentes().obterExecutaveis();
        Captura captura = new Captura();

        TimerTask tarefaRegistros = new TimerTask() {
            @Override
            public void run() {
                captura.capturarRegistros(executaveis);
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
