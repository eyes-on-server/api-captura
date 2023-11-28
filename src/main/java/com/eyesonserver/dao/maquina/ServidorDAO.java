package com.eyesonserver.dao.maquina;

import com.eyesonserver.database.Conexao;
import com.eyesonserver.mapper.maquina.ServidorRowMapper;
import com.eyesonserver.model.maquina.Servidor;
import com.eyesonserver.utils.InfoServidor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ServidorDAO {
    private final JdbcTemplate db = new Conexao().getConexaoDoBanco();

    public Servidor getServidorPorMacAddress(String macAddress) {

        try {
            return db.queryForObject("SELECT * FROM Servidor WHERE mac_address = ?", new ServidorRowMapper(), macAddress);
        } catch (EmptyResultDataAccessException e) {
            System.out.println ("Servidor não registrado!");
            return null;
        }

    }

    public void insertServidor(Integer fkEmpresa, String local) {
        String descricao = "Esse servidor foi registrado automaticamente.";

        db.update("INSERT INTO Servidor VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                0,
                fkEmpresa,
                InfoServidor.getNomeServidor (),
                local,
                InfoServidor.getIpv6Servidor(),
                InfoServidor.getMacAddressServidor(),
                InfoServidor.getSoServidor(),
                descricao
        );
    }
}
