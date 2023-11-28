package com.eyesonserver;

import com.eyesonserver.captura.Captura;
import com.eyesonserver.dao.maquina.ComponenteMedidaDAO;
import com.eyesonserver.dao.maquina.ComponenteServidorDAO;
import com.eyesonserver.dao.maquina.ServidorDAO;
import com.eyesonserver.dao.negocio.UsuarioDAO;
import com.eyesonserver.enumeradores.ComponentesMonitorados;
import com.eyesonserver.interfaces.Executavel;
import com.eyesonserver.model.maquina.Servidor;
import com.eyesonserver.model.negocio.Usuario;
import com.eyesonserver.utils.InfoServidor;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class App {
    private static final Integer escolhadoDoMenu = 0;
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final ServidorDAO servidorDAO = new ServidorDAO();

    private static final ComponenteServidorDAO componenteServidorDAO = new ComponenteServidorDAO();
    private static final ComponenteMedidaDAO componenteMedidaDAO = new ComponenteMedidaDAO();

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        String email;
        String senha;
        Usuario login = null;
        List<Usuario> loginsEncontrados;

        System.out.print("""                                
                    ______                   ____           _____                         \s
                   / ____/_  _____  _____   / __ \\____     / ___/___  ______   _____  _____
                  / __/ / / / / _ \\/ ___/  / / / / __ \\    \\__ \\/ _ \\/ ___/ | / / _ \\/ ___/
                 / /___/ /_/ /  __(__  )  / /_/ / / / /   ___/ /  __/ /   | |/ /  __/ /   \s
                /_____/\\__, /\\___/____/   \\____/_/ /_/   /____/\\___/_/    |___/\\___/_/    \s
                      /____/                                                                            
                                
                Seja muito bem-vindo(a) a API Java da Eyes On Server!
                """);



        do {
            System.out.print("Login: ");
            email = leitor.nextLine();

            System.out.print("Senha: ");
            senha = leitor.nextLine();

            loginsEncontrados = usuarioDAO.getUsuarioPorEmailSenha(email, senha);

            if (loginsEncontrados.isEmpty()) {
                System.out.println("Usuário não encontrado!\n");
            } else {
                login = loginsEncontrados.get(0);
            }
        } while (login == null);

        String macAddress = InfoServidor.getMacAddressServidor();
        List<Executavel> executaveis = new ArrayList<>();
        List<ComponentesMonitorados> componentesMonitorados;
        List<String> listaComponentesServidor;
        Servidor servidorAtual;

        do {
            servidorAtual = servidorDAO.getServidorPorMacAddress(macAddress);
            componentesMonitorados = new ArrayList<>(List.of(ComponentesMonitorados.values()));
            listaComponentesServidor = componenteServidorDAO.consultarComponenteServidorPorMacAddress(macAddress);

            if(Objects.isNull(servidorAtual)) {
                System.out.println("Novo servidor detectado, iniciando protocolo de instalação!");

                System.out.print ("Insira o local do servidor: ");
                String local = leitor.nextLine ();

                servidorDAO.insertServidor(login.getIdEmpresa(), local);
                Integer idServidor = servidorDAO.getServidorPorMacAddress(macAddress).getId();

                List<ComponentesMonitorados> componentesSelecionados = new ArrayList<>();

                System.out.println("\nSelecione os componentes que seja monitorar:");
                Scanner inputNumber = new Scanner(System.in);

                int i;
                int resposta;

                do {
                    for (i = 0; i < componentesMonitorados.size(); i++) {
                        System.out.printf("%d - %s%n", i + 1, componentesMonitorados.get(i).getNome());
                    }

                    i++;
                    System.out.printf("%d - Finalizar%n", i);

                    resposta = inputNumber.nextInt();

                    if (resposta == i) {
                        System.out.println("Finalizando cadastro!");
                    }

                    componentesSelecionados.add(componentesMonitorados.get(resposta - 1));
                    componentesMonitorados.remove(componentesMonitorados.get(resposta - 1));

                } while ((resposta > i || resposta < 0) || resposta != i);

                componentesSelecionados.forEach(item -> {
                    componenteServidorDAO.inserirComponenteServidor(idServidor, componenteMedidaDAO.coletarId(item.name()));
                });

            }

        } while(Objects.isNull(servidorAtual));

//        int idComponenteServidor = componenteServidorDAO.getIdComponenteServidorPorIdServidor(servidorAtual.getId());
//
//        Captura threadCaptura = new Captura(8000, idComponenteServidor);
//        threadCaptura.start();

        System.out.printf("\n\n\nOlá %s |", login.getNomeUsuario());

        System.out.print("Sistema operacional: ");
        System.out.println(InfoServidor.getSoServidor() + "\n\n");

        System.out.printf ("""
                Servidor monitorado: %s
                %n""", servidorAtual);

        System.out.println("Iniciando monitoramento do servidor: ");

        for (ComponentesMonitorados comp : componentesMonitorados) {
            for (String tipo : listaComponentesServidor) {
                if (comp.name().equalsIgnoreCase(tipo)) {
                    executaveis.add(comp.getMetodo());
                    break;
                }
            }
        }

        executaveis.forEach(Executavel::executar);

//        do {
//            System.out.println("""
//                    Escolha uma opção:
//                    1. Disco
//                    2. Memoria
//                    3. Rede
//                    4. CPU
//                    5. Todos os Componentes
//                    6. Processos
//                    7. Sair
//                    """);
//            System.out.print("Escolha: ");
//            escolhadoDoMenu = leitor.nextInt();
//
//            switch (escolhadoDoMenu) {
//                case 1 -> {
//                    System.out.println("Opção (Disco) escolhida!");
//                    long discoTotal = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal() / 1000000000;
//                    long discoDisponivel = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / 1000000000;
//                    System.out.print("Porcentagem de uso: ");
//                    System.out.print((discoTotal - discoDisponivel) * 100 / discoTotal);
//                    System.out.println(" %");
//                }
//                case 2 -> {
//                    System.out.println("Opção (Memória) escolhida! ");
//                    long memoriaTotal = looca.getMemoria().getTotal() / 100000000;
//                    long memoriaEmUso = looca.getMemoria().getEmUso() / 100000000;
//                    System.out.print("Porcentagem de uso: ");
//                    System.out.print(memoriaEmUso * 100 / memoriaTotal);
//                    System.out.println(" %");
//                }
//                case 3 -> {
//                    System.out.println("Opção (Rede) escolhida! ");
//                    System.out.print("Bytes enviados: ");
//                    System.out.print(looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getBytesEnviados());
//                    System.out.println(" bytes");
//                    System.out.print("Bytes recebidos: ");
//                    System.out.print(looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getBytesRecebidos());
//                    System.out.println(" bytes");
//                }
//                case 4 -> {
//                    System.out.println("Opção (CPU) escolhida!");
//                    System.out.print("Frequência de CPU: ");
//                    System.out.print(looca.getProcessador().getFrequencia() / 1000000000);
//                    System.out.println(" Hz");
//                }
//                case 5 -> {
//                    System.out.println("Opção (Todos) escolhida! ");
//                    long discoTotal = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal() / 1000000000;
//                    long discoDisponivel = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel() / 1000000000;
//                    System.out.print("Porcentagem de uso do disco: ");
//                    System.out.print((discoTotal - discoDisponivel) * 100 / discoTotal);
//                    System.out.println(" %");
//                    long memoriaTotal = looca.getMemoria().getTotal() / 100000000;
//                    long memoriaEmUso = looca.getMemoria().getEmUso() / 100000000;
//                    System.out.print("Porcentagem de uso da memória: ");
//                    System.out.print(memoriaEmUso * 100 / memoriaTotal);
//                    System.out.println(" %");
//                    System.out.print("Bytes enviados: ");
//                    System.out.print(looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getBytesEnviados());
//                    System.out.println(" bytes");
//                    System.out.print("Bytes recebidos: ");
//                    System.out.print(looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getBytesRecebidos());
//                    System.out.println(" bytes");
//                    System.out.print("Frequência de CPU: ");
//                    System.out.print(looca.getProcessador().getFrequencia() / 1000000000);
//                    System.out.println(" Hz");
//                }
//                case 6 -> {
//                    System.out.println("Nome Processo; PID; % Uso CPU; % Uso RAM; MB utilizados; VRAM Utilizada");
//                    ProcessoGrupo prcs = looca.getGrupoDeProcessos();
//                    for (com.github.britooo.looca.api.group.processos.Processo processo : prcs.getProcessos()) {
//                        System.out.printf("%s; %d; %.1f; %.1f; %.1f; %.1f\n",
//                                processo.getNome(),
//                                processo.getPid(),
//                                processo.getUsoCpu(),
//                                processo.getUsoMemoria(),
//                                processo.getBytesUtilizados() / Math.pow(10, 6),
//                                processo.getMemoriaVirtualUtilizada() / Math.pow(10, 6)
//                        );
//                    }
//                }
//                case 7 -> {
//                    System.out.println("Até mais!");
//                    leitor.close();
//                    threadCaptura.stop();
//                }
//                default -> System.out.println("Escolha inválida!");
//            }
//        } while (escolhadoDoMenu != 7);
    }
}
