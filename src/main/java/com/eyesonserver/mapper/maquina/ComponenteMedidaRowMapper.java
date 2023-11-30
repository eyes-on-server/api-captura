package com.eyesonserver.mapper.maquina;

import com.eyesonserver.model.maquina.ComponenteMedida;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponenteMedidaRowMapper implements RowMapper<ComponenteMedida> {
    @Override
    public ComponenteMedida mapRow(ResultSet rs, int i) throws SQLException {
        return new ComponenteMedida(
                rs.getInt("id_componente_medida"),
                rs.getString("nome_componente_medida"),
                rs.getString("tipo"),
                rs.getInt("fk_componente"),
                rs.getInt("fk_medida")
        );
    }
}
