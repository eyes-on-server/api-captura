package com.eyesonserver;

import com.eyesonserver.dao.maquina.ComponenteMedidaDAO;
import com.eyesonserver.dao.maquina.ComponenteServidorDAO;
import com.eyesonserver.dao.maquina.ServidorDAO;
import com.eyesonserver.dao.metrica.RegistroDAO;
import com.eyesonserver.dao.negocio.UsuarioDAO;
import com.eyesonserver.enumeradores.ComponentesMonitorados;
import com.eyesonserver.interfaces.Executavel;
import com.eyesonserver.model.maquina.ComponenteServidor;
import com.eyesonserver.model.maquina.Servidor;
import com.eyesonserver.model.metrica.Registro;
import com.eyesonserver.model.negocio.Usuario;
import com.eyesonserver.utils.InfoServidor;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;

import java.time.LocalDateTime;
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
        List<ComponentesMonitorados> executaveis = new ArrayList<>();
        List<ComponentesMonitorados> componentesMonitorados;
        List<ComponenteServidor> listaComponentesServidor;
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


        System.out.printf("\n\n\nOlá %s |", login.getNomeUsuario());

        System.out.print("Sistema operacional: ");
        System.out.println(InfoServidor.getSoServidor() + "\n\n");

        System.out.printf ("""
                Servidor monitorado: %s
                %n""", servidorAtual);

        System.out.println("Iniciando monitoramento do servidor: ");

        for (ComponentesMonitorados comp : componentesMonitorados) {
            for (ComponenteServidor componenteServidor : listaComponentesServidor) {
                if (comp.name().equalsIgnoreCase(componenteServidor.getTipo())) {
                    executaveis.add(comp);
                    break;
                }
            }
        }

        RegistroDAO registroDAO = new RegistroDAO();
        LocalDateTime momento = LocalDateTime.now();

        executaveis.forEach(item -> {
            Integer idComponenteServidor = componenteServidorDAO.coletarIdComponenteServidorPorMacaddressTipo(InfoServidor.getMacAddressServidor(), item.name()).getIdComponenteServidor();
            Object valorRegistro = item.getMetodo().executar();

            registroDAO.insertRegistro(new Registro(
                    idComponenteServidor, valorRegistro
            ), momento);

            System.out.printf("%s: %s%n", item.getNome(), valorRegistro);

        });
    }
}
