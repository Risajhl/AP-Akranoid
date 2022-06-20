package graphic.models;

import graphic.Commons;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Paddle extends Sprite{

    private int dx;
    private int rightSpeed;
    private int leftSpeed;
    private int size;

    public Paddle(){
        dx=0;
        rightSpeed=10;
        leftSpeed=-10;
        size=2;
        loadImage();
        getImageDimension();
        resetState();

    }

    private void loadImage(){
        image=new ImageIcon("src/main/resources/pics/thePaddle.png").getImage();
    }



    public void setBigImage(){
        ImageIcon pigPaddle=new ImageIcon("src/main/resources/pics/theBigPaddle.png");
        this.image=pigPaddle.getImage();
        this.size=3;
        getImageDimension();
    }
    public void setSmallImage(){
        ImageIcon pigPaddle=new ImageIcon("src/main/resources/pics/theSmallPaddle.png");
        this.image=pigPaddle.getImage();
        this.size=1;
        getImageDimension();
    }

    public void setNormalImage(){
        ImageIcon pigPaddle=new ImageIcon("src/main/resources/pics/thePaddle.png");
        this.image=pigPaddle.getImage();
        this.size=2;

        getImageDimension();
    }

    public void setNormalSpeed(){
        this.rightSpeed=10;
        this.leftSpeed=-10;
    }

    private void resetState(){
        x= Commons.PADDLE_X;
        y=Commons.PADDLE_Y;
    }

    public int getCenterX(){
        int q=this.x+(imageWidth)/2;
        return q;
    }

    public void move(){
        x+=dx;

        if(x<=0){
            x=0;
        }
        if(x>=Commons.WIDTH - imageWidth-20){
            x=Commons.WIDTH - imageWidth-20;
        }

    }


    public void keyPressed(KeyEvent e){
        int key=e.getKeyCode();

        if(key==KeyEvent.VK_LEFT){
            dx=leftSpeed;
        }
        if(key==KeyEvent.VK_RIGHT){
            dx=rightSpeed;
        }

    }

    public void keyReleased(KeyEvent e){
        int key=e.getKeyCode();

        if(key==KeyEvent.VK_LEFT){
            dx=0;
        }
        if(key==KeyEvent.VK_RIGHT){
            dx=0;
        }

    }

    public int getRightSpeed() {
        return rightSpeed;
    }

    public void setRightSpeed(int speed) {
        this.rightSpeed = speed;
    }

    public int getLeftSpeed() {
        return leftSpeed;
    }

    public void setLeftSpeed(int leftSpeed) {
        this.leftSpeed = leftSpeed;
    }

    public int getSize() {
        return size;
    }

}

