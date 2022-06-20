package graphic.panels;

import graphic.Commons;
import graphic.GraphicalAgent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class GameOver extends JPanel implements ActionListener {

    private final Image backgroundImage;
    private final GraphicalAgent graphicalAgent;
    private JButton restartButton;
    private JButton mainMenuButton;
    private final int score;
    private final String playerName;


    public GameOver(GraphicalAgent graphicalAgent,int score,String playerName){
        this.setLayout(null);
        this.setFocusable(true);

        setSize(Commons.WIDTH,Commons.HEIGHT);
        setBackground(new Color(99,176,161));
        setButtons();
        backgroundImage=new ImageIcon("src/main/resources/pics/gameOverImage.jpg").getImage();
        this.score=score;
        this.graphicalAgent=graphicalAgent;
        this.playerName=playerName;
        this.updateHighestScore();
    }

    private void setButtons(){
        restartButton=new JButton("Restart");
        restartButton.setBounds((Commons.WIDTH-300)/2,200,300,100);
        restartButton.setFont(new Font("Curlz MT",Font.BOLD,50));
        restartButton.setBackground(new Color(218,170,255));
        restartButton.setForeground(Color.white);
        restartButton.addActionListener(this);
        restartButton.setFocusable(false);
        add(restartButton);

        mainMenuButton=new JButton("Main Menu");
        mainMenuButton.setBounds((Commons.WIDTH-300)/2,370,300,100);
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
        Font font1=new Font("Jokerman",Font.BOLD,60);
        FontMetrics metrics1=this.getFontMetrics(font1);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font1);
        g2d.drawString("Game Over!",(Commons.WIDTH-metrics1.stringWidth("Game Over!"))/2,70);

        Font font2=new Font("serif",Font.BOLD,30);
        FontMetrics metrics2=this.getFontMetrics(font2);
        g2d.setFont(font2);
        g2d.drawString("score: "+score,(Commons.WIDTH-metrics2.stringWidth("score: "+score))/2,120);

        repaint();

    }

    private void updateHighestScore(){
        try {
        String path = "src/main/resources/info/" + playerName+"/highestScore.txt";
        File highestScoreFile=new File(path);
        FileOutputStream fout = null;
        Scanner scanner=new Scanner(highestScoreFile);
        int q=scanner.nextInt();
        if(score>q) {

            fout = new FileOutputStream(highestScoreFile, false);
            PrintStream out = new PrintStream(fout);
            out.println(score);
        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(restartButton)){
            graphicalAgent.startGame(playerName);
        }
        else if(e.getSource().equals(mainMenuButton)){
            graphicalAgent.mainMenu(playerName);
        }
    }
}
