package graphic.models.bricks;

import javax.swing.*;

public class InvisibleBrick extends GlassBrick{
    public InvisibleBrick(int x, int y) {
        super(x, y);
    }
    protected void loadImage(){
        image=new ImageIcon("src/main/resources/pics/invisibleBrick.png").getImage();
    }

    public String getType(){
        return "InvisibleBrick";
    }
}