package graphic.models.bricks;

import graphic.models.Sprite;

import javax.swing.*;
import java.awt.*;

public class Brick extends Sprite {

    public boolean destroyed;
    public boolean halfDestroyed;
    public boolean on;

    public Brick(int x,int y){
        this.x=x;
        this.y=y;
        destroyed=false;
        halfDestroyed=false;
        on=true;
        loadImage();
        getImageDimension();

    }


    public String getType(){
        return null;
    }

    public void setBrokenImage(){
        //:)
    }

    protected void loadImage(){
        image=new ImageIcon("src/main/resources/pics/glassBrick.png").getImage();
    }

    public boolean isDestroyed(){
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isHalfDestroyed() {
        return halfDestroyed;
    }

    public void setHalfDestroyed(boolean halfDestroyed) {
        this.halfDestroyed = halfDestroyed;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
