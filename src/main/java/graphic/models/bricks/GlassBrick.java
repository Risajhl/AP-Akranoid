package graphic.models.bricks;

import javax.swing.*;

public class GlassBrick extends Brick{
    public GlassBrick(int x, int y) {
        super(x, y);
    }

    protected void loadImage(){
        image=new ImageIcon("src/main/resources/pics/glassBrick.png").getImage();
    }
    public String getType(){
        return "GlassBrick";
    }

}
