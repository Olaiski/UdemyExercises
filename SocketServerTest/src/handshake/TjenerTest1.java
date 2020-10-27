package handshake;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TjenerTest1 {

    // Henter spesifikk index..

    final static String[] ord = {
            "Smuler",
            "Bak skyene",
            "Arbeidet",
            "Man får k",
            "Harens"
    };

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int port = 8000;
        ObjectInputStream in;
        ObjectOutputStream out;
        ServerSocket server;
        Socket socket;

        server = new ServerSocket(port);
        socket = server.accept();
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());

        int n = (int)(in.readObject());
        if (n < 0 || n >= ord.length)
            out.writeObject("ERROR");
        else
            out.writeObject(ord[n]);
        in.close();
        out.close();
        socket.close();


    }
}
