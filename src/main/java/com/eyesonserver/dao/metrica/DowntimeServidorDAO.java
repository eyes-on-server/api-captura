package com.eyesonserver.dao.metrica;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.database.ConexaoSqlServer;
import com.eyesonserver.utils.InfoServidor;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Objects;


public class DowntimeServidorDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();
    private final JdbcTemplate sqlServer = new ConexaoSqlServer().getConexaoDoBanco();
    private final Dotenv dotenv = Dotenv.load();

    public void inserirDowntime(Integer fkServidor, Long tempo, Double prejuizo, LocalDateTime momento) {

        if (Objects.requireNonNull(dotenv.get("AMBIENTE")).equalsIgnoreCase("Produção")) {
            try {
                sqlServer.update("INSERT INTO Downtime (fk_servidor, tempo_downtime, prejuizo, momento) VALUES (?, ?, ?, ?)",
                        fkServidor,
                        tempo,
                        prejuizo,
                        momento
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco Sql Server: " + e.fillInStackTrace());
            }

            try {
                db.update("INSERT INTO Downtime (fk_servidor, tempo_downtime, prejuizo, momento) VALUES (?, ?, ?, ?)",
                        fkServidor,
                        tempo,
                        prejuizo,
                        momento
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco MYSQL: " + e.fillInStackTrace());
            }
        } else {
            try {
                db.update("INSERT INTO Downtime (fk_servidor, tempo_downtime, prejuizo, momento) VALUES (?, ?, ?, ?)",
                        fkServidor,
                        tempo,
                        prejuizo,
                        momento
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco MYSQL: " + e.fillInStackTrace());
            }
        }

    }

}
