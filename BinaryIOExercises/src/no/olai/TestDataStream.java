package no.olai;

import java.io.*;

public class TestDataStream {
    public static void main(String[] args) throws IOException {

        try ( // Create an output stream for file temp2.dat
                DataOutputStream output =
                        new DataOutputStream(new FileOutputStream("temp2.dat"))
        ) {
            output.writeUTF("John");
            output.writeDouble(85.8);
            output.writeUTF("Jim");
            output.writeDouble(185.5);
            output.writeUTF("George");
            output.writeDouble(105.25);
        }


        try ( // Create an input steam for file temp2.dat
                DataInputStream input =
                     new DataInputStream(new FileInputStream("temp2.dat"))
        ){
            System.out.println(input.readUTF() + " " + input.readDouble());
            System.out.println(input.readUTF() + " " + input.readDouble());
            System.out.println(input.readUTF() + " " + input.readDouble());

        }


    }
}
