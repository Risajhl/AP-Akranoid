package graphic.models.gifts;

import javax.swing.*;

public class ConfusedPaddleGift extends Gift{

    public ConfusedPaddleGift(int GIFT_X, int GIFT_Y) {
        super(GIFT_X, GIFT_Y);
    }

    void loadImage(){
        image=new ImageIcon("src/main/resources/pics/theConfusedPaddleGift.png").getImage();
    }

    public String getType(){
        return "ConfusedPaddleGift";
    }

}
