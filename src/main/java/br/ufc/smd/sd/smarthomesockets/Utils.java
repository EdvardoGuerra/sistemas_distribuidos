package br.ufc.smd.sd.smarthomesockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Utils {



    public static String receberMensagemTCP(DataInputStream stream) throws IOException {

        byte[] message = null;
        int length = stream.read();
        if (length > 0) {
            message = new byte[length];
            stream.readFully(message, 0, message.length);
        }

        String resultado = new String(message, StandardCharsets.US_ASCII);
        return resultado;//Retorno da função
    }

}
