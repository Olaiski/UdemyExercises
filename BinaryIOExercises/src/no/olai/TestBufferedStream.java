package no.olai;

import java.io.*;

public class TestBufferedStream {
    public static void main(String[] args) throws IOException {

        try (DataOutputStream output =
                     new DataOutputStream(new BufferedOutputStream(new FileOutputStream("temp3.dat")))
        ) {
            output.writeUTF("John");
            output.writeDouble(85.8);
            output.writeUTF("Jim");
            output.writeDouble(185.5);
            output.writeUTF("George");
            output.writeDouble(105.25);
        }


        try (DataInputStream input =
                     new DataInputStream(new BufferedInputStream(new FileInputStream("temp3.dat")))
        ){
            System.out.println(input.readUTF() + " " + input.readDouble());
            System.out.println(input.readUTF() + " " + input.readDouble());
            System.out.println(input.readUTF() + " " + input.readDouble());

        }
    }
}
