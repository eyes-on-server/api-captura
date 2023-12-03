package com.eyesonserver.dao.metrica;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.database.ConexaoSqlServer;
import com.eyesonserver.mapper.metrica.ViewRegistroRowMapper;
import com.eyesonserver.model.metrica.ViewRegistro;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

public class ViewRegistrosDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();
    private final JdbcTemplate sqlServer = new ConexaoSqlServer().getConexaoDoBanco();
    private final Dotenv dotenv = Dotenv.load();


    public ViewRegistro consultarUltimoMomento(Integer fkServidor) {

        ViewRegistro resultado;

        if (Objects.requireNonNull(dotenv.get("AMBIENTE")).equalsIgnoreCase("Produção")) {
            try {
                resultado = sqlServer.queryForObject("SELECT MAX(Momento) FROM View_Registros WHERE Servidor = ?", new ViewRegistroRowMapper(), fkServidor);
            } catch (EmptyResultDataAccessException e) {
                System.out.println ("Nenhum registro encontrado!");
                resultado = null;
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao Sql Server: " + e.fillInStackTrace());
                System.out.println("Iniciando conexão com banco backup!");

                try {
                    resultado = db.queryForObject("SELECT MAX(Momento) FROM View_Registros WHERE Servidor = ?", new ViewRegistroRowMapper(), fkServidor);
                } catch (EmptyResultDataAccessException err) {
                    System.out.println ("Nenhum registro encontrado!");
                    resultado = null;
                }
            }
        } else{
            try {
                resultado = db.queryForObject("SELECT MAX(Momento) FROM View_Registros WHERE Servidor = ?;", new ViewRegistroRowMapper(), fkServidor);
            } catch (EmptyResultDataAccessException err) {
                System.out.println ("Nenhum registro encontrado!");
                resultado = null;
            }
        }
        return resultado;
    }

    public ViewRegistro consultarPenultimoMomento(Integer fkServidor, LocalDateTime ultimoRegistro) {

        ViewRegistro resultado;

        if (Objects.requireNonNull(dotenv.get("AMBIENTE")).equalsIgnoreCase("Produção")) {
            try {
                resultado = sqlServer.queryForObject("SELECT MAX(Momento) FROM View_Registros WHERE Servidor = ? and Momento < ?", new ViewRegistroRowMapper(), fkServidor, ultimoRegistro);
            } catch (EmptyResultDataAccessException e) {
                System.out.println ("Nenhum registro encontrado!");
                resultado = null;
            } catch (Exception e) {
                System.out.println("Não foi possível se conectar ao Sql Server: " + e.fillInStackTrace());
                System.out.println("Iniciando conexão com banco backup!");

                try {
                    resultado = db.queryForObject("SELECT MAX(Momento) FROM View_Registros WHERE Servidor = ? and Momento < ?", new ViewRegistroRowMapper(), fkServidor, ultimoRegistro);
                } catch (EmptyResultDataAccessException err) {
                    System.out.println ("Nenhum registro encontrado!");
                    resultado = null;
                }
            }
        } else {
            try {
                resultado = db.queryForObject("SELECT MAX(Momento) FROM View_Registros WHERE Servidor = ? and Momento < ?", new ViewRegistroRowMapper(), fkServidor, ultimoRegistro);
            } catch (EmptyResultDataAccessException err) {
                System.out.println ("Nenhum registro encontrado!");
                resultado = null;
            }
        }
        return resultado;
    }
}
