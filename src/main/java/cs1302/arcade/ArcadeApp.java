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

    private static Scene tScene;
    private static Scene mainScene;
    private static Stage mainStage;
    ImageView gLabelIV;
    ImageView mLabelIV;
    ImageView sLabelIV;
    private String mPath = ArcadeApp.class.getResource("/menu/m.png").toExternalForm();//mancala
    private String mLabelPath = ArcadeApp.class.getResource("/menu/mTitle.png").toExternalForm();
    private String gPath = ArcadeApp.class.getResource("/menu/g.png").toExternalForm(); //group1
    private String gLabelPath = ArcadeApp.class.getResource("/menu/gTitle.png").toExternalForm();
    private String sPath = ArcadeApp.class.getResource("/menu/score.png").toExternalForm(); //scores
    private String sLabelPath = ArcadeApp.class.getResource("/menu/sTitle.png").toExternalForm();
    private String blankImgPath = ArcadeApp.class
        .getResource("/menu/blankTItle.png").toExternalForm();
    private String blankSPath = ArcadeApp.class.getResource("/menu/bsTitle.png").toExternalForm();

    private void setMainStage(Stage stage) {
        ArcadeApp.mainStage = stage;
    }

    private void setMainScene(Scene scene) {
        ArcadeApp.mainScene = scene;
    }

/** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        String titleStr = ArcadeApp.class.getResource("/menu/mainTitle.png").toExternalForm();

        setMainStage(stage);
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
        String mStyle = "-fx-background-color: rgb(150, 59, 57);"; // background color

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
        gIV.setOnMouseClicked(e -> {
                Tetris tGame = new Tetris();
                tScene = new Scene(tGame.getRoot(), 700, 700);
                stage.setScene(tScene);
                stage.setResizable(false);
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
        mIV.setOnMouseClicked(e -> {

                Mancala mGame = new Mancala();
                Scene mScene = new Scene(mGame.getRoot(), 700, 700);
                stage.setScene(mScene);
                stage.setResizable(false);
                //stage.getScene().setRoot(mGame.getRoot());
/*              vbox.getChildren().clear();
                VBox test = new VBox();
                test.setStyle(mStyle);
                Scene mScene = new Scene(test);
*/
           });

        vbox.getChildren().addAll(titleIV, prompt, gpane);
        vbox.setMargin(titleIV, titlePad);
        vbox.setStyle(style);

        Scene scene = new Scene(vbox, 700, 700);
        setMainScene(scene);
//scene.addEventFilter(MouseEvent.ANY, e -> System.out.println( e));
        stage.setTitle("cs1302-arcade");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();

    } // start

    static public Stage getMainStage() {
        return ArcadeApp.mainStage;
    } // getStage()

    static public Scene getMainScene() {
        return ArcadeApp.mainScene;
    }

    static public Scene getTScene() {
        return ArcadeApp.tScene;
    }

} // ArcadeApp
