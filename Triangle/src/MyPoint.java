public class MyPoint {


    protected float x, y;

    public MyPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public MyPoint() {
    }



    
    public double Distance(float x2, float y2){

        return Math.sqrt((x2 - this.getX()) * (x2 - this.getX()) + (y2 - this.getY()) * (y2 - this.getY()));
        // return Math.sqrt((double)((x2 - this.getX()) * (x2 - this.getX()) + (y2 - this.getY()) * (y2 - this.getY())));

    }
    public double Distance(MyPoint xy){
        return xy.Distance(this.getX(), this.getY());
    }

    public double Distance(MyPoint xy, MyPoint cv){
        return xy.Distance(cv);
    }


    public double getOmkrets(){
        return 0;
    }


    public double getAreal(){
        return 0;
    }






    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return
                "X: " + x +
                ", Y: " + y;
    }



}
