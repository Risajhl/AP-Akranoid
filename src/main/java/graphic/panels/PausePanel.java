package graphic.panels;

import graphic.Commons;
import graphic.GraphicalAgent;
import graphic.models.Ball;
import graphic.models.Paddle;
import graphic.models.bricks.Brick;
import graphic.models.gifts.Gift;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class PausePanel extends JPanel implements ActionListener {

    private final Image backgroundImage;
    private final GraphicalAgent graphicalAgent;
    private JButton resumeButton;
    private JButton mainMenuButton;
    private JButton saveButton;
    private final Paddle paddle;
    private final ArrayList<Ball> balls;
    private final ArrayList<Brick> bricks;
    private final ArrayList<Gift> gifts;
    private final ArrayList<Gift> activeGifts;
    private final int crashedBricks;
    private final int lives;
    private final int timePassed;
    private final String savedName;
    private final String playerName;

    public PausePanel(String savedName,GraphicalAgent graphicalAgent, Paddle paddle, ArrayList<Ball> balls, ArrayList<Gift> gifts,ArrayList<Gift> activeGifts, ArrayList<Brick> bricks, int crashedBricks, int lives,int timePassed,String playerName){
        this.setLayout(null);
        this.setFocusable(true);

        setSize(Commons.WIDTH,Commons.HEIGHT);
        setBackground(new Color(99,176,161));
        setButtons();
        backgroundImage=new ImageIcon("src/main/resources/pics/pauseImage.png").getImage();
        this.graphicalAgent=graphicalAgent;

        this.paddle=paddle;
        this.balls=balls;
        this.gifts=gifts;
        this.activeGifts=activeGifts;
        this.bricks=bricks;
        this.crashedBricks=crashedBricks;
        this.lives=lives;
        this.timePassed=timePassed;
        this.savedName=savedName;
        this.playerName=playerName;



    }


    public void setButtons(){
        resumeButton=new JButton("Resume");
        resumeButton.setBounds((Commons.WIDTH-300)/2,180,300,100);
        resumeButton.setFont(new Font("Curlz MT",Font.BOLD,50));
        resumeButton.setBackground(new Color(218,170,255));
        resumeButton.setForeground(Color.white);
        resumeButton.addActionListener(this);
        resumeButton.setFocusable(false);
        add(resumeButton);

        saveButton=new JButton("Save");
        saveButton.setBounds((Commons.WIDTH-300)/2,315,300,100);
        saveButton.setFont(new Font("Curlz MT",Font.BOLD,50));
        saveButton.setBackground(new Color(218,170,255));
        saveButton.setForeground(Color.white);
        saveButton.addActionListener(this);
        saveButton.setFocusable(false);
        add(saveButton);

        mainMenuButton=new JButton("Main Menu");
        mainMenuButton.setBounds((Commons.WIDTH-300)/2,450,300,100);
        mainMenuButton.setFont(new Font("Curlz MT",Font.BOLD,50));
        mainMenuButton.setBackground(new Color(218,170,255));
        mainMenuButton.setForeground(Color.white);
        mainMenuButton.addActionListener(this);
        mainMenuButton.setFocusable(false);
        add(mainMenuButton);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawImage(backgroundImage,0,0,Commons.WIDTH,Commons.HEIGHT,this);

        Font font1=new Font("jokerman",Font.BOLD,60);
        FontMetrics metrics1=this.getFontMetrics(font1);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font1);
        g2d.drawString("Game Paused",(Commons.WIDTH-metrics1.stringWidth("Game Paused"))/2,100);

        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(resumeButton)) {
            graphicalAgent.resumeGame(savedName, paddle, balls, gifts, activeGifts, bricks, crashedBricks, lives, timePassed, playerName);
        } else if (e.getSource().equals(saveButton)) {
            System.out.println(savedName);
            if (savedName == null) {


                String gameName = JOptionPane.showInputDialog("Enter game name:");
                if (gameName != null && !"".equals(gameName)) {
                    if (gameExist(gameName)) {
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog(null, "There is another game saved with this name, do you wanna overWrite?", "Warning", dialogButton);
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            graphicalAgent.saveGame(gameName, paddle, balls, gifts, activeGifts, bricks, crashedBricks, lives, timePassed, playerName);
                        }

                    } else {
                        graphicalAgent.saveGame(gameName, paddle, balls, gifts, activeGifts, bricks, crashedBricks, lives, timePassed, playerName);
                    }
                }

            } else {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to update your game in the same directory?", "Warning", dialogButton);

                if (dialogResult == JOptionPane.YES_OPTION) {
                    graphicalAgent.saveGame(savedName, paddle, balls, gifts, activeGifts, bricks, crashedBricks, lives, timePassed, playerName);

                    // Saving code here
                } else if (dialogResult == JOptionPane.NO_OPTION) {
                    String gameName = JOptionPane.showInputDialog("Enter new game name:");
                    if (gameName != null && !"".equals(gameName)) {
                        if (gameExist(gameName)) {
                            int dialogButton1 = JOptionPane.YES_NO_OPTION;
                            int dialogResult1 = JOptionPane.showConfirmDialog(null, "There is another game saved with this name, do you wanna overWrite?", "Warning", dialogButton1);
                            if (dialogResult1 == JOptionPane.YES_OPTION) {
                                graphicalAgent.saveGame(gameName, paddle, balls, gifts, activeGifts, bricks, crashedBricks, lives, timePassed, playerName);
                            }

                        } else {
                            graphicalAgent.saveGame(gameName, paddle, balls, gifts, activeGifts, bricks, crashedBricks, lives, timePassed, playerName);
                        }
                    }

                }
            }

        }
        else if(e.getSource().equals(mainMenuButton)){
            graphicalAgent.mainMenu(playerName);
        }
    }




    public  boolean gameExist(String gameName){
        boolean h=false;
        File info =new File("src/main/resources/info/"+playerName);
        for (File file:
             info.listFiles()) {
            if(file.getName().equals(gameName)){
                h=true;
            }
        }
        return h;
    }
}
