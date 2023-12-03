package com.eyesonserver.dao.maquina;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.database.ConexaoSqlServer;
import com.eyesonserver.mapper.maquina.ComponenteServidorRowMapper;
import com.eyesonserver.model.maquina.ComponenteServidor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ComponenteServidorDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();
    private final JdbcTemplate sqlServer = new ConexaoSqlServer().getConexaoDoBanco();


    public void inserirComponenteServidor(Integer fkServidor, Integer fkComponenteMedida) {
        String query = "INSERT INTO Componente_Servidor (fk_Servidor, fk_componente_medida) VALUES (?, ?);";

        try {
            sqlServer.update(query, fkServidor, fkComponenteMedida);
        } catch (Exception e) {
            System.out.println("Não foi possível efetuar a conexão com o Sql Server: " + e.fillInStackTrace());
        }

        try {
            db.update(query, fkServidor, fkComponenteMedida);
        } catch (Exception e) {
            System.out.println("Não foi possível efetuar a conexão com o banco Backup: " + e.fillInStackTrace());
        }
    }

    public List<ComponenteServidor> consultarComponenteServidorPorMacAddress(String macAddress){
        String query = "SELECT * FROM view_componentes_servidores WHERE macAddress = ?;";
        List<ComponenteServidor> resultado;

        try {
            resultado = sqlServer.query(query, new ComponenteServidorRowMapper(), macAddress);
        } catch (Exception e) {
            System.out.println("Não foi possível se conectar ao Sql Server: " + e.fillInStackTrace());
            System.out.println("Iniciando conexão com Banco Backup!");
            resultado = db.query(query, new ComponenteServidorRowMapper(), macAddress);
        }
        return resultado;
    }

    public ComponenteServidor coletarIdComponenteServidorPorMacaddressTipo(String macAddress, String tipo){
        String query = "SELECT * FROM view_componentes_servidores WHERE macAddress = ? AND Tipo = ?;";
        ComponenteServidor resultado;

        try {
            resultado = sqlServer.queryForObject(query, new ComponenteServidorRowMapper(), macAddress, tipo);
        } catch (Exception e) {
            System.out.println("Não foi possível se conectar ao Sql Server: " + e.fillInStackTrace());
            System.out.println("Iniciando conexão com Banco Backup!");
            resultado = db.queryForObject(query, new ComponenteServidorRowMapper(), macAddress, tipo);
        }

        return resultado;
    }

}
