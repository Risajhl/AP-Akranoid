package graphic.models.bricks;

import javax.swing.*;

public class WoodenBrick extends Brick{
    boolean halfDestroyed;
    public WoodenBrick(int x, int y) {
        super(x, y);
        halfDestroyed=false;
    }



    protected void loadImage(){
        image=new ImageIcon("src/main/resources/pics/woodenBrick.png").getImage();
    }

    public void setBrokenImage(){
        ImageIcon brokenBrick=new ImageIcon("src/main/resources/pics/brokenWoodenBrick.png");
        this.image=brokenBrick.getImage();

    }

    public String getType(){
        return "WoodenBrick";
    }
}
