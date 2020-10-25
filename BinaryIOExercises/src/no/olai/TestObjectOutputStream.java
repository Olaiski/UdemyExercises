package no.olai;

import java.io.*;
import java.util.Date;

public class TestObjectOutputStream {
    public static void main(String[] args) throws ClassNotFoundException, IOException {


        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream("object.dat"))
        ) {
            output.writeUTF("Johnny");
            output.writeDouble(133.7);
            output.writeObject(new Date());

        }

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream("object.dat"))
        ) {
            String name = input.readUTF();
            double score = input.readDouble();
            Date date = (Date)input.readObject();
            System.out.println(name + "\n" + score + "\n" + date);
        }


    }
}
