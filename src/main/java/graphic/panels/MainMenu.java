package graphic.panels;

import graphic.Commons;
import graphic.GraphicalAgent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel implements ActionListener {
    private final GraphicalAgent graphicalAgent;
    private JButton start;
    private JButton loadGame;
    private JButton leaderBoard;
    private JButton exit;
    private final Image backgroundImage;
    private final Image arkanoidImage;
    private final String playerName;

    public MainMenu(GraphicalAgent graphicalAgent,String playerName){

        this.setLayout(null);
        this.setFocusable(true);

        setSize(Commons.WIDTH,Commons.HEIGHT);
        setBackground(new Color(99,176,161));
        setButtons();
        backgroundImage=new ImageIcon("src/main/resources/pics/mainMenuPic.jpg").getImage();
        arkanoidImage=new ImageIcon("src/main/resources/pics/arkanoid2.png").getImage();
        this.graphicalAgent=graphicalAgent;
        this.playerName=playerName;

    }

    public void setButtons(){
        start=new JButton("Start");
        start.setBounds((Commons.WIDTH-300)/2,120,300,100);
        start.setFont(new Font("Curlz MT",Font.BOLD,40));
        start.setBackground(new Color(195,208,240));
        start.addActionListener(this);
        start.setFocusable(false);
        add(start);

        loadGame=new JButton("Load Game");
        loadGame.setBounds((Commons.WIDTH-300)/2,240,300,100);
        loadGame.setFont(new Font("Curlz MT",Font.BOLD,40));
        loadGame.setBackground(new Color(226,177,241));
        loadGame.addActionListener(this);
        loadGame.setFocusable(false);
        add(loadGame);

        leaderBoard=new JButton("Leader Board");
        leaderBoard.setBounds((Commons.WIDTH-300)/2,360,300,100);
        leaderBoard.setFont(new Font("Curlz MT",Font.BOLD,40));
        leaderBoard.setBackground(new Color(195,234,251));
        leaderBoard.addActionListener(this);
        leaderBoard.setFocusable(false);
        add(leaderBoard);

        exit=new JButton("Exit");
        exit.setBounds((Commons.WIDTH-300)/2,480,300,100);
        exit.setFont(new Font("Curlz MT",Font.BOLD,40));
        exit.setBackground(new Color(195,158,222));
        exit.addActionListener(this);
        exit.setFocusable(false);
        add(exit);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawImage(backgroundImage,0,0,Commons.WIDTH,Commons.HEIGHT,this);
        g2d.drawImage(arkanoidImage,(Commons.WIDTH-300)/2,20,300,90,null);

        repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(start)){
            graphicalAgent.startGame(playerName);
        }
        else if(e.getSource().equals(loadGame)){
            String gameName=JOptionPane.showInputDialog("Enter game name:");
            if(gameName!=null && !gameName.equals("")) {
                graphicalAgent.loadGame(gameName, playerName);
            }
        }
        else if(e.getSource().equals(leaderBoard)){
            graphicalAgent.leaderBoard(playerName);
        }
        else if(e.getSource().equals(exit)){
            graphicalAgent.exitGame();
        }
    }

}
