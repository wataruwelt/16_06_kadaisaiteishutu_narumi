import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public class SlotMachine extends Label{
//  アニメーションを行うためのTimelineオブジェクト
    private Timeline timer;
//    スロットが回っている状態かを管理するフィールド
    private boolean isSlotStarted;

    SlotMachine(String text){
//        superとは何か？
        super(text);
        isSlotStarted = false;
//        この下にthis.～という形で数字の見た目・揃える位置を記載しましょう
//大きさ80,80に設定
        this.setPrefSize(80,80);
//        数字の真ん中に設定
        this.setAlignment(Pos.CENTER);
//        Borderを設定。以下の記述が課題のデザイン。 何故、new が二重になるのか？
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5), BorderWidths.DEFAULT)));
    }

    public  void start(){
//        各数字のスロットが回り始めたときの処理を記載しましょう
//        連打されたときに何回もアニメーションが設定されないように
//          isSlotStarted=trueだった場合は、何もしない
        if (isSlotStarted){
            return;
            }
//            スロットが回っているのでisSlotStartedをtrueに変える
            isSlotStarted=true;
//        乱数の設定を行うオブジェクトを宣言
            Random rand = new Random();
//         フィールドに設定されているtimerにtimelineオブジェクトを設定

//        重要：この際、Timeline timerという形で宣言してしまうと
//        timerがこのsetSlotStarted内でしか使えなくなってしまい
//        stopで使えなくなってしまうので気を付けましょう！
            timer = new Timeline(new KeyFrame(Duration.millis(1000),event ->{
                int randomNumber = rand.nextInt(9)+1;
                this.setText(String.valueOf(randomNumber));
            } ));

//            アニメーションをスタート
            timer.setCycleCount(Timeline.INDEFINITE);
            timer.play();
        }
        public void stop(){
//        この中に各数字のスロットが止まった時の処理を記載しましょう
     if (isSlotStarted){
         timer.stop();
         isSlotStarted=false;
     }
    }



    }

