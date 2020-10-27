package no.olai;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Echoer extends Thread{
    private DatagramSocket socket;

    public Echoer(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try{

            while (true) {
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String echoString = new String(buffer);
                System.out.println("Text received is: " + new String(echoString));
            }


        }catch (SocketException e) {
            System.out.println("SocketException " + e.getMessage());

        } catch (IOException e ) {
            System.out.println("IOException " + e.getMessage());

        } finally {
            socket.close();
        }

    }
}
