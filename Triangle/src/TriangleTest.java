import java.sql.SQLOutput;

public class TriangleTest {

    public static void main(String[] args) {


        MyPoint p1 = new MyPoint(10,30);
        MyPoint p2 = new MyPoint(30,40);
        MyPoint p3 = new MyPoint(40,10);

        MyPoint t1 = new Triangle(p1,p2,p3);


        System.out.println("P1: " + p1.toString());
        System.out.println("P2: " + p2.toString());
        System.out.println("P3: " + p3.toString());

        System.out.println();

        System.out.println("Distanse mellom p1 og p2: " + p1.Distance(p2));
        System.out.println("Distanse mellom p2 og p3: " + p2.Distance(p3));
        System.out.println("Distanse mellom p3 og p1: " + p3.Distance(p1));

        System.out.println();

        System.out.println("Omkrets av t1: " + t1.getOmkrets());
        System.out.println("Areal av t1: " + t1.getAreal());





    }
}
