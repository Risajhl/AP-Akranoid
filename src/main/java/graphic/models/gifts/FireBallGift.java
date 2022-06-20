package graphic.models.gifts;

import javax.swing.*;

public class FireBallGift extends Gift{

    public FireBallGift(int GIFT_X, int GIFT_Y) {
        super(GIFT_X, GIFT_Y);
    }

    void loadImage(){
        image=new ImageIcon("src/main/resources/pics/fireBallGift.png").getImage();
    }

    public String getType(){
        return "FireBallGift";
    }

}
