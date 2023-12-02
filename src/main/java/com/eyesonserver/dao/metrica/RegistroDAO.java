package com.eyesonserver.dao.metrica;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.model.metrica.Registro;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

public class RegistroDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();

    public void insertRegistro(Registro registro, LocalDateTime momento) {
        db.update("INSERT INTO Registro VALUES (null, ?, ?, ?)",
                registro.getFkComponenteServidor(),
                registro.getValor(),
                momento
                );
    }
}
