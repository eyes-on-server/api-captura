package com.eyesonserver.dao.maquina;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.database.ConexaoSqlServer;
import com.eyesonserver.mapper.maquina.ServidorRowMapper;
import com.eyesonserver.model.maquina.Servidor;
import com.eyesonserver.utils.InfoServidor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ServidorDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();

    private final JdbcTemplate sqlServer = new ConexaoSqlServer().getConexaoDoBanco();

    public Servidor getServidorPorMacAddress(String macAddress) {

        Servidor resultado;

        try {
            resultado = sqlServer.queryForObject("SELECT * FROM Servidor WHERE mac_address = ?", new ServidorRowMapper(), macAddress);
        } catch (EmptyResultDataAccessException e) {
            System.out.println ("Servidor não registrado!");
            resultado = null;
        } catch (Exception e) {
            System.out.println("Não foi possível se conectar ao Sql Server: " + e.fillInStackTrace());
            System.out.println("Iniciando conexão com banco backup!");

            try {
                resultado = db.queryForObject("SELECT * FROM Servidor WHERE mac_address = ?", new ServidorRowMapper(), macAddress);
            } catch (EmptyResultDataAccessException err) {
                System.out.println ("Servidor não registrado!");
                resultado = null;
            }
        }

        return resultado;

    }

    public void insertServidor(Integer fkEmpresa, String local) {
        String descricao = "Esse servidor foi registrado automaticamente.";

        try {
            sqlServer.update("INSERT INTO Servidor (fk_empresa, nome_servidor, local_servidor, ipv6_servidor, mac_address, so_servidor, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    fkEmpresa,
                    InfoServidor.getNomeServidor (),
                    local,
                    InfoServidor.getIpv6Servidor(),
                    InfoServidor.getMacAddressServidor(),
                    InfoServidor.getSoServidor(),
                    descricao
            );
        } catch (Exception e) {
            System.out.println("Não foi possível se conectar ao banco Sql Server: " + e.fillInStackTrace());
        }

        try {
            db.update("INSERT INTO Servidor VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    0,
                    fkEmpresa,
                    InfoServidor.getNomeServidor (),
                    local,
                    InfoServidor.getIpv6Servidor(),
                    InfoServidor.getMacAddressServidor(),
                    InfoServidor.getSoServidor(),
                    descricao
            );
        } catch (Exception e) {
            System.out.println("Não foi possível se conectar ao banco MYSQL: " + e.fillInStackTrace());
        }
    }
}
