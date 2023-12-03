package com.eyesonserver.mapper.metrica;

import com.eyesonserver.model.metrica.ViewRegistro;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewRegistroRowMapper implements RowMapper<ViewRegistro> {
    @Override
    public ViewRegistro mapRow(ResultSet rs, int i) throws SQLException {
        return new ViewRegistro(
                rs.getString("MAX(Momento)")
        );
    }
}
