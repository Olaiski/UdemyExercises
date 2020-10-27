package sammetjener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest extends Thread{

    // Egner seg hvis kommunikasjonen tar lang tid
    // Thread -> Blokkerer for å betjelne andre klienter

    private Socket socket;
    final static String[] ord = {
            "Smuler",
            "Bak skyene",
            "Arbeidet",
            "Man får k",
            "Harens"
    };

    public ServerTest(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {

        int port = 8000;
        ServerSocket server = new ServerSocket(port);

        while (true) {
            Socket socket = server.accept();
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("Host: " + inetAddress.getHostName());
            System.out.println("IP: " + inetAddress.getHostAddress());
            Thread thread = new ServerTest(socket);
            thread.start();
        }

    }

    public void run() { // Håndterer bare kommunikasjon med en bestemt klient
        try { // Hver tråd har sin egen socket
            ObjectInputStream in = new ObjectInputStream
                    (socket.getInputStream());

            ObjectOutputStream out = new ObjectOutputStream
                    (socket.getOutputStream());

            int n = (int)(in.readObject());
            if (n < 0 || n >= ord.length)
                out.writeObject("ERROR");
            else
                out.writeObject(ord[n]);

            out.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
