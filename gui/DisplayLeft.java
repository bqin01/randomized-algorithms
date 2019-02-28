import java.awt.*;
import javax.swing.*;
import java.util.*;

public class DisplayLeft extends JPanel
{
  private int WIDTH;
  private int HEIGHT;
  private long positivePoints;
  private long totalPoints;
  private ArrayList<BoardLocation> locs;
  public DisplayLeft(){
    locs = new ArrayList<BoardLocation>();
    setVisible(true);
    WIDTH = 630;
    HEIGHT = 630;
    positivePoints = 0;
    totalPoints = 0;
  }
  public void addPoint(BoardLocation b){
    locs.add(b);
    if(locs.size() > 200) locs.remove(0);
    totalPoints++;
    if(b.inCircle()) positivePoints++;
  }
  public void clear(){
    totalPoints = 0;
    positivePoints = 0;
    locs.clear();
  }
  public void removeLast(){
    if(totalPoints == 0) return;
    totalPoints--;
    if(locs.get(locs.size()-1).inCircle()) positivePoints--;
    locs.remove(locs.size()-1);
  }
  public double computePi(){
    if(totalPoints == 0) return 3.14159265358979;
    return (double)positivePoints / totalPoints * 4;
  }
  public void paint(Graphics g){
    g.setColor(Color.white);
    g.fillRect(0,0,WIDTH-1,HEIGHT-1);
    g.setColor(Color.red);
    g.fillOval(0,0,WIDTH-1,HEIGHT-1);
    g.setColor(Color.black);
    g.fillOval(63,63,WIDTH-127,HEIGHT-127);
    g.setColor(Color.red);
    g.fillOval(126,126,WIDTH-253,HEIGHT-253);
    g.setColor(Color.black);
    g.fillOval(189,189,WIDTH-379,HEIGHT-379);
    g.setColor(Color.red);
    g.fillOval(252,252,WIDTH-503,HEIGHT-503);
    g.setColor(Color.gray);
    for(int i = 0; i < locs.size(); i++){
      int dotSize = 10;
      if(locs.get(i).inCircle()) g.setColor(Color.green);
      else g.setColor(Color.gray);
      if(i == locs.size()-1){
         dotSize = 30;
       }
      g.fillOval((int)(locs.get(i).getX()*WIDTH/2 + WIDTH/2)-dotSize/2, (int)(locs.get(i).getY()*HEIGHT/2 + HEIGHT/2)-dotSize/2, dotSize, dotSize);
    }
  }
}
