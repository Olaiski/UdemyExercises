package no.olai;

import java.io.*;

public class TestObjectStreamForArray {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        int[] numbers = {1,2,3,4,5};
        String[] names = {"Benny", "Anders", "Frida"};

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream("array.dat", true))
        ) {
            output.writeObject(numbers);
            output.writeObject(names);
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream("array.dat"))
        ) {
            int[] newNumbers = (int[])(input.readObject());
            String[] newNames = (String[])(input.readObject());

            for (int i=0; i<newNumbers.length; i++)
                System.out.print(newNumbers[i] + " ");
            System.out.println();

            for (int i=0; i<newNames.length; i++)
                System.out.print(newNames[i] + " ");

        }


    }
}
