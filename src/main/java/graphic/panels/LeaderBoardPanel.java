package graphic.panels;

import graphic.Commons;
import graphic.GraphicalAgent;
import graphic.Person;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

public class LeaderBoardPanel extends JPanel {
    private final Image backgroundImage;
    private final Image topTenImage;
    private final GraphicalAgent graphicalAgent;
    private JButton mainMenuButton;
    private final String playerName;
    private ArrayList<Person> topTen;


    public LeaderBoardPanel(GraphicalAgent graphicalAgent, String playerName) {
        this.setLayout(null);
        this.setFocusable(true);

        setSize(Commons.WIDTH, Commons.HEIGHT);
        setBackground(new Color(99, 176, 161));
        backgroundImage = new ImageIcon("src/main/resources/pics/pauseimage.png").getImage();
        topTenImage = new ImageIcon("src/main/resources/pics/topten.png").getImage();
        setButtons();
        this.playerName = playerName;
        this.topTen = getTopTen();
        this.graphicalAgent = graphicalAgent;
    }

    public void setButtons(){
        mainMenuButton=new JButton("Main Menu");
        mainMenuButton.setBounds(Commons.WIDTH/2-80,580,160,40);
        mainMenuButton.setFont(new Font("SERIF",Font.BOLD,20));
        mainMenuButton.setBackground(new Color(183,159,232));
        mainMenuButton.setForeground(Color.white);
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(mainMenuButton)){
                    graphicalAgent.mainMenu(playerName);
                }
            }
        });
        mainMenuButton.setFocusable(false);
        add(mainMenuButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImage, 0, 0, Commons.WIDTH, Commons.HEIGHT, this);
        g2d.setColor(Color.white);
        g2d.drawImage(topTenImage,220,10,280,90,this);
        Font font1=new Font("Constantia",Font.BOLD,30);
        if(topTen.size()<10){
            int j=0;
            for(int i=topTen.size()-1;i>=0;i--){
                Person person=topTen.get(i);
                g2d.setFont(font1);
                g2d.drawString((j+1)+": ",60,100+50*j);

                g2d.drawString(person.getName(),120,100+50*j);
                g2d.drawString(person.getScore()+"",570,100+50*j);
                j++;
            }
        }
        else {
            int j=0;
            for(int i=topTen.size()-1;i>=topTen.size()-11;i--){
                Person person=topTen.get(i);
                g2d.setFont(font1);
                g2d.drawString((j+1)+": "+person.getName(),120,100+50*j);
                g2d.drawString(person.getScore()+"",570,100+50*j);

                j++;
            }
        }

        repaint();

    }

    public ArrayList<Person> getTopTen() {
        Scanner scanner;
        Map<String, Person> people = new HashMap<>();
        ArrayList<Person> peopleByAge = new ArrayList<>(people.values());
        try {
            String path = "src/main/resources/info";
            File info = new File(path);

            for (File playerDirectory:
                 info.listFiles()) {
                File highestScoreFile=new File(path+"/"+playerDirectory.getName()+"/highestScore.txt");
               scanner=new Scanner(highestScoreFile);
               int highestScore=scanner.nextInt();
                Person person = new Person(playerDirectory.getName(), highestScore);
                people.put(person.getName(), person);

            }
            peopleByAge = new ArrayList<>(people.values());

            Collections.sort(peopleByAge, Comparator.comparing(Person::getScore));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return peopleByAge;


    }



}
