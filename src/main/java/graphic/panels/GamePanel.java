package graphic.panels;

import graphic.Commons;
import graphic.GraphicalAgent;
import graphic.models.Ball;
import graphic.models.Paddle;
import graphic.models.bricks.*;
import graphic.models.gifts.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
    private ArrayList<Brick> bricks;
    private ArrayList<Gift> gifts;
    private ArrayList<Gift> activeGifts;
    private ArrayList<Ball> balls;
    private javax.swing.Timer timer;
    private javax.swing.Timer timer2;
    private Ball ball;
    private Paddle paddle;
    private boolean inGame=true;
    private Random random;
    private int crashedBricks;
    private int lives;
    private Image backgroundImage;
    private final GraphicalAgent graphicalAgent;
    private JButton pauseButton;
    private JButton restartButton;
    private int timePassed;
     String savedName;
     String playerName;

    public GamePanel(GraphicalAgent graphicalAgent,String playerName){
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(Commons.WIDTH,Commons.HEIGHT);
        this.setFocusable(true);
        this.requestFocus();
        this.setFocusTraversalKeysEnabled(false);
        this.setBackground(new Color(99,176,161));
        this.addKeyListener(this);
        random=new Random();
        this.graphicalAgent=graphicalAgent;
        gameInit();
        setButtons();
        this.timePassed=0;
        this.savedName=null;
        this.playerName=playerName;
    }


    public GamePanel(String savedName,GraphicalAgent graphicalAgent,Paddle paddle,ArrayList<Ball> balls,ArrayList<Gift> gifts,ArrayList<Gift> activeGifts,ArrayList<Brick> bricks,int crashedBricks,int lives,int timePassed,String playerName){
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(Commons.WIDTH,Commons.HEIGHT);
        this.setFocusable(true);
        this.requestFocus();
        this.setFocusTraversalKeysEnabled(false);
        this.setBackground(new Color(99,176,161));
        this.addKeyListener(this);
        random=new Random();
        this.graphicalAgent=graphicalAgent;


        this.balls=balls;
        this.bricks=bricks;
        this.gifts=gifts;
        this.activeGifts=activeGifts;
        this.paddle=paddle;
        this.crashedBricks=crashedBricks;
        this.lives=lives;
        this.timePassed=timePassed;
        this.savedName=savedName;
        this.playerName=playerName;

        backgroundImage=new ImageIcon("src/main/resources/pics/gameBackground.png").getImage();

        setButtons();
        timer=new javax.swing.Timer(Commons.PERIOD,this);
        timer2=new javax.swing.Timer(20000,new TwentySeconds());
        timer.start();
        timer2.start();

    }

    private void gameInit() {
        ball=new Ball();
        bricks=new ArrayList();
        gifts=new ArrayList();
        activeGifts=new ArrayList();
        balls=new ArrayList<>();
        paddle=new Paddle();
        crashedBricks=0;
        lives=3;
        backgroundImage=new ImageIcon("src/main/resources/pics/gameBackground.png").getImage();
        balls.add(ball);
        int m=bricks.size()/6;
        for(int i=0;i<5+m;i++){
            for(int j=0;j<6;j++){
                int q=random.nextInt(5);
                switch (q){
                    case 0: WoodenBrick woodenBrick=new WoodenBrick(j*100+55,i*40+50);
                        bricks.add(woodenBrick);
                        break;

                    case 1: GlassBrick glassBrick=new GlassBrick(j*100+55,i*40+50);
                        bricks.add(glassBrick);
                        break;

                    case 2:
                        InvisibleBrick invisibleBrick=new InvisibleBrick(j*100+55,i*40+50);
                        bricks.add(invisibleBrick);
                        break;

                    case 3: BlinkerBrick blinkerBrick=new BlinkerBrick(j*100+55,i*40+50);
                        bricks.add(blinkerBrick);
                        break;

                    case 4: GiftHolderBrick giftHolderBrick=new GiftHolderBrick(j*100+55,i*40+50);
                        bricks.add(giftHolderBrick);
                        break;
                }

            }
        }
        timer=new javax.swing.Timer(Commons.PERIOD,this);
        timer2=new javax.swing.Timer(20000,new TwentySeconds());
        timer.start();
        timer2.start();
    }

    private void setButtons(){
        pauseButton=new JButton("Pause");
        pauseButton.setFocusable(false);
        pauseButton.setFont(new Font("rockwell",Font.BOLD,15));
        pauseButton.setBounds(554,10,90,30);
        pauseButton.setBackground(new Color(169,73,138));
        pauseButton.setForeground(Color.white);
        pauseButton.addMouseListener( new MouseAdapter()
        {
            public void mouseClicked( MouseEvent e )
            {
                inGame=false;
                timer.stop();
                timer2.stop();
                graphicalAgent.pause(savedName,paddle,balls,gifts,activeGifts,bricks,crashedBricks,lives,timePassed,playerName);
            }
        } );

        add(pauseButton);

        restartButton=new JButton("Restart");
        restartButton.setFocusable(false);
        restartButton.setFont(new Font("rockwell",Font.BOLD,15));
        restartButton.setBounds(455,10,90,30);
        restartButton.setBackground(new Color(228,132,204));
        restartButton.setForeground(Color.white);
        restartButton.addMouseListener( new MouseAdapter()
        {
            public void mouseClicked( MouseEvent e )
            {
                timer.stop();
                timer2.stop();
                graphicalAgent.startGame(playerName);
            }
        } );

        add(restartButton);
    }


    public class TwentySeconds implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            for(int i=0;i<bricks.size();i++){
                Brick brick=bricks.get(i);
                brick.setY(brick.getY()+40);
            }
            for(int i=0;i<6;i++){
                int q=random.nextInt(5);
                switch (q){
                    case 0: WoodenBrick woodenBrick=new WoodenBrick(i*100+55,50);
                        bricks.add(woodenBrick);
                        break;

                    case 1: GlassBrick glassBrick=new GlassBrick(i*100+55,50);
                        bricks.add(glassBrick);
                        break;

                    case 2:InvisibleBrick invisibleBrick=new InvisibleBrick(i*100+55,50);
                        bricks.add(invisibleBrick);
                        break;

                    case 3: BlinkerBrick blinkerBrick=new BlinkerBrick(i*100+55,50);
                        bricks.add(blinkerBrick);
                        break;

                    case 4: GiftHolderBrick giftHolderBrick=new GiftHolderBrick(i*100+55,50);
                        bricks.add(giftHolderBrick);
                        break;
                }

            }

            repaint();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);

        if(inGame){
            drawObject(g2d);
        }
        else {
            gameFinished();
        }

        Toolkit.getDefaultToolkit().sync();

    }

    private void drawObject(Graphics2D g2d){

        g2d.drawImage(backgroundImage,0,0,Commons.WIDTH,Commons.HEIGHT,this);


        for (Ball ball:
                balls) {
            g2d.drawImage(ball.getImage(),ball.getX(),ball.getY(),ball.getImageWidth(),ball.getImageHeight(),this);

        }

        g2d.drawImage(paddle.getImage(),paddle.getX(),paddle.getY(),paddle.getImageWidth(),paddle.getImageHeight(),this);


        for(int i=0;i<bricks.size();i++){
            if(!bricks.get(i).isDestroyed()) {
                if (bricks.get(i).isOn()) {
                    g2d.drawImage(bricks.get(i).getImage(), bricks.get(i).getX(), bricks.get(i).getY(), bricks.get(i).getImageWidth(), bricks.get(i).getImageHeight(), this);
                }
            }
        }

        for(int i=0;i<gifts.size();i++){
            g2d.drawImage(gifts.get(i).getImage(), gifts.get(i).getX(), gifts.get(i).getY(), gifts.get(i).getImageWidth(), gifts.get(i).getImageHeight(), this);

        }

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Serif",Font.BOLD,25));
        FontMetrics metrics= getFontMetrics(g2d.getFont());
        g2d.drawString("Score: "+crashedBricks,(Commons.WIDTH - metrics.stringWidth("score: "+crashedBricks))/2, g2d.getFont().getSize());
        g2d.drawString("Lives: "+lives,10, g2d.getFont().getSize());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame) {
            doGameCycle();
        }
    }

    private void doGameCycle(){
        for (Ball ball:
                balls) {
            ball.move();
        }
        paddle.move();
        for (Gift gift : gifts) {
            gift.move();
        }
        timePassed++;
        if(timePassed%100==0){
            for (Gift gift:
                 activeGifts) {
                gift.setTimeLeft(gift.getTimeLeft()-1);
            }
        }


        for(int i=0;i<activeGifts.size();i++){
            if(activeGifts.get(i).getTimeLeft()==0){
                removeGift(activeGifts.get(i));
                activeGifts.remove(i);

            }else {
                applyGift(activeGifts.get(i));
            }
        }


        checkCollision();
        repaint();
    }

    private void stopGame(){
        inGame=false;
        timer.stop();
        timer2.stop();
    }

    private void gameFinished(){
        graphicalAgent.gameOver(crashedBricks,playerName);


    }

    private void checkCollision() {
        for (int i = 0, j = 0; i < bricks.size(); i++) {
            if (bricks.get(i).getRect().getMaxY() >= Commons.BOTTOM_EDGE && !bricks.get(i).isDestroyed()) {
                stopGame();

            }

        }

        for (int i = 0; i < gifts.size(); i++) {
            if (gifts.get(i).getRect().intersects(paddle.getRect())) {
                if(gifts.get(i).getType().equals("NewBallGift")){
                    Ball ball2=new Ball();
                    ball2.setX(paddle.getCenterX());
                    balls.add(ball2);
                }
                else {
                    activeGifts.add(gifts.get(i));
                }
                gifts.remove(i);


            }
        }


        if(balls.size()==1){
            if(balls.get(0).getRect().getMaxY()>=Commons.BOTTOM_EDGE){
                if(lives>1){
                    lives--;
                    balls.get(0).setX(paddle.getX()+(paddle.getImageWidth()/2));
                    balls.get(0).setY(Commons.BALL_Y);
                }
                else {
                    stopGame();
                }
            }
        }

        for(int i=0;i<balls.size();i++){
            if(balls.get(i).getRect().getMaxY()>Commons.BOTTOM_EDGE){
                balls.remove(i);
            }
        }

        for (Ball ball:
                balls) {

            if (ball.getRect().intersects(paddle.getRect()) && ball.getY()<=paddle.getRect().getMinY()) {

                double xCenter = paddle.getCenterX();
                ball.setYdir(-Math.abs(ball.getYdir()));

                if(ball.getCenterX() > paddle.getCenterX()) {
                    if (ball.getXdir() > 0)
                        ball.setXdir(0.002 * Math.abs(xCenter - ball.getCenterX()) + ball.getXdir());
                    else
                        ball.setXdir(-(-0.002 * Math.abs(xCenter - ball.getCenterX()) + ball.getXdir()));
                }
                else {
                    if (ball.getXdir() > 0)
                        ball.setXdir(-(0.002 * Math.abs(xCenter - ball.getCenterX()) + ball.getXdir()));
                    else
                        ball.setXdir(-0.002 * Math.abs(xCenter - ball.getCenterX()) + ball.getXdir());
                }


            }


            for (int i = 0; i < bricks.size(); i++) {
                if (ball.getRect().intersects(bricks.get(i).getRect())) {

                    if (!bricks.get(i).isDestroyed() && bricks.get(i).isOn()) {

                        if (bricks.get(i).getType().equals("GiftHolderBrick")) {
                            newGift(bricks.get(i));

                        }

                        if(!ball.isOnFire())normalCrash(ball, bricks.get(i));


                        if (bricks.get(i).getType().equals("WoodenBrick")) {
                            if (bricks.get(i).isHalfDestroyed()) {
                                bricks.get(i).setDestroyed(true);
                                crashedBricks++;
                            } else {
                                bricks.get(i).setBrokenImage();
                                bricks.get(i).setHalfDestroyed(true);
                            }

                        } else {
                            bricks.get(i).setBrokenImage();
                            bricks.get(i).setDestroyed(true);
                            crashedBricks++;

                        }
                    }

                }
            }
        }

    }

    private void normalCrash(Ball ball, Brick brick){

        int ballLeft = (int) ball.getRect().getMinX();
        int ballHeight = (int) ball.getRect().getHeight();
        int ballWidth = (int) ball.getRect().getWidth();
        int ballTop = (int) ball.getRect().getMinY();

        Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
        Point pointLeft = new Point(ballLeft - 1, ballTop);
        Point pointTop = new Point(ballLeft, ballTop - 1);
        Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);


        if(brick.getRect().contains(pointTop) || brick.getRect().contains(pointBottom)){
            ball.setYdir(-1*ball.getYdir());
        }
        else if(brick.getRect().contains(pointLeft)||brick.getRect().contains(pointRight)){
            ball.setXdir(-1*ball.getXdir());
        }
    }

    private void newGift(Brick brick){
        int q=random.nextInt(8);

        switch (q){
            case 0:
                BigPaddleGift bigPaddleGift=new BigPaddleGift(brick.getX() + (brick.imageWidth / 2), (int) brick.getRect().getMaxY());
                gifts.add(bigPaddleGift);
                break;

            case 1:
                SmallPaddleGift smallPaddleGift=new SmallPaddleGift(brick.getX() + (brick.imageWidth / 2), (int) brick.getRect().getMaxY());
                gifts.add(smallPaddleGift);
                break;

            case 2:
                NewBallGift newBallGift=new NewBallGift(brick.getX() + (brick.imageWidth / 2), (int) brick.getRect().getMaxY());
                gifts.add(newBallGift);
                break;

            case 3:
                FastBallGift fastBallGift=new FastBallGift(brick.getX() + (brick.imageWidth / 2), (int) brick.getRect().getMaxY());
                gifts.add(fastBallGift);
                break;

            case 4:
                SlowBallGift slowBallGift=new SlowBallGift(brick.getX() + (brick.imageWidth / 2), (int) brick.getRect().getMaxY());
                gifts.add(slowBallGift);
                break;

            case 5:
                ConfusedPaddleGift confusedPaddleGift=new ConfusedPaddleGift(brick.getX() + (brick.imageWidth / 2), (int) brick.getRect().getMaxY());
                gifts.add(confusedPaddleGift);
                break;

            case 6:
                FireBallGift fireBallGift=new FireBallGift(brick.getX() + (brick.imageWidth / 2), (int) brick.getRect().getMaxY());
                gifts.add(fireBallGift);
                break;
            case 7:
                RandomGift randomGift=new RandomGift(brick.getX() + (brick.imageWidth / 2), (int) brick.getRect().getMaxY());
                gifts.add(randomGift);
                break;
        }


    }

    private void removeGift(Gift gift){
        String giftType = gift.getType();
        switch (giftType) {
            case "BigPaddleGift":
                paddle.setNormalImage();
                break;

            case "SmallPaddleGift":
                paddle.setNormalImage();
                break;
            case "FastBallGift":
                for (Ball ball:
                        balls) {

                    ball.setSpeed(2);

                }
                break;

            case "SlowBallGift":

                for (Ball ball:
                        balls) {
                    ball.setSpeed(2);
                }
                break;




            case "ConfusedPaddleGift":
                paddle.setNormalSpeed();
                break;

            case "FireBallGift":
                for (Ball ball1:
                        balls) {
                    ball1.setNormal();
                    ball1.setOnFire(false);
                }

                break;


        }
    }

    private void applyGift(Gift gift) {
        String giftType = gift.getType();
        switch (giftType) {
            case "BigPaddleGift":
                paddle.setBigImage();
                break;

            case "SmallPaddleGift":
                paddle.setSmallImage();
                break;

            case "FastBallGift":
                for (Ball ball:
                        balls) {
                    ball.setSpeed(4);

                }

                break;

            case "SlowBallGift":
                for (Ball ball:
                     balls) {
                     ball.setSpeed(1);

                }

                break;

            case "ConfusedPaddleGift":
                paddle.setRightSpeed(-10);
                paddle.setLeftSpeed(10);

                break;

            case "FireBallGift":
                for (Ball ball1:
                        balls) {
                    ball1.setOnFireImage();
                    ball1.setOnFire(true);

                }

                break;


        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        paddle.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        paddle.keyReleased(e);
    }

}
