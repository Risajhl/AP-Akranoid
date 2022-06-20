package graphic;

import graphic.models.Ball;
import graphic.models.Paddle;
import graphic.models.bricks.*;
import graphic.models.gifts.*;
import graphic.panels.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GraphicalAgent {
    GameFrame gameFrame;



    public GraphicalAgent() {
        gameFrame = new GameFrame();
        String playerName = JOptionPane.showInputDialog("Enter your name:");
        if(playerName!=null && !playerName.equals("")) {
            loadPlayer(playerName);
            gameFrame.setContentPane(new MainMenu(this, playerName));
            gameFrame.setVisible(true);
        }
    }

    public void startGame(String playerName) {
        gameFrame.setVisible(false);
        gameFrame.getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel(this,playerName);
        gamePanel.requestFocus();
        gameFrame.setContentPane(gamePanel);
        gameFrame.getContentPane().repaint();

        gameFrame.setVisible(true);

    }

    public void resumeGame(String savedName,Paddle paddle, ArrayList<Ball> balls, ArrayList<Gift> gifts, ArrayList<Gift> activeGifts, ArrayList<Brick> bricks, int crashedBricks, int lives, int timePassed,String playerName) {
        gameFrame.setVisible(false);
        gameFrame.getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel(savedName,this, paddle, balls, gifts, activeGifts, bricks, crashedBricks, lives, timePassed,playerName);
        gamePanel.requestFocus();
        gameFrame.setContentPane(gamePanel);
        gameFrame.getContentPane().repaint();

        gameFrame.setVisible(true);

    }


    public void pause(String savedName,Paddle paddle, ArrayList<Ball> balls, ArrayList<Gift> gifts, ArrayList<Gift> activeGifts, ArrayList<Brick> bricks, int crashedBricks, int lives, int timePassed,String playerName) {
        gameFrame.getContentPane().removeAll();
        gameFrame.setContentPane(new PausePanel(savedName,this, paddle, balls, gifts, activeGifts, bricks, crashedBricks, lives, timePassed,playerName));
        gameFrame.getContentPane().repaint();
    }


    public void mainMenu(String playerName) {
        gameFrame.getContentPane().removeAll();
        gameFrame.setContentPane(new MainMenu(this,playerName));
        gameFrame.getContentPane().repaint();

    }

    public void gameOver(int score,String playerName) {
        gameFrame.getContentPane().removeAll();
        gameFrame.setContentPane(new GameOver(this, score,playerName));
        gameFrame.getContentPane().repaint();
    }

    public void leaderBoard(String playerName){
        gameFrame.getContentPane().removeAll();
        gameFrame.setContentPane(new LeaderBoardPanel(this,playerName));
        gameFrame.getContentPane().repaint();
    }

    public void exitGame() {
        System.exit(0);
    }


    public void saveGame(String gameName, Paddle paddle, ArrayList<Ball> balls, ArrayList<Gift> gifts, ArrayList<Gift> activeGifts, ArrayList<Brick> bricks, int crashedBricks, int lives, int timePassed,String playerName) {
        try {

            String path = "src/main/resources/info/" +playerName+"/"+ gameName;
            File gameDirectory = new File(path);

            File paddleFile;
            File ballsFile;
            File giftsFile;
            File activeGiftsFile;
            File bricksFile;
            File integersFile;

            if (gameDirectory.exists()) {

                 paddleFile = new File(path + "/paddle.txt");
                 ballsFile = new File(path + "/balls.txt");
                 giftsFile = new File(path + "/gifts.txt");
                 activeGiftsFile = new File(path + "/activeGifts.txt");
                 bricksFile = new File(path + "/bricks.txt");
                 integersFile = new File(path + "/integers.txt");
            } else {
                gameDirectory.mkdir();
                 paddleFile = new File(path + "/paddle.txt");
                 ballsFile = new File(path + "/balls.txt");
                 giftsFile = new File(path + "/gifts.txt");
                 activeGiftsFile = new File(path + "/activeGifts.txt");
                 bricksFile = new File(path + "/bricks.txt");
                 integersFile = new File(path + "/integers.txt");
                List<File> files = new ArrayList<>();
                files.add(paddleFile);
                files.add(ballsFile);
                files.add(giftsFile);
                files.add(activeGiftsFile);
                files.add(bricksFile);
                files.add(integersFile);
                for (File file :
                        files) {
                    file.createNewFile();
                }
            }

            FileOutputStream fout = new FileOutputStream(paddleFile, false);
            PrintStream out = new PrintStream(fout);
            out.println(paddle.getX());
            out.println(paddle.getY());
            out.println(paddle.getRightSpeed());
            out.println(paddle.getLeftSpeed());
            out.println(paddle.getSize());

            FileOutputStream fout1 = new FileOutputStream(ballsFile, false);
            PrintStream out1 = new PrintStream(fout1);
            out1.println(balls.size());
            out1.println();
            for(int i=0;i<balls.size();i++){
                out1.println(balls.get(i).getX());
                out1.println(balls.get(i).getY());
                out1.println(balls.get(i).getYdir());
                out1.println(balls.get(i).getXdir());
                out1.println(balls.get(i).getSpeed());
                out1.println(balls.get(i).isOnFire());
                out1.println();
            }

            FileOutputStream fout2 = new FileOutputStream(giftsFile, false);
            PrintStream out2 = new PrintStream(fout2);
            out2.println(gifts.size());
            out2.println();
            for(int i=0;i<gifts.size();i++){

                out2.println(gifts.get(i).getType());
                out2.println(gifts.get(i).getX());
                out2.println(gifts.get(i).getY());
                out2.println(gifts.get(i).getTimeLeft());
                out2.println();
            }

            FileOutputStream fout3 = new FileOutputStream(activeGiftsFile, false);
            PrintStream out3 = new PrintStream(fout3);
            out3.println(activeGifts.size());
            out3.println();
            for(int i=0;i<activeGifts.size();i++){
                out3.println(activeGifts.get(i).getType());
                out3.println(activeGifts.get(i).getX());
                out3.println(activeGifts.get(i).getY());
                out3.println(activeGifts.get(i).getTimeLeft());
                out3.println();
            }

            FileOutputStream fout4 = new FileOutputStream(bricksFile, false);
            PrintStream out4 = new PrintStream(fout4);
            out4.println(bricks.size());
            out4.println();
            for(int i=0;i<bricks.size();i++){

                out4.println(bricks.get(i).getType());
                out4.println(bricks.get(i).getX());
                out4.println(bricks.get(i).getY());
                out4.println(bricks.get(i).isDestroyed());
                out4.println(bricks.get(i).isHalfDestroyed());
                out4.println(bricks.get(i).isOn());
                out4.println();
            }

            FileOutputStream fout5 = new FileOutputStream(integersFile, false);
            PrintStream out5 = new PrintStream(fout5);

            out5.println(crashedBricks);
            out5.println(lives);
            out5.println(timePassed);


            JOptionPane.showMessageDialog(null,"Saved!");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadGame(String gameName,String playerName){
        try {
            String path="src/main/resources/info/"+playerName+"/"+gameName;
            File gameDirectory=new File(path);
            if(gameDirectory.exists()) {


                String activeGiftsPath = "src/main/resources/info/" +playerName+"/"+ gameName + "/activeGifts.txt";
                File gameFile = new File(activeGiftsPath);
                Scanner scanner = new Scanner(gameFile);
                int num = scanner.nextInt();
                scanner.nextLine();
                ArrayList<Gift> activeGifts = new ArrayList<>();
                if (num != 0) {
                    for (int i = 0; i < num; i++) {
                        scanner.nextLine();
                        String type = scanner.nextLine();
                        int GIFT_X = scanner.nextInt();
                        int GIFT_Y = scanner.nextInt();
                        int timeLeft = scanner.nextInt();

                        switch (type) {
                            case "BigPaddleGift":
                                BigPaddleGift bigPaddleGift = new BigPaddleGift(GIFT_X, GIFT_Y);
                                bigPaddleGift.setTimeLeft(timeLeft);
                                activeGifts.add(bigPaddleGift);
                                break;
                            case "ConfusedPaddleGift":
                                ConfusedPaddleGift confusedPaddleGift = new ConfusedPaddleGift(GIFT_X, GIFT_Y);
                                confusedPaddleGift.setTimeLeft(timeLeft);
                                activeGifts.add(confusedPaddleGift);
                                break;
                            case "FastBallGift":
                                FastBallGift fastBallGift = new FastBallGift(GIFT_X, GIFT_Y);
                                fastBallGift.setTimeLeft(timeLeft);
                                activeGifts.add(fastBallGift);
                                break;
                            case "FireBallGift":
                                FireBallGift fireBallGift = new FireBallGift(GIFT_X, GIFT_Y);
                                fireBallGift.setTimeLeft(timeLeft);
                                activeGifts.add(fireBallGift);
                                break;
                            case "NewBallGift":
                                NewBallGift newBallGift = new NewBallGift(GIFT_X, GIFT_Y);
                                newBallGift.setTimeLeft(timeLeft);
                                activeGifts.add(newBallGift);
                                break;
                            case "SlowBallGift":
                                SlowBallGift slowBallGift = new SlowBallGift(GIFT_X, GIFT_Y);
                                slowBallGift.setTimeLeft(timeLeft);
                                activeGifts.add(slowBallGift);
                                break;
                            case "SmallPaddleGift":
                                SmallPaddleGift smallPaddleGift = new SmallPaddleGift(GIFT_X, GIFT_Y);
                                smallPaddleGift.setTimeLeft(timeLeft);
                                activeGifts.add(smallPaddleGift);
                                break;
                        }
                    }
                }


                String ballsPath = "src/main/resources/info/" +playerName+"/"+ gameName + "/balls.txt";
                File ballsFile = new File(ballsPath);
                scanner = new Scanner(ballsFile);
                int num1 = scanner.nextInt();
                scanner.nextLine();
                ArrayList<Ball> balls = new ArrayList<>();
                if (num1 != 0) {
                    for (int i = 0; i < num1; i++) {
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        double ydir = scanner.nextDouble();
                        double xdir = scanner.nextDouble();
                        int speed = scanner.nextInt();
                        scanner.nextLine();
                        String isOnFire = scanner.nextLine();
                        scanner.nextLine();

                        Ball ball = new Ball();
                        ball.setX(x);
                        ball.setY(y);
                        ball.setXdir(xdir);
                        ball.setYdir(ydir);
                        ball.setSpeed(speed);
                        ball.setOnFire(isOnFire.equals("true"));

                        balls.add(ball);

                    }

                }

                String bricksPath = "src/main/resources/info/" +playerName+"/"+ gameName + "/bricks.txt";
                File bricksFile = new File(bricksPath);
                scanner = new Scanner(bricksFile);
                int num2 = scanner.nextInt();
                ArrayList<Brick> bricks = new ArrayList<>();
                System.out.println(scanner.nextLine());


                if (num2 != 0) {
                    for (int i = 0; i < num2; i++) {
                        scanner.nextLine();

                        String type = scanner.nextLine();
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        scanner.nextLine();
                        String isDestroyed = scanner.nextLine();
                        String isHalfDestroyed = scanner.nextLine();
                        String isOn = scanner.nextLine();

                        switch (type) {
                            case "BlinkerBrick":
                                BlinkerBrick blinkerBrick = new BlinkerBrick(x, y);
                                blinkerBrick.setDestroyed(isDestroyed.equals("true"));
                                blinkerBrick.setHalfDestroyed(isHalfDestroyed.equals("true"));
                                blinkerBrick.setOn(isOn.equals("true"));
                                bricks.add(blinkerBrick);
                                break;
                            case "GiftHolderBrick":
                                GiftHolderBrick giftHolderBrick = new GiftHolderBrick(x, y);
                                giftHolderBrick.setDestroyed(isDestroyed.equals("true"));
                                giftHolderBrick.setHalfDestroyed(isHalfDestroyed.equals("true"));
                                giftHolderBrick.setOn(isOn.equals("true"));
                                bricks.add(giftHolderBrick);
                                break;
                            case "GlassBrick":
                                GlassBrick glassBrick = new GlassBrick(x, y);
                                glassBrick.setDestroyed(isDestroyed.equals("true"));
                                glassBrick.setHalfDestroyed(isHalfDestroyed.equals("true"));
                                glassBrick.setOn(isOn.equals("true"));
                                bricks.add(glassBrick);
                                break;
                            case "InvisibleBrick":
                                InvisibleBrick invisibleBrick = new InvisibleBrick(x, y);
                                invisibleBrick.setDestroyed(isDestroyed.equals("true"));
                                invisibleBrick.setHalfDestroyed(isHalfDestroyed.equals("true"));
                                invisibleBrick.setOn(isOn.equals("true"));
                                bricks.add(invisibleBrick);
                                break;
                            case "WoodenBrick":
                                WoodenBrick woodenBrick = new WoodenBrick(x, y);
                                woodenBrick.setDestroyed(isDestroyed.equals("true"));
                                woodenBrick.setHalfDestroyed(isHalfDestroyed.equals("true"));
                                woodenBrick.setOn(isOn.equals("true"));
                                bricks.add(woodenBrick);
                                break;
                        }

                    }
                }

                String giftsPath = "src/main/resources/info/" +playerName+"/"+ gameName + "/gifts.txt";
                File giftsFile = new File(giftsPath);
                scanner = new Scanner(giftsFile);
                int num3 = scanner.nextInt();
                scanner.nextLine();
                ArrayList<Gift> gifts = new ArrayList<>();
                if (num3 != 0) {
                    for (int i = 0; i < num3; i++) {
                        scanner.nextLine();
                        String type = scanner.nextLine();
                        int GIFT_X = scanner.nextInt();
                        int GIFT_Y = scanner.nextInt();
                        int timeLeft = scanner.nextInt();
                        scanner.nextLine();

                        switch (type) {
                            case "BigPaddleGift":
                                BigPaddleGift bigPaddleGift = new BigPaddleGift(GIFT_X, GIFT_Y);
                                bigPaddleGift.setTimeLeft(timeLeft);
                                gifts.add(bigPaddleGift);
                                break;
                            case "ConfusedPaddleGift":
                                ConfusedPaddleGift confusedPaddleGift = new ConfusedPaddleGift(GIFT_X, GIFT_Y);
                                confusedPaddleGift.setTimeLeft(timeLeft);
                                gifts.add(confusedPaddleGift);
                                break;
                            case "FastBallGift":
                                FastBallGift fastBallGift = new FastBallGift(GIFT_X, GIFT_Y);
                                fastBallGift.setTimeLeft(timeLeft);
                                gifts.add(fastBallGift);
                                break;
                            case "FireBallGift":
                                FireBallGift fireBallGift = new FireBallGift(GIFT_X, GIFT_Y);
                                fireBallGift.setTimeLeft(timeLeft);
                                gifts.add(fireBallGift);
                                break;
                            case "NewBallGift":
                                NewBallGift newBallGift = new NewBallGift(GIFT_X, GIFT_Y);
                                newBallGift.setTimeLeft(timeLeft);
                                gifts.add(newBallGift);
                                break;
                            case "SlowBallGift":
                                SlowBallGift slowBallGift = new SlowBallGift(GIFT_X, GIFT_Y);
                                slowBallGift.setTimeLeft(timeLeft);
                                gifts.add(slowBallGift);
                                break;
                            case "SmallPaddleGift":
                                SmallPaddleGift smallPaddleGift = new SmallPaddleGift(GIFT_X, GIFT_Y);
                                smallPaddleGift.setTimeLeft(timeLeft);
                                gifts.add(smallPaddleGift);
                                break;
                            case "RandomGift":
                                RandomGift randomGift = new RandomGift(GIFT_X, GIFT_Y);
                                randomGift.setTimeLeft(timeLeft);
                                gifts.add(randomGift);
                                break;
                        }
                    }
                }


                String paddlePath = "src/main/resources/info/" +playerName+"/"+ gameName + "/paddle.txt";
                File paddleFile = new File(paddlePath);
                scanner = new Scanner(paddleFile);
                int paddleX = scanner.nextInt();
                int paddleY = scanner.nextInt();
                int paddleRightSpeed = scanner.nextInt();
                int paddleLeftSpeed = scanner.nextInt();
                int paddleSize = scanner.nextInt();
                Paddle paddle = new Paddle();
                paddle.setX(paddleX);
                paddle.setY(paddleY);
                paddle.setRightSpeed(paddleRightSpeed);
                paddle.setLeftSpeed(paddleLeftSpeed);
                switch (paddleSize) {
                    case 1:
                        paddle.setSmallImage();
                        break;
                    case 2:
                        paddle.setNormalImage();
                        break;
                    case 3:
                        paddle.setBigImage();
                        break;
                }

                String integersPath = "src/main/resources/info/" +playerName+"/"+ gameName + "/integers.txt";
                File integersFile = new File(integersPath);
                scanner = new Scanner(integersFile);
                int crashedBricks = scanner.nextInt();
                int lives = scanner.nextInt();
                int timePassed = scanner.nextInt();


                resumeGame(gameName, paddle, balls, gifts, activeGifts, bricks, crashedBricks, lives, timePassed,playerName);


            }
            else {
                JOptionPane.showMessageDialog(null,"This game can not be found in this player's savedGames!");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadPlayer(String playerName){
        String path = "src/main/resources/info/" + playerName;
        File playerDirectory = new File(path);
        if(!playerDirectory.exists()){
            playerDirectory.mkdir();
            File highestScoreFile=new File(path+"/highestScore.txt");
            try {
                highestScoreFile.createNewFile();
                FileOutputStream fout = null;
                try {
                    fout = new FileOutputStream(highestScoreFile, false);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                PrintStream out = new PrintStream(fout);
                out.println(0);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
