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
    ImageView gLabelIV;
    ImageView mLabelIV;
    ImageView sLabelIV;
    String mPath = ArcadeApp.class.getResource("/m.png").toExternalForm();//mancala
    String mLabelPath = ArcadeApp.class.getResource("/mTitle.png").toExternalForm();
    String gPath = ArcadeApp.class.getResource("/g.png").toExternalForm(); //group1
    String gLabelPath = ArcadeApp.class.getResource("/gTitle.png").toExternalForm();
    String sPath = ArcadeApp.class.getResource("/score.png").toExternalForm(); //scores
    String sLabelPath = ArcadeApp.class.getResource("/sTitle.png").toExternalForm();
    String blankImgPath = ArcadeApp.class.getResource("/blankTItle.png").toExternalForm();
    String blankSPath = ArcadeApp.class.getResource("/bsTitle.png").toExternalForm();

/** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        String titleStr = ArcadeApp.class.getResource("/mainTitle.png").toExternalForm();//title

        GridPane gpane = new GridPane();
        VBox vbox = new VBox();
        HBox hbox = new HBox();

        //gpane.setGridLinesVisible(true);

// =-=-=-=-=-=-=-= title =-=-=-=-=-=-=-=-=-=
// this section will eventually be put in a seperate method to
// fit the < 60 lined method rule
        Image titleImage = new Image(titleStr);
        ImageView titleIV = new ImageView(titleImage);
        ImageView prompt = new ImageView(); //instructions
        Insets titlePad = new Insets(30.0, 0.0, 0.0, 60.0);
        String style = "-fx-background-color: rgb(170, 69, 67);"; // background color

// =-=-=-=-=-=-=-= gridpane with children =-=-=-=-=-=-=-=-=-=
// this section will eventually be put in a seperate method to
// fit the < 60 lined method rule
        Insets ivPad = new Insets(50.0, 0.0, 0.0, 0.0);
        Image blankImg = new Image(blankImgPath);
        Image blankSImg = new Image(blankSPath);
// variables for group1Game visuals
        Image gImg = new Image(gPath); // image for green arcade box
        Image gLabel = new Image(gLabelPath); // label on top of green
        ImageView gIV = new ImageView(gImg); // set green
        ImageView gTitle = new ImageView(); // to be used onMouseEnter
// variables for score visuals
        Image sImg = new Image(sPath); // image for plaque
        Image sLabel = new Image(sLabelPath); // label on top of plaque
        ImageView sIV = new ImageView(sImg); // set plaque
        ImageView sTitle = new ImageView();
// variables for mancala visuals
        Image mImg = new Image(mPath); // image for blue arcade box
        Image mLabel = new Image(mLabelPath); // labe on top of blue
        ImageView mIV = new ImageView(mImg); // set blue
        ImageView mTitle = new ImageView();
// add some padding
        gTitle.setFitHeight(75.0);
        gTitle.setFitWidth(300.0);
        mIV.setFitHeight(404);
        prompt.setFitHeight(100);
// add IV's to gidpane
        gpane.add(gTitle, 0, 0); // labels
        gpane.add(sTitle, 1, 0);
        gpane.add(mTitle, 2, 0);
        gpane.add(gIV, 0, 1); // machines and plaque
        gpane.add(sIV, 1, 1);
        gpane.add(mIV, 2, 1);

// =-=-=-=-=-=-=-= mouse events =-=-=-=-=-=-=-=-=-=
// group1Game
        gIV.setOnMouseEntered(e -> {
                stage.getScene().getRoot().setCursor(Cursor.HAND);
                gTitle.setImage(gLabel);
            });
        gIV.setOnMouseExited(e -> {
                stage.getScene().getRoot().setCursor(Cursor.DEFAULT);
                gTitle.setImage(blankImg); // on exit remove label
            });
// scores
        sIV.setOnMouseEntered(e -> {
                stage.getScene().getRoot().setCursor(Cursor.HAND);
                sTitle.setImage(sLabel);
            });
        sIV.setOnMouseExited(e -> {
                stage.getScene().getRoot().setCursor(Cursor.DEFAULT);
                sTitle.setImage(blankSImg);
            });
// mancala
        mIV.setOnMouseEntered(e -> {
                stage.getScene().getRoot().setCursor(Cursor.HAND);
                mTitle.setImage(mLabel);
            });
        mIV.setOnMouseExited(e -> {
                stage.getScene().getRoot().setCursor(Cursor.DEFAULT);
                mTitle.setImage(blankImg);
            });


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

} // ArcadeApp
