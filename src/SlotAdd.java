import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.util.Duration;

import java.util.Random;

public class SlotAdd extends Button{
    private Timeline timer;
    private  boolean isSlotStarted;

    SlotAdd(String text){
        super(text);
        isSlotStarted = false;
        this.setPrefHeight(50);
        this.setPrefWidth(50);
        this.setAlignment(Pos.CENTER);
        this.setBorder(new Border(new BorderStroke(null,null,new CornerRadii(5), BorderWidths.DEFAULT)));
    }

    public  void  setSlotStarted(){
        if(!isSlotStarted){
            isSlotStarted = true;
            Random rand = new Random();
            timer = new Timeline(new KeyFrame(Duration.millis(1000),event -> {
                int randomNumber = rand.nextInt(9)+1;
                this.setText(String.valueOf(randomNumber));
            }));
            timer.setCycleCount(Timeline.INDEFINITE);
            timer.play();
        }
    }

    public void stopSlot(){
        if (isSlotStarted){
            timer.stop();
            isSlotStarted=false;
        }
    }

//    スロットが回っているかどうかを返すメソッド
public  boolean getIsSlotStarted(){
        return isSlotStarted;
}
}
