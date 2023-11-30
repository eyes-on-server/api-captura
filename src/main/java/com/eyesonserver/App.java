package com.eyesonserver;


import com.eyesonserver.captura.AgendamentoProcessos;
import com.eyesonserver.captura.AgendamentoRegistros;


public class App {


    public static void main(String[] args) {

        Thread thread1 = new Thread(new AgendamentoRegistros(3000));
        Thread thread2 = new Thread(new AgendamentoProcessos(3000));

        thread1.start();
        System.out.println("Iniciando monitoramento do servidor: ");
        thread2.start();

    }
}
