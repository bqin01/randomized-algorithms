import java.util.*;

public class BoardLocation{
    private double x;
    private double y;
    public BoardLocation(){
      Random rand = new Random();
      int ax = rand.nextInt(1000000000);
      int ay = rand.nextInt(1000000000);
      x = ax/500000000.0 - 1;
      y = ay/500000000.0 - 1;
    }
    public double getX(){
      return x;
    }
    public double getY(){
      return y;
    }
    public boolean inCircle(){
      return x*x + y*y < 1;
    }
}
