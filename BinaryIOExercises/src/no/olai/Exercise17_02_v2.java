package no.olai;

import java.io.*;

public class Exercise17_02_v2 {

    public static void main(String[] args) throws IOException {

        int min = 0;
        int max = 199;

        int d = (int) (Math.random() * ((max-min)+1));

        int count = 0;
        double avg;
        double sum = 0;
        double maxVal = Double.MIN_VALUE;
        double minVal = Double.MAX_VALUE;


            try (DataOutputStream output =
                         new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Exercise17_02.dat")))
            ) {
                for (int i=0; i<=d; i++) {
                    double dRan = Math.round(Math.random() * ((max-min)+1) * 100.0) / 100.0;
                    output.writeDouble(dRan);
                }

            }



            try (DataInputStream input =
                         new DataInputStream(new BufferedInputStream(new FileInputStream("Exercise17_02.dat")))
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

                    System.out.println(dIn);
                    count++;
                    sum += dIn;
                }
            }


        sum = Math.round(sum * 100) / 100.0;
        avg = Math.round(sum / count * 100.0) / 100.0;
        System.out.println("\nTotal count: " + count + "\nSum: " + sum + "\nGj.snitt: " + avg);
        System.out.println( "Min: " + minVal + "\nMax: " + maxVal);

    }
}
