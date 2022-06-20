package graphic.models.gifts;

import javax.swing.*;

public class NewBallGift extends Gift{

    public NewBallGift(int GIFT_X, int GIFT_Y) {
        super(GIFT_X, GIFT_Y);
    }

    void loadImage(){
        image=new ImageIcon("src/main/resources/pics/theMultipleBalls.png").getImage();
    }

    public String getType(){
        return "NewBallGift";
    }

}