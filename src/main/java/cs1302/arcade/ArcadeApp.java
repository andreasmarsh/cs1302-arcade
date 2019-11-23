package cs1302.arcade;
import javafx.scene.Cursor;
import java.lang.Runnable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;


/**
 * Application subclass for {@code ArcadeApp}.
 * @version 2019.fa
 */
public class ArcadeApp extends Application {

    ImageView mancalaIV;
    Image mancalaImg;
    GridPane gpane;
    String mStr = ArcadeApp.class.getResource("/m.png").toExternalForm();         //mancala

    String gStr = ArcadeApp.class.getResource("/g.png").toExternalForm();         //group1


/** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        //String bgString = ArcadeApp.class.getResource("/mainMenuBG.png").toExternalForm();
        String titleStr = ArcadeApp.class.getResource("/mainTitle.png").toExternalForm();//title



        String gHighStr = ArcadeApp.class.getResource("/gHigh.png").toExternalForm();
        String scoreStr = ArcadeApp.class.getResource("/score.png").toExternalForm(); //scores

        VBox vbox = new VBox();
        HBox hbox = new HBox();
        GridPane gpane = new GridPane();
        gpane.setGridLinesVisible(true);
        // =-=-=-=-=-=-=-= title =-=-=-=-=-=-=-=-=-=
        Image titleImage = new Image(titleStr);
        ImageView titleIV = new ImageView(titleImage);
        Insets titlePad = new Insets(30.0, 0.0, 0.0, 60.0);
        String style = "-fx-background-color: rgb(170, 69, 67);"; // background color

        // =-=-=-=-=-=-=-= hbox with IVs =-=-=-=-=-=-=-=-=-=
        Insets ivPad = new Insets(50.0, 0.0, 0.0, 0.0);
        Image group1Img = new Image(gStr);
        ImageView group1IV = new ImageView(group1Img);
        Image scoreImg = new Image(scoreStr);
        ImageView scoreIV = new ImageView(scoreImg);
        Image mancalaImg = new Image(mStr);
        ImageView mancalaIV = new ImageView(mancalaImg);

        /*
        if (group1IV.isHover()) {
            group1Img = new Image(gHighStr);
            group1IV = new ImageView(group1Img);
        } else {
            group1Img = new Image(gStr);
            group1IV = new ImageView(group1Img);
        }
        */

        gpane.add(group1IV, 0, 0);
        gpane.add(scoreIV, 1, 0);
        gpane.add(mancalaIV, 2, 0);
        mancalaIV.setOnMouseEntered(this::mEnter);

/*
        hbox.getChildren().addAll(group1IV, scoreIV, mancalaIV);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        */
        vbox.getChildren().addAll(titleIV, gpane);
        vbox.setMargin(titleIV, titlePad);
        vbox.setStyle(style);

        Scene scene = new Scene(vbox, 700, 700);
        stage.setTitle("cs1302-arcade");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    } // start

    private void  mEnter(MouseEvent e) {
                System.out.println("enter mancala");
                //event.getScene().setCursor(Cursor.HAND); // on enter sent cursor to hand
                //mancalaImg = new Image(mHighStr);
                mancalaIV = new ImageView();
                gpane = new GridPane();
                Runnable r = () -> {
                    String mHighStr = ArcadeApp.class.getResource("/mHigh.png").toExternalForm();
                    System.out.println("runnable");
                    Platform.runLater(() -> {
                            mancalaIV.setImage(new Image(mHighStr));
                            gpane.add(mancalaIV, 2, 0);

                        });
                    Platform.runLater(() -> System.out.println("later"));
                };

                Thread t = new Thread(r);
                t.setDaemon(true);
                t.start();

                //gpane.add(mancalaIV, 2, 0);
    }



} // ArcadeApp
