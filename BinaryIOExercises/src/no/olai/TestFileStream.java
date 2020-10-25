package no.olai;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileStream {
    public static void main(String[] args) throws IOException {

        final int MB = 1048576;

        try(FileOutputStream output = new FileOutputStream("temp.dat")) {

            for (int i=1; i<=MB; i++) {
                output.write((byte)i);
            }

        }

        try(FileInputStream input = new FileInputStream("temp.dat")) {
            int value;
            while ((value = input.read()) != -1) {

            }
        }

    }

}
