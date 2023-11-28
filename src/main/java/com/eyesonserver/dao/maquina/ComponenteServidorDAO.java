package com.eyesonserver.dao.maquina;

import com.eyesonserver.database.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ComponenteServidorDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();

    public void inserirComponenteServidor(Integer fkServidor, Integer fkComponenteMedida) {
        String query = "INSERT INTO Eyes_On_Server.Componente_Servidor VALUES (NULL, ?, ?);";
        db.update(query, fkServidor, fkComponenteMedida);
    }

    public List<String> consultarComponenteServidorPorMacAddress(String macAddress){
        String query = "SELECT tipo FROM Eyes_On_Server.view_componentes_servidores WHERE `macAddress` = ?;";
        return db.query(query, new BeanPropertyRowMapper<>(), macAddress);
    }

}
