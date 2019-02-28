import java.awt.*;
import javax.swing.*;
import java.util.*;

public class DisplayRight extends JPanel
{
  private int WIDTH;
  private int HEIGHT;
  private int totalPoints;
  private ArrayList<BoardLocation> locs;
  public DisplayRight(){
    locs = new ArrayList<BoardLocation>();
    setVisible(true);
    WIDTH = 630;
    HEIGHT = 630;
    totalPoints = 0;
  }
  public void addPoint(BoardLocation b){
    locs.add(b);
    if(locs.size() > 200) locs.remove(0);
    totalPoints++;
  }
  public void clear(){
    locs.clear();
    totalPoints = 0;
  }
  public void removeLast(){
    if(totalPoints == 0) return;
    totalPoints--;
    double x = locs.get(locs.size()-1).getX();
    double y = locs.get(locs.size()-1).getY();
    locs.remove(locs.size()-1);
  }
  public void paint(Graphics g){
    g.setColor(Color.white);
    g.fillRect(0,0,WIDTH-1,HEIGHT-1);
    g.setColor(Color.green);
    g.fillOval(0,0,WIDTH-1,HEIGHT-1);
    g.setColor(Color.black);
    for(BoardLocation b : locs){
      g.fillOval((int)(b.getX()*WIDTH/2 + WIDTH/2)-5, (int)(b.getY()*HEIGHT/2 + HEIGHT/2)-5, 10, 10);
    }
  }
}
