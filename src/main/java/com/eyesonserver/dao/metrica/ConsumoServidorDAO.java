package com.eyesonserver.dao.metrica;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.database.ConexaoSqlServer;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

public class ConsumoServidorDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();
    private final JdbcTemplate sqlServer = new ConexaoSqlServer().getConexaoDoBanco();
    private final Dotenv dotenv = Dotenv.load();

    public void insertConsumoServidor(Integer fkServidor, Double consumoGeral, LocalDateTime momento) {

        if (Objects.requireNonNull(dotenv.get("AMBIENTE")).equalsIgnoreCase("Produção")) {
            try {
                sqlServer.update("INSERT INTO Consumo_Servidor (fk_servidor, porcentagem_uso, momento) VALUES (?, ?, ?)",
                        fkServidor,
                        consumoGeral,
                        momento
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco Sql Server: " + e.fillInStackTrace());
            }

            try {
                db.update("INSERT INTO Consumo_Servidor (fk_servidor, porcentagem_uso, momento) VALUES (?, ?, ?)",
                        fkServidor,
                        consumoGeral,
                        momento
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco MYSQL: " + e.fillInStackTrace());
            }
        } else {
            try {
                db.update("INSERT INTO Consumo_Servidor (fk_servidor, porcentagem_uso, momento) VALUES (?, ?, ?)",
                        fkServidor,
                        consumoGeral,
                        momento
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco MYSQL: " + e.fillInStackTrace());
            }
        }

    }
}
