package handshake;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class KlientTest1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int port = 8000;
        String host = "localhost";
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket socket;

        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(1);
        String s = (String)(in.readObject());
        System.out.println(s);
        in.close();
        out.close();
        socket.close();

    }
}
