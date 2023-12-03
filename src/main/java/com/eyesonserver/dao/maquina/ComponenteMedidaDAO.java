package com.eyesonserver.dao.maquina;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.database.ConexaoSqlServer;
import com.eyesonserver.mapper.maquina.ComponenteMedidaRowMapper;
import com.eyesonserver.model.maquina.ComponenteMedida;
import org.springframework.jdbc.core.JdbcTemplate;

public class ComponenteMedidaDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();
    private final JdbcTemplate sqlServer = new ConexaoSqlServer().getConexaoDoBanco();

    public ComponenteMedida coletarCompMedidaPorTipo(String tipo) {
        String query = "SELECT * FROM Componente_Medida WHERE tipo = ?;";
        ComponenteMedida resultado;

        try {
            resultado = sqlServer.queryForObject(query, new ComponenteMedidaRowMapper(), tipo);
        } catch (Exception e) {
            System.out.println("Não foi possível se conectar ao Sql Server: " + e.fillInStackTrace());
            System.out.println("Capturando dados do Banco de Backup!");
            resultado = db.queryForObject(query, new ComponenteMedidaRowMapper(), tipo);
        }

        return resultado;
    }

}
