package com.eyesonserver.utils;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class InfoServidor {
    private static final Looca looca = new Looca();
    public static String getNomeServidor() {
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec("hostname");
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader (p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static String getIpv6Servidor() {
        String ipv6 = "0000:0:0:0:000:0000:0000:0000";

        List<RedeInterface> interfacesRede = looca.getRede().getGrupoDeInterfaces().getInterfaces();

        for (RedeInterface redeInterface : interfacesRede) {
            if(redeInterface.getNome().startsWith("e")) {
                ipv6 = redeInterface.getEnderecoIpv6().get(0);
            }
        }
        return ipv6;
    }
}
