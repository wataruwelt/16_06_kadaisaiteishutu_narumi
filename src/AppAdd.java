import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class AppAdd extends Application{
//    全体でスロットが走っているかを確認する変数
//    Startボタンを押す前に、スロットのボタンを押して判定が走ってしまうのを避けるために設定
    boolean isSlotStarted = false;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Slot Machine");

        // スロット部のHBoxの設定
        HBox slot_box = new HBox(20d);
        slot_box.setAlignment(Pos.CENTER);

        // ArrayListにスロットラベルを入れる
        ArrayList<SlotAdd> slotArr = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            slotArr.add(new SlotAdd("1"));
        }

        slot_box.getChildren().addAll(slotArr);

        // Start・Stopボタンを設定するHBoxの設定
        HBox buttonBox = new HBox(10d);
        buttonBox.setAlignment(Pos.CENTER);
        Button startButton = new Button("Start");
        buttonBox.getChildren().add(startButton);

        // スロット部とStart・Stopボタンを設定するVBoxの設定
        VBox all_box = new VBox(20d);
        all_box.setAlignment(Pos.CENTER);
        all_box.getChildren().add(slot_box);
        all_box.getChildren().add(buttonBox);

        // StartボタンのAction設定
        startButton.setOnAction(event -> {
            for(int i = 0; i < slotArr.size(); i++) {
                slotArr.get(i).setSlotStarted();
            }
            // 全体のステータス
            isSlotStarted = true;
        });

        // 各ボタンのAction設定
        for(int i = 0;i < slotArr.size(); i++){
            slotArr.get(i).setOnAction(event -> {
                // eventからこのボタンがどのボタンなのかを取得する
                // (外側のローカル変数を利用することができなくslotArr.get(i).stopSlot()が利用できないため
                // この方法を利用する
                SlotAdd thisSlotLabel = ((SlotAdd)event.getSource());
                thisSlotLabel.stopSlot();

                // 他のスロットが回っているか確認
                Boolean isOtherSlotStarted = false;
                // もしほかのスロットが回っているとisOtherSlotStartedはtrueになる。
                for(int j = 0; j < slotArr.size(); j++){
                    isOtherSlotStarted = isOtherSlotStarted || slotArr.get(j).getIsSlotStarted();
                }
                // もし他のラベルが回っていなかったら かつ 全体でスロットが走っていたら
                if(!isOtherSlotStarted && isSlotStarted) {
                    // 全てのラベルの値をres_arrに格納する
                    ArrayList<String> res_arr = new ArrayList<>();
                    for(int k = 0; k < slotArr.size(); k++){
                        res_arr.add(slotArr.get(k).getText());
                    }

                    // res_arrに格納されている最初の値が何個入っているかカウントし、全体の大きさと同じだったら
                    // 全て同じ値と判定する。
                    if (Collections.frequency(res_arr, res_arr.get(0)) == res_arr.size()) {
                        // 新しいウインドウを生成
                        Stage newStage = new Stage();

                        // モーダルウインドウに設定
                        newStage.initModality(Modality.APPLICATION_MODAL);
                        // オーナーを設定
                        newStage.initOwner(primaryStage);

                        // 新しいウインドウ内に配置するコンテンツを生成
                        HBox hbox = new HBox();
                        hbox.setPadding(new Insets(10));
                        Label label = new Label("おめでとう！");
                        label.setFont(new Font(20d));
                        hbox.getChildren().add(label);

                        newStage.setScene(new Scene(hbox));
                        // 音を鳴らす設定
                        AudioClip audioClip = new AudioClip("http://nakano-sound.com/freedeta/%E3%83%95%E3%82%A1%E3%83%B3%E3%83%95%E3%82%A1%E3%83%BC%E3%83%AC9%EF%BC%88%E3%82%B2%E3%83%BC%E3%83%A0%E5%90%91%E3%81%91%EF%BC%89.mp3");
                        audioClip.play();

                        // 新しいウインドウを表示
                        newStage.show();
                    }

                    // 最後のスロットボタンがクリックされたということなのでisSlotStartedはfalseにする。
                    isSlotStarted = false;
                }

            });
        }

        Scene scene = new Scene(all_box, 350, 120);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
