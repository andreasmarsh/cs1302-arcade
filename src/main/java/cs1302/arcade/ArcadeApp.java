package cs1302.arcade;

import java.lang.InterruptedException;
import javafx.scene.effect.Reflection;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.image.WritableImage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javafx.stage.Modality;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import javafx.scene.Group;
import javafx.scene.paint.Color;
import java.util.Scanner;
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
    private static String tLine;
    private static ArrayList<String> tList;
    private static File tFile;
    private String mPath = "file:resources/menu/m.png"; //mancala
    private String mLabelPath = "file:resources/menu/mTitle.png";
    private String gPath = "file:resources/menu/g.png"; //group1
    private String gLabelPath = "file:resources/menu/gTitle.png";
    private String sPath = "file:resources/menu/score.png"; //scores
    private String sLabelPath = "file:resources/menu/sTitle.png";
    private String blankImgPath = "file:resources/menu/blankTItle.png";
    private String blankSPath = "file:resources/menu/bsTitle.png";
    ImageView titleIV;
    Rectangle r1;
    Rectangle r2;
    VBox vbox;
    ScaleTransition r1T;
    ScaleTransition r2T;

    /**
     * Sets the mainStage of ArcadeApp.
     * @param stage the stage to be set as mainStage
     */
    private void setMainStage(Stage stage) {
        ArcadeApp.mainStage = stage;
    }

    /**
     * Sets the mainScene of ArcadeApp.
     * @param scene the scene to be set as mainScene
     */
    private void setMainScene(Scene scene) {
        ArcadeApp.mainScene = scene;
    }

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        String titleStr = "file:resources/menu/mainTitle.png";

        setMainStage(stage);
        GridPane gpane = new GridPane();
        vbox = new VBox();
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

// animation attempt


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
            //tScene = new Scene(tGame.getRoot(), 700, 700);
            //stage.setScene(tScene);
            //stage.setResizable(false);
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
        sIV.setOnMouseClicked(e -> {
            getScores();
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
        });

        r1 = new Rectangle(0, 1, 700, 1);
        r1.setFill(Color.BLACK);
        r2 = new Rectangle(0, 699, 700, 1);
        r2.setFill(Color.web("#aa4543"));

        ScaleTransition r1T = new ScaleTransition(Duration.seconds(2), r1);
        r1T.setToY(1400);
        r1T.setCycleCount(1);
        r1T.play();


        ScaleTransition r2T = new ScaleTransition(Duration.seconds(3), r2);
        r2T.setToY(-1400);
        r2T.setCycleCount(1);

        r1T.setOnFinished(e -> {
                r2T.play();
            });

        vbox.getChildren().addAll(r1, r2);

        r2T.setOnFinished(e -> {
                System.out.println("finished");
                vbox.getChildren().addAll(titleIV, prompt, gpane);
                vbox.setMargin(titleIV, titlePad);
                vbox.setStyle(style);
            });

        Scene scene = new Scene(vbox, 700, 700);

        setMainScene(scene);

//scene.addEventFilter(MouseEvent.ANY, e -> System.out.println( e));
        stage.setTitle("cs1302-arcade");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    } // start

    /**
     * creates a .txt file to manage highscores.
     */
    private void getScores() {
        File tFile = new File("tetScores.txt");
        File mFile = new File("manScores.txt");

        // hi!! it's me! please delete this comment after you read it,
        // line 163 handles what happens when you click on the scores
        // but it should be function if you just call this one method

        try {
            Scanner sc = new Scanner(tFile);

            // tList contains the scores from the tetScores.txt AKA the tetris highscores
            ArrayList<String> tList = new ArrayList<String>();

            // this while adds all the scores from the file to the list
            while (sc.hasNextLine()) {
                String tLine = sc.nextLine();
                tList.add(tLine);
            }

            //make new scanner to scan mancala file
            sc = new Scanner(mFile);
            // mList has mancala scores
            ArrayList<String> mList = new ArrayList<String>();

            while (sc.hasNextLine()) {
                String mLine = sc.nextLine();
                mList.add(mLine);
            }

            // this method can be found right after this one
            // aka lines 237
            showScores(tList, mList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } //getScores()

    /**
     * handles highscore window.
     * @param tList list containing tetris scores
     * @param mList list containing mancala scores
     */
    public void showScores(ArrayList<String> tList, ArrayList<String> mList) {
        Stage newWindow = new Stage();

        // hi!!! me again, this method is almost an exact copy of
        // mancala control's window
        // PS I know the list are not ordered/sorted
        // but according to the requirements they dont mention that they have to be

        Text tHeader = new Text("TETRIS HIGHSCORES:\n");
        String tScores = "";
        Text mHeader = new Text("\nMANCALA HIGHSCORES:\n");
        String mScores = "";

        for (int i = 0; i < tList.size(); i++) {
            tScores += tList.get(i) + "\n";
        } // for
        Text tScoresText = new Text(tScores);

        for (int i = 0; i < mList.size(); i++) {
            mScores += mList.get(i) + "\n";
        } // for
        Text mScoresText = new Text(mScores);

        // adds all texts to controlBox
        VBox scoresBox = new VBox();
        scoresBox.getChildren().addAll(tHeader, tScoresText, mHeader, mScoresText);

        scoresBox.setAlignment(Pos.TOP_CENTER);
        Scene score = new Scene(scoresBox, 420, 280);

        // New window of stage
        newWindow.setMaxWidth(420);
        newWindow.setMaxHeight(280);
        newWindow.setMinWidth(420);
        newWindow.setMinHeight(280);

        newWindow.setTitle("High Scores");
        newWindow.sizeToScene();
        newWindow.setScene(score);
        newWindow.setResizable(false);

        // modality
        newWindow.initModality(Modality.APPLICATION_MODAL);

        newWindow.show();
        /*newWindow.setOnCloseRequest(close -> {

                newWindow.close();
            });
        */
    }


    /**
     * returns main stage of ArcadeApp.
     * @return ArcadeApp.mainStage
     */
    public static Stage getMainStage() {
        return ArcadeApp.mainStage;
    } // getMainStage()

    /**
     * returns mancala scene.
     * @return ArcadeApp.mainScene
     */
    public static Scene getMainScene() {
        return ArcadeApp.mainScene;
    } // getMainScene

    /**
     * returns tetris scene.
     * @return ArcadeApp.tScene
     */
    public static Scene getTScene() {
        return ArcadeApp.tScene;
    } // getTScene

} // ArcadeApp
