package graphic.models.gifts;

import javax.swing.*;

public class SlowBallGift extends Gift{

    public SlowBallGift(int GIFT_X, int GIFT_Y) {
        super(GIFT_X, GIFT_Y);
    }

    void loadImage(){
        image=new ImageIcon("src/main/resources/pics/theSlowBallGift.png").getImage();
    }

    public String getType(){
        return "SlowBallGift";
    }

}

