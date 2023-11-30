package com.eyesonserver.mapper.maquina;

import com.eyesonserver.model.maquina.ComponenteServidor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponenteServidorRowMapper implements RowMapper<ComponenteServidor> {
    @Override
    public ComponenteServidor mapRow(ResultSet rs, int i) throws SQLException {
        return new ComponenteServidor(
                rs.getString("empresa"),
                rs.getString("servidor"),
                rs.getString("sistemaOperacional"),
                rs.getString("macAddress"),
                rs.getString("local"),
                rs.getString("Tipo"),
                rs.getString("componente"),
                rs.getString("medida"),
                rs.getInt("idComponenteServidor")
        );
    }
}
