package com.eyesonserver.dao.metrica;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.model.metrica.Registro;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RegistroDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();

    public void insertRegistro(Registro registro) {
        db.update("INSERT INTO Registro VALUES (?, ?, ?, ?)",
                0,
                registro.getFkComponenteServidor(),
                registro.getValor(),
                registro.getMomento()
                );
    }
}
