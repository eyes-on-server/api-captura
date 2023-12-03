package com.eyesonserver.dao.maquina;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.database.ConexaoSqlServer;
import com.eyesonserver.model.maquina.Processo;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Objects;

public class ProcessoDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();
    private final JdbcTemplate sqlServer = new ConexaoSqlServer().getConexaoDoBanco();

    private final Dotenv dotenv = Dotenv.load();



    public void insertProcessos(List<Processo> processos, Integer fkServidor) {

        if (Objects.requireNonNull(dotenv.get("AMBIENTE")).equalsIgnoreCase("Produção")) {
            for (Processo processo : processos) {
                try {
                    db.update(
                            "INSERT INTO Processos (id_processos, fk_servidor, pid_processos, nome_processos, uso_memoria_processos, uso_cpu_processos)" +
                                    " VALUES (?, ?, ?, ?, ?, ?)",
                            processo.getId(),
                            fkServidor,
                            processo.getPid(),
                            processo.getNome(),
                            processo.getUsoMemoria(),
                            processo.getUsoCpu());
                } catch (Exception ignored) {

                }

                try {
                    sqlServer.update(
                            "INSERT INTO Processos (id_processos, fk_servidor, pid_processos, nome_processos, uso_memoria_processos, uso_cpu_processos)" +
                                    " VALUES (?, ?, ?, ?, ?, ?)",
                            processo.getId(),
                            fkServidor,
                            processo.getPid(),
                            processo.getNome(),
                            processo.getUsoMemoria(),
                            processo.getUsoCpu());
                } catch (Exception ignored) {
                }

            }
        } else {
            for (Processo processo : processos) {
                try {
                    db.update(
                            "INSERT INTO Processos (id_processos, fk_servidor, pid_processos, nome_processos, uso_memoria_processos, uso_cpu_processos)" +
                                    " VALUES (?, ?, ?, ?, ?, ?)",
                            processo.getId(),
                            fkServidor,
                            processo.getPid(),
                            processo.getNome(),
                            processo.getUsoMemoria(),
                            processo.getUsoCpu());
                } catch (Exception ignored) {
                }
            }
        }

    }
}
