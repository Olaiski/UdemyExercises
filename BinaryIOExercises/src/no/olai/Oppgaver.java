package no.olai;

import java.io.*;
import java.util.Scanner;

public class Oppgaver {
    public static void main(String[] args) throws IOException {

        File file = new File("Oppgave1og2.dat");
//        doubleRandom(file);


        file = new File("100cText");
        tekstLeser(file);



    }

    // Skriver tilfeldig antall double verdier til binær fil. Verdiene er satt mellom 0 og 200.
    private static void doubleRandom(File file) throws IOException {

        int min = 0;
        int max = 199;

        int d = (int) (Math.random() * ((max-min)+1));

        int count = 0;
        double avg;
        double sum = 0;
        double maxVal = Double.MIN_VALUE;
        double minVal = Double.MAX_VALUE;

        try (DataOutputStream output =
                     new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))
        ) {
            for (int i=0; i<=d; i++) {
                double dRan = Math.round(Math.random() * ((max-min)+1) * 100.0) / 100.0;
                output.writeDouble(dRan);
            }

        }

        try (DataInputStream input =
                     new DataInputStream(new BufferedInputStream(new FileInputStream(file)))
        ) {
            while (true) {
                double dIn = input.readDouble();
                if (dIn < minVal) {
                    minVal = dIn;
                }
                if (dIn > maxVal) {
                    maxVal = dIn;
                }
                if (input.available() == 0){
                    break;
                }

                System.out.print(dIn + " ");
                count++;
                sum += dIn;
            }
        }

        sum = Math.round(sum * 100) / 100.0;
        avg = Math.round(sum / count * 100.0) / 100.0;
        System.out.println("\nTotal count: " + count + "\nSum: " + sum + "\nGj.snitt: " + avg);
        System.out.println( "Min: " + minVal + "\nMax: " + maxVal);
    }


    private static void tekstLeser(File file) throws IOException {

        String t = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m";
        int textLen = 0;
        int binLen = 0;

        // Skriver til text fil
        try (PrintWriter pw = new PrintWriter(file+".txt")) {
            pw.println(t);
        }
        // leser fra text fil
        try (Scanner sc = new Scanner(new FileReader(file+".txt"))) {
            int len = 0;
            System.out.println("Textfil: ");
            while (sc.hasNext()) {
                String line = sc.nextLine();
                len += line.length();
                System.out.println(line + "\nLengde: " + len);

            }

        }

        System.out.println("\n-------------------\n");

        // Skriver / Leser til UTF-8 fil
        try (DataOutputStream output =
                     new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file+".dat")))
        ) {
            output.writeUTF(t);
        }

        try (DataInputStream input =
                     new DataInputStream(new BufferedInputStream(new FileInputStream(file+".dat")))
        ) {
            int len = 0;
            System.out.println("Binær fil:");
            while (true) {
                System.out.println(input.readUTF() + "\nLengde: ");
                if (input.available() == 0)
                    break;
            }

        }

    }






}
