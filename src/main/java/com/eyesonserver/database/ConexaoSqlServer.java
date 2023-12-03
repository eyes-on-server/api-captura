package com.eyesonserver.database;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoSqlServer {
    private final JdbcTemplate conexaoDoBanco;

    public ConexaoSqlServer() {
        Dotenv dotenv = Dotenv.load();
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(dotenv.get("DRIVER_SQL_SERVER"));
        dataSource.setUrl(dotenv.get("URL_SQL_SERVER"));
        dataSource.setUsername(dotenv.get("USUARIO_SQL_SERVER"));
        dataSource.setPassword(dotenv.get("SENHA_SQL_SERVER"));

        conexaoDoBanco = new JdbcTemplate(dataSource)   ;
    }

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }
}
