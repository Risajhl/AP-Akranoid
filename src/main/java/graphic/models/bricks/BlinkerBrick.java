package graphic.models.bricks;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class BlinkerBrick extends Brick{

    public BlinkerBrick(int x, int y) {
        super(x, y);
        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                setOn(!isOn());
            }
        }, 3000,2000 );
        setOn(!isOn());


    }

    public String getType(){
        return "BlinkerBrick";
    }

    protected void loadImage(){
        image=new ImageIcon("src/main/resources/pics/blinkerBrick.png").getImage();
    }

}
