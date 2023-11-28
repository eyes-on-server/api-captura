package com.eyesonserver.dao.maquina;

import com.eyesonserver.database.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ComponenteMedidaDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();

    public Integer coletarId(String tipo) {
        String query = "SELECT * FROM Eyes_On_Server.Componente_Medida WHERE tipo = ?;";
        return db.queryForObject(query, new BeanPropertyRowMapper<Integer>(), tipo);
    }
}
