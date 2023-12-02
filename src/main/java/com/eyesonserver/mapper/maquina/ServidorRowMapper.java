package com.eyesonserver.mapper.maquina;

import com.eyesonserver.model.maquina.Servidor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServidorRowMapper implements RowMapper<Servidor> {
    @Override
    public Servidor mapRow(ResultSet rs, int i) throws SQLException {
        return new Servidor(
                rs.getInt("id_servidor"),
                rs.getInt("fk_empresa"),
                rs.getString("nome_servidor"),
                rs.getString("local_servidor"),
                rs.getString("ipv6_servidor"),
                rs.getString("so_servidor"),
                rs.getString("mac_address")
        );
    }
}
