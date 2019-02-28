import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame
{
  private static long totalPointsDrawn = 0;
  private static int timeToPlay = 100;
  private static int WIDTH = 1920;
  private static int HEIGHT = 1080;
  private static boolean removeAll = false;
  private static boolean isPlaying = false;

  private static DisplayLeft draw1;
  private static DisplayRight draw2;

  private static JButton playButton;
  private static JButton FFButton;
  private static JButton slowButton;
  private static JButton doubleButton;
  private static JButton resetButton;
  private static JButton increButton;
  private static JButton decreButton;

  private static JLabel countLabel;
  private static JLabel pi1;
  private static JPanel countPanel;
  private static JPanel panel1;

  private static JLabel titleLabel;
  private static JPanel titlePanel;
  private static JLabel nameLabel;
  private static JPanel namePanel;
  private static JLabel descrLabel;
  private static JPanel descrPanel;

  private static TextField inputText;
  private static JButton inputButton;

  private static int drawSize;
  private static DecimalFormat df;

  public static boolean isNumeric(String str)
  {
    try
    {
      double d = Integer.parseInt(str);
    }
    catch(NumberFormatException nfe)
    {
      return false;
    }
    return true;
  }
  public Display(){
    setTitle("Monte Carlo v. Las Vegas");
    setLocation(0,0);
    setSize(WIDTH, HEIGHT);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(null);
    drawSize = Math.min(WIDTH/2-100,HEIGHT-450);
    df = new DecimalFormat("0.00000000000");

    draw1 = new DisplayLeft();
    draw1.setLocation((WIDTH/2-drawSize)/2,100);
    draw1.setSize(drawSize, drawSize);
    draw1.setPreferredSize(new Dimension(drawSize,drawSize));
    add(draw1);

    draw2 = new DisplayRight();
    draw2.setLocation((WIDTH/2-drawSize)/2 + WIDTH/2, 100);
    draw2.setSize(drawSize, drawSize);
    draw2.setPreferredSize(new Dimension(drawSize, drawSize));
    add(draw2);

    playButton = new JButton(">");
    playButton.setFont(new Font("Georgia",Font.PLAIN,30));
    playButton.setBounds(WIDTH/2-100, HEIGHT - 300, 200, 80);
    playButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        isPlaying = !isPlaying;
        if(increButton.isEnabled()){
          increButton.setEnabled(false);
          decreButton.setEnabled(false);
          inputButton.setEnabled(false);
        }else{
          increButton.setEnabled(true);
          decreButton.setEnabled(true);
          inputButton.setEnabled(true);
        }
      }
    });
    add(playButton);

    FFButton = new JButton(">>");
    FFButton.setFont(new Font("Georgia",Font.PLAIN,30));
    FFButton.setBounds(WIDTH/2 + 150, HEIGHT - 300, 80, 80);
    FFButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(timeToPlay > 1) timeToPlay /= 10;
      }
    });
    add(FFButton);

    slowButton = new JButton("<<");
    slowButton.setFont(new Font("Georgia",Font.PLAIN,30));
    slowButton.setBounds(WIDTH/2 - 230, HEIGHT - 300, 80, 80);
    slowButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(timeToPlay < 1000) timeToPlay *= 10;
        timeToPlay = Math.min(timeToPlay,1000);
      }
    });
    add(slowButton);

    doubleButton = new JButton("x2");
    doubleButton.setFont(new Font("Georgia",Font.PLAIN,30));
    doubleButton.setBounds(WIDTH/2 + 260, HEIGHT - 300, 80, 80);
    doubleButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        long iterations = Math.min(totalPointsDrawn,10000000);
        for(int i = 0; i < iterations; i++){
          draw1.addPoint(new BoardLocation());
          draw2.addPoint(new BoardLocation());
        }
        totalPointsDrawn += iterations;
        pi1.setText("pi = " + df.format(draw1.computePi()));
        countLabel.setText(totalPointsDrawn + ((totalPointsDrawn <= 10000000)?" Iterations":" Iters."));
        draw1.repaint(0,0,630,630);
        draw2.repaint(0,0,630,630);
      }
    });
    add(doubleButton);

    resetButton = new JButton("0");
    resetButton.setFont(new Font("Georgia",Font.PLAIN,30));
    resetButton.setBounds(WIDTH/2 - 340, HEIGHT - 300, 80, 80);
    resetButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        draw1.clear();
        draw2.clear();
        totalPointsDrawn = 0;
      }
    });
    add(resetButton);

    increButton = new JButton("+");
    increButton.setFont(new Font("Georgia",Font.PLAIN,25));
    increButton.setBounds(WIDTH/2 + 150, HEIGHT - 200, 50, 50);
    increButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        totalPointsDrawn++;
        draw1.addPoint(new BoardLocation());
        draw2.addPoint(new BoardLocation());
        pi1.setText("pi = " + df.format(draw1.computePi()));
        countLabel.setText(totalPointsDrawn + ((totalPointsDrawn <= 10000000)?" Iterations":" Iters."));
        draw1.repaint(0,0,630,630);
        draw2.repaint(0,0,630,630);
      }
    });
    add(increButton);

    decreButton = new JButton("-");
    decreButton.setFont(new Font("Georgia",Font.PLAIN,25));
    decreButton.setBounds(WIDTH/2 - 200, HEIGHT - 200, 50, 50);
    decreButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(totalPointsDrawn != 0) totalPointsDrawn--;
        draw1.removeLast();
        draw2.removeLast();
        pi1.setText("pi = " + df.format(draw1.computePi()));
        countLabel.setText(totalPointsDrawn + ((totalPointsDrawn <= 10000000)?" Iterations":" Iters."));
        draw1.repaint(0,0,630,630);
        draw2.repaint(0,0,630,630);
      }
    });
    add(decreButton);

    countLabel = new JLabel();
    countPanel = new JPanel();
    countLabel.setFont(new Font("Georgia",Font.PLAIN,30));
    countPanel.setLocation(WIDTH/2-150, HEIGHT - 200);
    countPanel.setSize(300,100);
    countPanel.setPreferredSize(new Dimension(300, 100));
    countPanel.add(countLabel);
    add(countPanel);

    pi1 = new JLabel();
    panel1 = new JPanel();
    pi1.setText("pi = ?");
    pi1.setFont(new Font("Georgia",Font.PLAIN,30));
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
    pi1.setAlignmentX(Component.LEFT_ALIGNMENT);
    panel1.setLocation((WIDTH/2-drawSize)/2, 120 + drawSize);
    panel1.setSize(drawSize,50);
    panel1.setPreferredSize(new Dimension(drawSize, 50));
    panel1.add(pi1);
    add(panel1);

    nameLabel = new JLabel();
    namePanel = new JPanel();
    nameLabel.setText("Bill Z. Qin '19");
    nameLabel.setFont(new Font("Georgia",Font.PLAIN,20));
    nameLabel.setLocation(WIDTH-140,10);
    nameLabel.setSize(150,20);
    nameLabel.setPreferredSize(new Dimension(150,20));
    nameLabel.add(namePanel);
    add(nameLabel);

    titleLabel = new JLabel();
    titlePanel = new JPanel();
    titleLabel.setText("Empirically Deriving Pi");
    titleLabel.setFont(new Font("Georgia",Font.PLAIN,45));
    titleLabel.setLocation(WIDTH/2-230,20);
    titleLabel.setSize(600, 55);
    titleLabel.setPreferredSize(new Dimension(600, 50));
    titleLabel.add(titlePanel);
    add(titleLabel);

    descrLabel = new JLabel();
    descrPanel = new JPanel();
    descrLabel.setText("Part of An Investigation of Randomization");
    descrLabel.setFont(new Font("Georgia",Font.PLAIN,20));
    descrLabel.setLocation(10,0);
    descrLabel.setSize(500,40);
    descrLabel.setPreferredSize(new Dimension(500,40));
    descrLabel.add(descrPanel);
    add(descrLabel);

    inputText = new TextField("Jump to...");
    inputText.setFont(new Font("Georgia", Font.PLAIN, 30));
    inputText.setLocation(WIDTH/2 + 350, HEIGHT - 200);
    inputText.setSize(200,50);
    inputText.setPreferredSize(new Dimension(200,40));
    inputButton = new JButton("Go!");
    inputButton.setFont(new Font("Georgia",Font.PLAIN,20));
    inputButton.setBounds(WIDTH/2 + 550, HEIGHT - 200, 100, 50);
    inputButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(isNumeric(inputText.getText()))
        {
          long n = Integer.parseInt(inputText.getText());
          if(n < totalPointsDrawn){
            draw1.clear();
            draw2.clear();
            totalPointsDrawn = 0;
          }
          for(long i = totalPointsDrawn; i < n; i++){
            totalPointsDrawn++;
            draw1.addPoint(new BoardLocation());
            draw2.addPoint(new BoardLocation());
            countLabel.setText(totalPointsDrawn + ((totalPointsDrawn <= 10000000)?" Iterations":" Iters."));
          }
          pi1.setText("pi = " + df.format(draw1.computePi()));
          draw1.repaint(0,0,630,630);
          draw2.repaint(0,0,630,630);
        }
      }
    });
    add(inputText);
    add(inputButton);

    setVisible(true);
  }

  public static void main(String[] args) throws InterruptedException {
    Display window = new Display();

    while(true){
      if(removeAll){
        removeAll = false;
        draw1.clear();
        draw2.clear();
        totalPointsDrawn = 0;
      }
      pi1.setText("pi = " + df.format(draw1.computePi()));
      countLabel.setText(totalPointsDrawn + ((totalPointsDrawn <= 10000000)?" Iterations":" Iters."));
      if(totalPointsDrawn == 0){
        draw1.repaint(0,0,630,630);
        draw2.repaint(0,0,630,630);
      }
      if(isPlaying){
        totalPointsDrawn++;
        draw1.addPoint(new BoardLocation());
        draw2.addPoint(new BoardLocation());
        pi1.setText("pi = " + df.format(draw1.computePi()));
        countLabel.setText(totalPointsDrawn + ((totalPointsDrawn <= 10000000)?" Iterations":" Iters."));
        draw1.repaint(0,0,630,630);
        draw2.repaint(0,0,630,630);
        playButton.setText("||");
      }else{
        playButton.setText(">");
      }
      TimeUnit.MILLISECONDS.sleep(timeToPlay);
      pi1.setText("pi = " + df.format(draw1.computePi()));
      countLabel.setText(totalPointsDrawn + ((totalPointsDrawn <= 10000000)?" Iterations":" Iters."));
    }

  }
}
