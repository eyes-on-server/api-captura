package com.eyesonserver.dao.negocio;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.database.ConexaoSqlServer;
import com.eyesonserver.model.negocio.Alertas;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

public class AlertasDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();
    private final JdbcTemplate sqlServer = new ConexaoSqlServer().getConexaoDoBanco();
    private final Dotenv dotenv = Dotenv.load();


    public void insertAlerta(Alertas alerta) {

        if (Objects.requireNonNull(dotenv.get("AMBIENTE")).equalsIgnoreCase("Produção")) {
            try {
                sqlServer.update("INSERT INTO Alertas (fk_empresa, fk_servidor, fk_componente_medida, titulo_alerta, descricao_alerta, data_hora_abertura, tipoAlerta) VALUES (?, ?, ?, ?, ?, ?, ?)",
                        alerta.getFkEmpresa(),
                        alerta.getFkServidor(),
                        alerta.getFkComponenteMedida(),
                        alerta.getTituloAlerta(),
                        alerta.getDescricaoAlerta(),
                        alerta.getMomento(),
                        alerta.getTipoAlerta()
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco Sql Server: " + e.fillInStackTrace());;
            }

            try {
                db.update("INSERT INTO Alertas VALUES (null, ?, ?, ?, ?, ?, ?, ?)",
                        alerta.getFkEmpresa(),
                        alerta.getFkServidor(),
                        alerta.getFkComponenteMedida(),
                        alerta.getTituloAlerta(),
                        alerta.getDescricaoAlerta(),
                        alerta.getMomento(),
                        alerta.getTipoAlerta()
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco Backup: " + e.fillInStackTrace());;
            }
        } else {
            try {
                db.update("INSERT INTO Alertas VALUES (null, ?, ?, ?, ?, ?, ?, ?)",
                        alerta.getFkEmpresa(),
                        alerta.getFkServidor(),
                        alerta.getFkComponenteMedida(),
                        alerta.getTituloAlerta(),
                        alerta.getDescricaoAlerta(),
                        alerta.getMomento(),
                        alerta.getTipoAlerta()
                );
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao banco Backup: " + e.fillInStackTrace());;
            }
        }
    }
}
