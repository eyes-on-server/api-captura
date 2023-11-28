package com.eyesonserver.mapper.negocio;

import com.eyesonserver.model.negocio.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRowMapper implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(ResultSet rs, int i) throws SQLException {
        return new Usuario(
                rs.getString("nome"),
                rs.getInt("id_empresa"),
                rs.getString("login"),
                rs.getString("senha")
        );
    }
}
