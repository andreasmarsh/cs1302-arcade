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

    Scene scene;
    ImageView gTitle;
    ImageView mTitle;
    ImageView sTitle;
    Image group1Img;
    Image mancalaImg;
    GridPane gpane = new GridPane();
    String mStr = ArcadeApp.class.getResource("/m.png").toExternalForm();         //mancala
    String mHighStr = ArcadeApp.class.getResource("/mTitle.png").toExternalForm();
    String gStr = ArcadeApp.class.getResource("/g.png").toExternalForm();         //group1
    String gHighStr = ArcadeApp.class.getResource("/gTitle.png").toExternalForm();
    String sStr = ArcadeApp.class.getResource("/score.png").toExternalForm(); //scores
    String sHighStr = ArcadeApp.class.getResource("/sTitle.png").toExternalForm(); //scores

/** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        //String bgString = ArcadeApp.class.getResource("/mainMenuBG.png").toExternalForm();
        String titleStr = ArcadeApp.class.getResource("/mainTitle.png").toExternalForm();//title

        VBox vbox = new VBox();
        HBox hbox = new HBox();

        gpane.setGridLinesVisible(true);

        // =-=-=-=-=-=-=-= title =-=-=-=-=-=-=-=-=-=
        Image titleImage = new Image(titleStr);
        ImageView titleIV = new ImageView(titleImage);
        ImageView prompt = new ImageView(); //instructions
        Insets titlePad = new Insets(30.0, 0.0, 0.0, 60.0);
        String style = "-fx-background-color: rgb(170, 69, 67);"; // background color

        // =-=-=-=-=-=-=-= hbox with IVs =-=-=-=-=-=-=-=-=-=
        Insets ivPad = new Insets(50.0, 0.0, 0.0, 0.0);
        Image group1Img = new Image(gStr);
        ImageView group1IV = new ImageView(group1Img);
        Image scoreImg = new Image(sStr);
        ImageView scoreIV = new ImageView(scoreImg);
        Image mancalaImg = new Image(mStr);
        ImageView mancalaIV = new ImageView(mancalaImg);
        ImageView mTitle = new ImageView();
        ImageView gTitle = new ImageView();
        ImageView sTitle = new ImageView();
        gTitle.setFitHeight(75.0);
        mancalaIV.setFitHeight(404);
        prompt.setFitHeight(100);

        gpane.add(gTitle, 0, 0);
        gpane.add(sTitle, 1, 0);
        gpane.add(mTitle, 2, 0);
        gpane.add(group1IV, 0, 1);
        gpane.add(scoreIV, 1, 1);
        gpane.add(mancalaIV, 2, 1);
        group1IV.setOnMouseEntered(gEnter());
        group1IV.setOnMouseMoved(gMove());
        group1IV.setOnMouseExited(gExit());
        scoreIV.setOnMouseEntered(sEnter());
        mancalaIV.setOnMouseEntered(mEnter());



        //mancalaIV.setOnMouseMoved(mInside());
        //mancalaIV.getOnMouseExited(mExit());


        vbox.getChildren().addAll(titleIV, prompt, gpane);
        vbox.setMargin(titleIV, titlePad);

        vbox.setStyle(style);

        Scene scene = new Scene(vbox, 700, 700);
        //scene.addEventFilter(MouseEvent.ANY, e -> System.out.println( e));
        stage.setTitle("cs1302-arcade");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    } // start

    private EventHandler<? super MouseEvent> mEnter() {
        return event -> {
            System.out.println("enter mancala");
            mTitle = new ImageView();
            mTitle.setImage(new Image(mHighStr));

            gpane.add(mTitle, 2, 0);
        }; //event
    } // mEnter()

    private EventHandler<? super MouseEvent> sEnter() {
        return event -> {
            System.out.println("enter scores");
            sTitle = new ImageView();
            sTitle.setImage(new Image(sHighStr));

            gpane.add(sTitle, 1, 0);
        }; //event
    } // mEnter()

    private EventHandler<? super MouseEvent> gEnter() {
        return event -> {
            System.out.println("enter group1");
            gTitle = new ImageView();
            gTitle.setImage(new Image(gHighStr));

            gpane.add(gTitle, 0, 0);
        }; //event
    } // mEnter()

    private EventHandler<? super MouseEvent> gExit() {
        return event -> {
            System.out.println("exit group1");
            gTitle = new ImageView();
            gTitle.setImage(null);

            gpane.add(gTitle, 0, 0);
        }; //event
    } // mEnter()

    private EventHandler<? super MouseEvent> gMove() {
        return event -> {
            System.out.println("move group1");
            Image check = new Image(gHighStr);
            if (gTitle.getImage() != check) {
                gTitle = new ImageView();
                gTitle.setImage(new Image(gHighStr));

                gpane.add(gTitle, 0, 0);
            }
        }; //event
    } // mEnter()


/*
    private EventHandler<? super MouseEvent> mInside() {
        return event -> {
            Image check = new Image(mHighStr);
            if (mancalaIV.getImage() != check) {
                System.out.println("inside");
                mancalaIV = new ImageView();

            //System.out.println("runnable");

                mancalaIV.setImage(new Image(mHighStr));
                //mancalaIV.getScene().setCursor(Cursor.HAND);
                gpane.add(mancalaIV, 2, 0);
            }
        }; //event
    }

    private EventHandler<? super MouseEvent> mExit() {

        return event -> {
            System.out.println("exit mancala");
            mancalaIV = new ImageView();
            mancalaIV.setImage(new Image(mStr));
            gpane.add(mancalaIV, 2, 0);
        }; //event
    } // mExit
    */

} // ArcadeApp
