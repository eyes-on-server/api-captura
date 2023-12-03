package com.eyesonserver.utils;

import com.eyesonserver.dao.maquina.ComponenteMedidaDAO;
import com.eyesonserver.dao.maquina.ServidorDAO;
import com.eyesonserver.dao.metrica.ConsumoServidorDAO;
import com.eyesonserver.dao.metrica.DowntimeServidorDAO;
import com.eyesonserver.dao.metrica.RegistroDAO;
import com.eyesonserver.dao.metrica.ViewRegistrosDAO;
import com.eyesonserver.dao.negocio.AlertasDAO;
import com.eyesonserver.enumeradores.ComponentesMonitorados;
import com.eyesonserver.looca.cpu.CpuUso;
import com.eyesonserver.looca.disco.DiscoUso;
import com.eyesonserver.looca.memoria.MemoriaUso;
import com.eyesonserver.model.maquina.ComponenteMedida;
import com.eyesonserver.model.maquina.Servidor;
import com.eyesonserver.model.metrica.ViewRegistro;
import com.eyesonserver.model.negocio.Alertas;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class FuncoesIndividuais {

    public void verificarMetricas(ComponentesMonitorados componentesMonitorados, Object valor, LocalDateTime momento){

        ServidorDAO servidorDAO = new ServidorDAO();
        AlertasDAO alertasDAO = new AlertasDAO();
        ComponenteMedidaDAO componenteMedidaDAO = new ComponenteMedidaDAO();
        ComponenteMedida resultado = componenteMedidaDAO.coletarCompMedidaPorTipo(componentesMonitorados.name());
        Integer[] metricas = new Integer[3];

        metricas[0] = resultado.getValorAlertaEmergencia();
        metricas[1] = resultado.getValorAlertaPerigo();
        metricas[2] = resultado.getValorAlertaPrevencao();

        String tipo = "";

        Class<?> classe = valor.getClass();
        String tipoClasse = classe.getSimpleName();

    if (tipoClasse.equalsIgnoreCase("Long")) {
        valor = Double.valueOf((Long) valor);
    }

        if (metricas[0] != 0) {
            if ((Double) valor >= metricas[0]) {
                tipo = "Emergência";
            } else if ((Double) valor >= metricas[1]) {
                tipo = "Perigo";
            } else if ((Double) valor >= metricas[2]) {
                tipo = "Prevenção";
            } else {
                System.out.println("Componente não apresenta alerta\n");
            }
        } else {
            System.out.println("Componente não apresenta alerta\n");
        }

        if (!tipo.equalsIgnoreCase("")) {

            Servidor servidor = servidorDAO.getServidorPorMacAddress(InfoServidor.getMacAddressServidor());

            String titulo = "Alerta %s no servidor %s".formatted(
                    tipo, servidor.getNome()
            );

            String mensagem = "Detectamos que o/a %s registrou um valor de %f, entrando assim no estado de %s".formatted(
                    componentesMonitorados.getNome(), valor, tipo
            );

            alertasDAO.insertAlerta(new Alertas(
                    titulo,
                    mensagem,
                    tipo,
                    momento,
                    resultado.getIdComponenteMedida(),
                    servidor.getFkEmpresa(),
                    servidor.getId()
            ));
            System.out.println("Alerta Registrado!\n");
        }
    }

    public void calcularConsumoGeralServidor() {

        Double cpuUso = new CpuUso().executar();
        Double limiteCpu = .75;

        Double memoriaUso = new MemoriaUso().executar();
        Double limiteMemoria = .7;

        Double discoUso = new DiscoUso().executar();
        Double limiteDisco = .85;

        Integer fkServidor = new ServidorDAO().getServidorPorMacAddress(InfoServidor.getMacAddressServidor()).getId();

        double consumoGeral = (cpuUso * limiteCpu) - (memoriaUso * limiteMemoria) - (discoUso * limiteDisco);
        consumoGeral = Math.abs(consumoGeral);

        System.out.printf("Consumo do servidor: %f%%%n", consumoGeral);

        new ConsumoServidorDAO().insertConsumoServidor(
                fkServidor,
                consumoGeral,
                LocalDateTime.now()
        );
    }

    public void verificarDowntime() {

        Dotenv dotenv = Dotenv.load();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int margem = Objects.requireNonNull(dotenv.get("AMBIENTE")).equalsIgnoreCase("Produção") ? 60 : 25;
        double prejuizoPorSegundo = 1111111.1;

        ViewRegistrosDAO viewRegistrosDAO = new ViewRegistrosDAO();
        Integer fkServidor = new ServidorDAO().getServidorPorMacAddress(InfoServidor.getMacAddressServidor()).getId();

        LocalDateTime ultimoMomentoRegistro = LocalDateTime.parse(viewRegistrosDAO.consultarUltimoMomento(fkServidor).getMomento(), formatter);
        ViewRegistro penultimoMomentoRegistro = viewRegistrosDAO.consultarPenultimoMomento(fkServidor, ultimoMomentoRegistro);

        if (penultimoMomentoRegistro != null) {
            LocalDateTime penultimoMomento = LocalDateTime.parse(viewRegistrosDAO.consultarPenultimoMomento(fkServidor, ultimoMomentoRegistro).getMomento(), formatter);
            long diferencaSegundos = ChronoUnit.SECONDS.between(penultimoMomento, ultimoMomentoRegistro);

            if (diferencaSegundos > margem) {
                new DowntimeServidorDAO().inserirDowntime(
                        fkServidor,
                        diferencaSegundos - margem,
                        (double) (diferencaSegundos - margem) * prejuizoPorSegundo,
                        LocalDateTime.now()
                );
                System.out.println("Downtime Registrado!");
            }

        }

    }

}
