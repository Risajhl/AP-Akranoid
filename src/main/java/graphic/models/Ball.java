package graphic.models;

import graphic.Commons;

import javax.swing.*;

public class Ball extends Sprite{
    private int speed=2;

    private double xdir;
    private double ydir;

    private boolean onFire;

    public Ball(){
        xdir=2;
        ydir=3;

        onFire=false;

        loadImage();
        getImageDimension();
        resetState();
    }

    private void loadImage(){
        image=new ImageIcon("src/main/resources/pics/ball.png").getImage();
    }

    public void setOnFireImage(){
        image=new ImageIcon("src/main/resources/pics/theFireBall.png").getImage();
    }

    public void setNormal(){
        onFire=false;
        image=new ImageIcon("src/main/resources/pics/ball.png").getImage();
    }

    public void move(){
        if(x==0 && y==0){
            System.out.println(":D");
            setXdir(4);
        }
        x+=xdir*speed;
        y+=ydir*speed;



        if(x<=0){
            setXdir(-1*xdir);
        }
        if(x>= Commons.WIDTH-imageWidth-20){

            setXdir(-1*xdir);
        }
        if(y<=0){
            setYdir(-1*ydir);
        }

    }

    private void resetState(){
        x=Commons.BALL_X;
        y=Commons.BALL_Y;
    }


    public boolean isOnFire() {
        return onFire;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

    public double getXdir() {
        return xdir;
    }


    public double getYdir() {
        return ydir;
    }

    public void setXdir(double xdir) {
        this.xdir = xdir;
    }


    public void setYdir(double ydir) {
        this.ydir = ydir;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCenterX(){
        return this.getX()+(this.getImageWidth()/2);
    }
}
