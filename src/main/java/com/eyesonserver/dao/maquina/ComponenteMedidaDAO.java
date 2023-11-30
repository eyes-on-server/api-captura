package com.eyesonserver.dao.maquina;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.mapper.maquina.ComponenteMedidaRowMapper;
import com.eyesonserver.model.maquina.ComponenteMedida;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ComponenteMedidaDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();

    public ComponenteMedida coletarId(String tipo) {
        String query = "SELECT * FROM Eyes_On_Server.Componente_Medida WHERE tipo = ?;";
        return db. queryForObject(query, new ComponenteMedidaRowMapper(), tipo);
    }
}
