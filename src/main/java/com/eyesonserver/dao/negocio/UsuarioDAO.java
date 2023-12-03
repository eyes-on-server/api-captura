package com.eyesonserver.dao.negocio;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.database.ConexaoSqlServer;
import com.eyesonserver.mapper.negocio.UsuarioRowMapper;
import com.eyesonserver.model.negocio.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UsuarioDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();
    private final JdbcTemplate sqlServer = new ConexaoSqlServer().getConexaoDoBanco();

    public List<Usuario> getUsuarioPorEmailSenha(String email, String senha) {
        String query = "SELECT * FROM View_Login WHERE login = ? AND senha = ?;";
        List<Usuario> resultado;

        try {
            resultado = sqlServer.query(query, new UsuarioRowMapper(), email, senha);
        } catch (Exception e) {
            System.out.println("Não foi possível se conectar ao Sql Server: " + e.getCause());
            System.out.println("Iniciando conexão com o banco Backup!");
            resultado = db.query(query, new UsuarioRowMapper(), email, senha);
        }

        return resultado;
    }
}


