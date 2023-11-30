package com.eyesonserver.database;


import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;


public class Conexao {
    //classe responsavel pela conexao com o bd

    private final JdbcTemplate conexaoDoBanco;

    public Conexao() {
        Dotenv dotenv = Dotenv.load();
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dotenv.get("DRIVER_DB"));
        dataSource.setUrl(dotenv.get("URL_DB"));
        dataSource.setUsername(dotenv.get("USUARIO_DB"));
        dataSource.setPassword(dotenv.get("SENHA_DB"));

        conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }
}


