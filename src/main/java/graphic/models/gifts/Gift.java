package graphic.models.gifts;

import graphic.models.Sprite;

import javax.swing.*;

public class Gift extends Sprite {
    private int dy;
    private int timeLeft;

    public Gift(int GIFT_X,int GIFT_Y){
        dy=5;
        timeLeft=10;
        x=GIFT_X;
        y=GIFT_Y;
        loadImage();
        getImageDimension();
    }
    void loadImage(){

        //:)
    }

    public String getType(){
        return null;
    }

    public void move(){
        y+=dy;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

}
