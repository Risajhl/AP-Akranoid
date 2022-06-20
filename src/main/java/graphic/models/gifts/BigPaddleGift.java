package graphic.models.gifts;

import javax.swing.*;

public class BigPaddleGift extends Gift{

    public BigPaddleGift(int GIFT_X, int GIFT_Y) {
        super(GIFT_X, GIFT_Y);
    }

    void loadImage(){
        image=new ImageIcon("src/main/resources/pics/theBigPaddleGift.png").getImage();
    }

    public String getType(){
        return "BigPaddleGift";
    }

}

