package graphic.models.bricks;

import javax.swing.*;

public class GiftHolderBrick extends Brick{
    public GiftHolderBrick(int x, int y) {
        super(x, y);
    }

    protected void loadImage(){
        image=new ImageIcon("src/main/resources/pics/giftHolderBrick.png").getImage();
    }

    public String getType(){
        return "GiftHolderBrick";
    }
}
