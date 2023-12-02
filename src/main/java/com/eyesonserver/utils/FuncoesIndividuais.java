package com.eyesonserver.utils;

import com.eyesonserver.dao.maquina.ComponenteMedidaDAO;
import com.eyesonserver.dao.maquina.ServidorDAO;
import com.eyesonserver.dao.negocio.AlertasDAO;
import com.eyesonserver.enumeradores.ComponentesMonitorados;
import com.eyesonserver.model.maquina.ComponenteMedida;
import com.eyesonserver.model.maquina.Servidor;
import com.eyesonserver.model.negocio.Alertas;

import java.time.LocalDateTime;

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

}
