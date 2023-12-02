package com.eyesonserver.captura;

import com.eyesonserver.dao.maquina.ComponenteServidorDAO;
import com.eyesonserver.dao.maquina.ProcessoDAO;
import com.eyesonserver.dao.metrica.RegistroDAO;
import com.eyesonserver.enumeradores.ComponentesMonitorados;
import com.eyesonserver.model.maquina.Processo;
import com.eyesonserver.model.metrica.Registro;
import com.eyesonserver.utils.FuncoesIndividuais;
import com.eyesonserver.utils.InfoServidor;
import com.github.britooo.looca.api.core.Looca;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Captura {

        public void capturarRegistros(List<ComponentesMonitorados> executaveis) {

            ComponenteServidorDAO componenteServidorDAO = new ComponenteServidorDAO();
            RegistroDAO registroDAO = new RegistroDAO();
            LocalDateTime momento = LocalDateTime.now();
            FuncoesIndividuais funcoesIndividuais = new FuncoesIndividuais();

            executaveis.forEach(item -> {
                Integer idComponenteServidor = componenteServidorDAO.coletarIdComponenteServidorPorMacaddressTipo(InfoServidor.getMacAddressServidor(), item.name()).getIdComponenteServidor();
                Object valorRegistro = item.getMetodo().executar();

                registroDAO.insertRegistro(new Registro(
                        idComponenteServidor, valorRegistro
                ), momento);

                System.out.printf("%s: %s%n", item.getNome(), valorRegistro);

                funcoesIndividuais.verificarMetricas(item, valorRegistro, momento);

            });
        }

        public void capturarProcessos() {
            List<Processo> processos = new ArrayList<>();

            Looca looca = new Looca();
            List<com.github.britooo.looca.api.group.processos.Processo> processosLooca = looca.getGrupoDeProcessos().getProcessos();

            for (com.github.britooo.looca.api.group.processos.Processo processo : processosLooca) {
                processos.add(new Processo(0, processo.getPid(), processo.getNome(), processo.getUsoMemoria(), processo.getUsoCpu())
                );
            }

            ProcessoDAO processoDAO = new ProcessoDAO();
            processoDAO.insertProcessos(processos, 6);
        }


}
