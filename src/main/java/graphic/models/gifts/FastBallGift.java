package graphic.models.gifts;

import javax.swing.*;

public class FastBallGift extends Gift{

    public FastBallGift(int GIFT_X, int GIFT_Y) {
        super(GIFT_X, GIFT_Y);
    }

    void loadImage(){
        image=new ImageIcon("src/main/resources/pics/theFastBallGift.png").getImage();
    }

    public String getType(){
        return "FastBallGift";
    }

}
