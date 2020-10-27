package no.olai;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class KlientTest2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int port = 8000;
        String host = "localhost";
        Socket socket = new Socket(host, port);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        String s = (String)(in.readObject());
        System.out.println(s);
        in.close();
        socket.close();


    }
}
