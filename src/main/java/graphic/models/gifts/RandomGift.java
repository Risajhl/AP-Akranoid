package graphic.models.gifts;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class RandomGift extends Gift{
    String type;
    public RandomGift(int GIFT_X, int GIFT_Y) {
        super(GIFT_X, GIFT_Y);
        ArrayList<String> giftTypes=new ArrayList<>();
        giftTypes.add("BigPaddleGift");
        giftTypes.add("ConfusedPaddleGift");
        giftTypes.add("FastBallGift");
        giftTypes.add("FireBallGift");
        giftTypes.add("NewBallGift");
        giftTypes.add("SlowBallGift");
        giftTypes.add("SmallPaddleGift");

        Random random=new Random();
        int q=random.nextInt(7);
        type= giftTypes.get(q);
    }

    void loadImage(){
        image=new ImageIcon("src/main/resources/pics/RandomGift.png").getImage();
    }

    public String getType(){
        return type;
    }
}
