package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StarsController {
    Image image = new Image("sample/assets/planet1.png");
    Image image2 = new Image("sample/assets/planet2.png");
    List<Star> stars = new ArrayList<>();

    @FXML
    private Canvas canvas;


    @FXML
    void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                canvas.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        canvas.getScene().getWindow().hide();
                    }
                });
            }
        });
        starInitializer();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateGc();
                move();
            }
        };
        timer.start();
    }

    private void move() {
        for (Star star : stars) {
            float currentZ = star.getZ();
            float currentX = star.getX();
            float currentY = star.getY();

            if (currentZ < 0) {
                currentZ = (float) (Math.random() * 1080) + 1;
                if (star.getType()==200||star.getType()==250){
                    currentZ=1080;
                }
                currentX= (float) (Math.random() * -3840) + 1920;
                currentY= (float) (Math.random() * -2160) + 1080;

            } else {
                currentZ -= 0.8;
            }
            star.setZ(currentZ);
            star.setX(currentX);
            star.setY(currentY);
        }
    }

    private void updateGc() {
        float x;
        float y;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 1920, 1080);

        for (Star star : stars) {
            float currentX = star.getX();
            float currentY = star.getY();
            float currentZ = star.getZ();
            x = calculatePos(currentX / currentZ, 0, 2, -150, 1920) + 1920f / 2;
            y = calculatePos(currentY / currentZ, 0, 2, -150, 1080) + 1080f / 2;
            float starSize = calculatePos(currentZ, 0, 1080, 5, 0) ;
            if (star.getType()==200){
                 starSize = calculatePos(currentZ, 0, 1080, 40, 2) ;
            }
            else if(star.getType()==250){
                starSize = calculatePos(currentZ, 0, 1080, 60, 2) ;
            }
            if (star.getType()==1) {
                gc.setFill(Color.rgb(200, 255, 200, 0.9));
                gc.fillOval(x, y, starSize, starSize);
            }
            else if (star.getType()==2){
                gc.setFill(Color.rgb(255,255,230,0.9));
                gc.fillOval(x, y, starSize, starSize);
            }
            else if (star.getType()==3){
                gc.setFill(Color.rgb(255,200,255,0.9));
                gc.fillOval(x, y, starSize, starSize);
            }
            else if (star.getType()==200){
                gc.setFill(Color.rgb(255,200,255,0.9));
                gc.drawImage(image,x,y,starSize,starSize);
            }
            else if (star.getType()==250){
                gc.setFill(Color.rgb(100,200,255,0.9));
                gc.drawImage(image2,x,y,starSize,starSize);
            }
        }
    }

    private float calculatePos(float n, float start1, float stop1, float start2, float stop2) {

        return ((n-start1)/(stop1-start1))*(stop2-start2)+start2;
    }

    private void starInitializer() {
        for (int i = 0; i < 30000; i++) {
            float x = (float) (Math.random() * -3840) + 1920;
            float y = (float) (Math.random() * -2160) + 1080;
            float z = (float) (Math.random() * (1080) + 1);
            int type = (int) (Math.random()*4+1);
            Star star = new Star(x, y, z,type);
            stars.add(star);

        }
    }

    public void exitOnKey(KeyEvent event) {
    }
}
