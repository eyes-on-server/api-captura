package com.eyesonserver.captura;

import com.eyesonserver.dao.maquina.ProcessoDAO;
import com.eyesonserver.dao.metrica.RegistroDAO;
import com.eyesonserver.model.maquina.Processo;
import com.eyesonserver.model.metrica.Registro;
import com.github.britooo.looca.api.core.Looca;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Captura implements Runnable {
        private Thread worker;
        private final AtomicBoolean running = new AtomicBoolean(false);
        private int interval;
        private Integer idComponenteServidor;

        public Captura(int sleepInterval, Integer idComponenteServidor) {

                this.interval = sleepInterval;
                this.idComponenteServidor = idComponenteServidor;
        }

        public void start() {
                worker = new Thread(this);
                worker.start();
        }

        public void stop() {
                running.set(false);
        }

        @Override
        public void run() {
                running.set(true);
                while (running.get()) {
                        try {
                                Thread.sleep(interval);
                        } catch (InterruptedException e){
                                Thread.currentThread().interrupt();
                                System.out.println(
                                        "Essa thread foi interrompida; falha ao completar operação.");
                        }

                        Looca looca = new Looca();
                        RegistroDAO registroDAO = new RegistroDAO();
                        ProcessoDAO processoDAO = new ProcessoDAO();

                        LocalDateTime horaAtual = LocalDateTime.now();
                        DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        formatarHora.format(horaAtual);

                        String cpuFrequencia;
                        String cpuPorcentagemUso;
                        String redeBytesEnviados;
                        String redeBytesRecebidos;
                        String discoPorcentagemUso;
                        String memoriaPorcentagemUso;
                        List<Processo> processos = new ArrayList<>();


                        // Capturando a frequencia da cpu:
                        cpuFrequencia = String.valueOf(looca.getProcessador().getFrequencia() / 1000000000);

                        // Capturando a porcentagem de uso da cpu:
                        cpuPorcentagemUso = String.valueOf(looca.getProcessador().getUso().shortValue());

                        // Capturando a rede -- Bytes enviados:
                        redeBytesEnviados = String.valueOf(looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getBytesEnviados());

                        // Capturando a rede -- Bytes Recebidos:
                        redeBytesRecebidos = String.valueOf(looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getBytesRecebidos());



                        // Capturando a porcentagem de uso do disco:
                        long discoTotal = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal() / 1000000000;
                        long discoDisponivel = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / 1000000000;
                        discoPorcentagemUso = String.valueOf((discoTotal - discoDisponivel) * 100 / discoTotal);


                        // Capturando a porcentagem de uso da memória:
                        long memoriaTotal = looca.getMemoria().getTotal() / 100000000;
                        long memoriaEmUso = looca.getMemoria().getEmUso() / 100000000;
                        memoriaPorcentagemUso = String.valueOf(memoriaEmUso * 100 / memoriaTotal);


                        // Criando registros:
                        Registro memoriaPorcentagemUsoRegistro = new Registro(idComponenteServidor, memoriaPorcentagemUso, horaAtual);
                        Registro discoPorcentagemUsoRegistro = new Registro(idComponenteServidor, discoPorcentagemUso, horaAtual);
                        Registro cpuFrequenciaRegistro = new Registro(idComponenteServidor, cpuFrequencia, horaAtual);
                        Registro cpuPorcentagemUsoRegistro = new Registro(idComponenteServidor, cpuPorcentagemUso, horaAtual);
                        Registro redeByteEnviadosRegistro = new Registro(idComponenteServidor, redeBytesEnviados, horaAtual);
                        Registro redeByteRecebidosRegistro = new Registro(idComponenteServidor, redeBytesRecebidos, horaAtual);

                        List<com.github.britooo.looca.api.group.processos.Processo> processosLooca = looca.getGrupoDeProcessos().getProcessos();

                        // Insert no banco de dados:
                        registroDAO.insertRegistro(memoriaPorcentagemUsoRegistro);
                        registroDAO.insertRegistro(discoPorcentagemUsoRegistro);
                        registroDAO.insertRegistro(cpuFrequenciaRegistro);
                        registroDAO.insertRegistro(cpuPorcentagemUsoRegistro);
                        registroDAO.insertRegistro(redeByteEnviadosRegistro);
                        registroDAO.insertRegistro(redeByteRecebidosRegistro);

                        for (com.github.britooo.looca.api.group.processos.Processo processo : processosLooca) {
                                processos.add(new Processo(0, processo.getPid(), processo.getNome(), processo.getUsoMemoria(), processo.getUsoCpu())
                                );
                        }
                        processoDAO.insertProcessos(processos, 6);

                }
        }
}
