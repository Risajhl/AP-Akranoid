package graphic.models.gifts;

import javax.swing.*;

public class SmallPaddleGift extends Gift{

    public SmallPaddleGift(int GIFT_X, int GIFT_Y) {
        super(GIFT_X, GIFT_Y);
    }

    void loadImage(){
        image=new ImageIcon("src/main/resources/pics/theSmallPaddleGift.png").getImage();
    }

    public String getType(){
        return "SmallPaddleGift";
    }

}
