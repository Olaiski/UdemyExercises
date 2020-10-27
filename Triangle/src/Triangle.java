public class Triangle extends MyPoint{

    protected MyPoint p1, p2, p3;






    public Triangle(MyPoint p1, MyPoint p2, MyPoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(){

    }




    @Override
    public double getOmkrets(){
        MyPoint p1 = getP1();
        MyPoint p2 = getP2();
        MyPoint p3 = getP3();

        return p1.Distance(p2) + p2.Distance(p3) + p3.Distance(p1);
    }

    @Override
    public double getAreal(){
        // Herons formula
        MyPoint p1 = getP1();
        MyPoint p2 = getP2();
        MyPoint p3 = getP3();

        double s1 = p1.Distance(p2);
        double s2 = p2.Distance(p3);
        double s3 = p3.Distance(p1);

        double area = (s1+s2+s3) / 2.0d;

        return Math.sqrt(area * (area - s1) * (area - s2) * (area - s3));

    }



    public MyPoint getP1() {
        return p1;
    }

    public void setP1(MyPoint p1) {
        this.p1 = p1;
    }

    public MyPoint getP2() {
        return p2;
    }

    public void setP2(MyPoint p2) {
        this.p2 = p2;
    }

    public MyPoint getP3() {
        return p3;
    }

    public void setP3(MyPoint p3) {
        this.p3 = p3;
    }



}
