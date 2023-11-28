package com.eyesonserver.dao.negocio;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.mapper.negocio.UsuarioRowMapper;
import com.eyesonserver.model.negocio.Usuario;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UsuarioDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();

    public List<Usuario> getUsuarioPorEmailSenha(String email, String senha) {
        String query = "SELECT * FROM View_Login WHERE login = ? AND senha = ?;";
        return db.query(query, new UsuarioRowMapper(), email, senha);
    }
}


