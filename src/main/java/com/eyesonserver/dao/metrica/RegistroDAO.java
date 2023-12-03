package com.eyesonserver.dao.metrica;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.database.ConexaoSqlServer;
import com.eyesonserver.model.metrica.Registro;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegistroDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();
    private final JdbcTemplate sqlServer = new ConexaoSqlServer().getConexaoDoBanco();
    private final Dotenv dotenv = Dotenv.load();


    public void insertRegistro(Registro registro, LocalDateTime momento) {

        if (Objects.requireNonNull(dotenv.get("AMBIENTE")).equalsIgnoreCase("Produção")) {
            try {
                sqlServer.update("INSERT INTO Registro (fk_componente_servidor, valor_registro, momento_registro) VALUES (?, ?, ?)",
                        registro.getFkComponenteServidor(),
                        registro.getValor(),
                        momento
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco SQL Server: " + e.fillInStackTrace());
            }

            try {
                db.update("INSERT INTO Registro VALUES (null, ?, ?, ?)",
                        registro.getFkComponenteServidor(),
                        registro.getValor(),
                        momento
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco Backup: " + e.fillInStackTrace());
            }
        } else {
            try {
                db.update("INSERT INTO Registro VALUES (null, ?, ?, ?)",
                        registro.getFkComponenteServidor(),
                        registro.getValor(),
                        momento
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco Backup: " + e.fillInStackTrace());
            }
        }

    }
}
