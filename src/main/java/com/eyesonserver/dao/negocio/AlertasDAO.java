package com.eyesonserver.dao.negocio;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.model.negocio.Alertas;
import org.springframework.jdbc.core.JdbcTemplate;

public class AlertasDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();

    public void insertAlerta(Alertas alerta) {
        db.update("INSERT INTO Eyes_On_Server.Alertas VALUES (null, ?, ?, ?, ?, ?, ?, ?)",
                alerta.getFkEmpresa(),
                alerta.getFkServidor(),
                alerta.getFkComponenteMedida(),
                alerta.getTituloAlerta(),
                alerta.getDescricaoAlerta(),
                alerta.getMomento(),
                alerta.getTipoAlerta()
        );
    }
}
