package no.olai;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TjenerTest2 {

    final static String[] ord = {
            "Smuler",
            "Bak skyene",
            "Arbeidet",
            "Man får k",
            "Harens"
    };

    public static void main(String[] args) throws IOException {

        int port = 8000;
        ServerSocket server = new ServerSocket(port);

        while (true) {
            Socket socket = server.accept();
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            int n = (int) (Math.random()*ord.length);
            out.writeObject(ord[n]);
            out.close();
            socket.close();
        }


    }
}
