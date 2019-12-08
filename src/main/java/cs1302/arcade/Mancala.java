package cs1302.arcade;

// import java.lang.Object.ListNode;
import cs1302.arcade.ArcadeApp;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.Cursor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.lang.InterruptedException;

// TO DO:
// set up grid pane/IVs for board
// set child under titleIV, holding IVs with menu and control

public class Mancala {

    private VBox root;
    private HBox hbox;
    private HBox title;
    private HBox lBoard;
    private HBox rBoard;
    private String BG = Mancala.class.getResource("/mancala/mancalaBG.png").toExternalForm();
    private String titleStr = Mancala.class.getResource("/mancala/mancalaTitle.png").toExternalForm();
    private ImageView filler;
    private ImageView titleIV;
    private GridPane prompt;
    private ImageView[] marblesIV;

    private String menuStr = Mancala.class.getResource("/mancala/menu.png").toExternalForm();
    private String controlsStr = Mancala.class.getResource("/mancala/controls.png").toExternalForm();

    // variables for menu and controlls
    Image menuImage = new Image(menuStr);
    ImageView menuIV = new ImageView(menuImage);
    Image controlsImage = new Image(controlsStr);
    ImageView controlsIV = new ImageView(controlsImage);

    public Mancala() {

        GridPane gpane = new GridPane();
        //gpane.setGridLinesVisible(true);
        root = new VBox();
        hbox = new HBox();
        title = new HBox();

        // creates title IV
        Image titleImage = new Image(titleStr);
        titleIV = new ImageView(titleImage);

        // creates gridpane of menu and controls
        filler = new ImageView();
        filler.setFitWidth(500);
        filler.setFitHeight(100);
        prompt = new GridPane();
        //prompt.setGridLinesVisible(true);
        prompt.add(menuIV, 0, 0);
        prompt. add(filler, 1, 0);
        prompt.add(controlsIV, 2, 0);

        // =-=-=-=-=- linked list reprsenting board =-=-=-=-=
        LinkedList<Integer> gameBoard = new LinkedList<>();

        gameBoard.add(0); // position 0 is left big pocket
        gameBoard.add(3); // positions 1 - 5 are bottom row
        gameBoard.add(3);
        gameBoard.add(3);
        gameBoard.add(3);
        gameBoard.add(3);
        gameBoard.add(0); // position 6 is right big pocket
        gameBoard.add(3); // positions 7 - 11 are top row
        gameBoard.add(3);
        gameBoard.add(3);
        gameBoard.add(3);
        gameBoard.add(3);

        // =-=-=-=-=- IV list that relates to gameBoard -=-=-=-=
        LinkedList<ImageView> gameIVs = new LinkedList<>();

        gameIVs.add(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm())));
        gameIVs.add(setSizeIV(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(1) + ".png").toExternalForm()))));
        gameIVs.add(setSizeIV(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(2) + ".png").toExternalForm()))));
        gameIVs.add(setSizeIV(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(3) + ".png").toExternalForm()))));
        gameIVs.add(setSizeIV(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(4) + ".png").toExternalForm()))));
        gameIVs.add(setSizeIV(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(5) + ".png").toExternalForm()))));
        gameIVs.add(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm())));
        gameIVs.add(setSizeIV(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(7) + ".png").toExternalForm()))));
        gameIVs.add(setSizeIV(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(8) + ".png").toExternalForm()))));
        gameIVs.add(setSizeIV(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(9) + ".png").toExternalForm()))));
        gameIVs.add(setSizeIV(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(10) + ".png").toExternalForm()))));
        gameIVs.add(setSizeIV(new ImageView(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(11) + ".png").toExternalForm()))));

        // left and right spaces of board
        lBoard = new HBox();
        rBoard = new HBox();
        lBoard.setMinWidth(150);
        lBoard.setMaxWidth(150);
        lBoard.setMinHeight(220);
        rBoard.setMinWidth(150);
        rBoard.setMaxWidth(150);
        rBoard.setMinHeight(220);
        lBoard.getChildren().addAll(gameIVs.get(0));
        rBoard.getChildren().addAll(gameIVs.get(6));
        lBoard.setPadding(new Insets(0, 0, 0, 25));
        rBoard.setPadding(new Insets(0, 0, 0, -25));

        // crates list containing images for each amount of beads in game
        //List<Image> ims = new ArrayList<>();
        //for (int i = 0; i < 11; i++) {
        //    ims.add(new Image(Mancala.class.getResource();
        //} // for

        // fills 2x5 gridpane with all values from gameBoard
        int increaser = 1;
        for (int i = 1; i > 0; i--) {
            for (int j = 0; j < 5; j++) {
                gpane.add(gameIVs.get(increaser), j, i);
                gameIVs.get(increaser).setOnMouseEntered(e -> {
                        //if (gameBoard.get(increaser) != 0) {
                        root.setCursor(Cursor.HAND);
                        //} // if
                    });
                gameIVs.get(increaser).setOnMouseExited(e -> {
                        root.setCursor(Cursor.DEFAULT);
                    });

                increaser++;
                if (increaser == 6) {
                    increaser++;
                } // if
            } // for
        } // for
        for (int i = 0; i > -1; i--) {
            for (int j = 4; j > -1; j--) {
                gpane.add(gameIVs.get(increaser), j, i);
                gameIVs.get(increaser).setOnMouseEntered(e -> {
                        root.setCursor(Cursor.HAND);
                    });
                gameIVs.get(increaser).setOnMouseExited(e -> {
                        root.setCursor(Cursor.DEFAULT);
                    });

                increaser++;
            } // for
        } // for
        hbox.getChildren().addAll(lBoard, gpane, rBoard);

        // event handler for IVs in all of gpane
        /**
           } // for
           try {
           Thread.sleep(1000);
           } catch(InterruptedException ex) {
           // nothing
           }
        */

        gameIVs.get(1).setOnMouseClicked(e -> {
                int temp = gameBoard.get(1);
                gameBoard.set(1, 0);
                for (int i = 0; i < temp; i++) {
                    if (i > 9) {
                        gameBoard.set(i - 9, gameBoard.get(i - 9) + 1);
                        gameIVs.get(i - 9).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 9) + ".png").toExternalForm()));
                    } else { // if
                        gameBoard.set(i + 2, gameBoard.get(i + 2) + 1);
                        gameIVs.get(i + 2).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 2) + ".png").toExternalForm()));
                    } // else
                } // for

                // if (gameBoard.get(1) < 5 &&
                // gameBoard.get(1 + gameBoards.get(1)) == 1)
                // if ((1 + gameBoard.get(1) == 5)
                // take out marbles from 7 and update 7 image
                // if == 4 ...
                // finally
                // add 1 to gameBoard.get(6)
                // set gameBoard.get(1 + gameBoard.get(1)) to 0
                // update that locations image
                //

                if (temp != 5) {
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(false);
                    } // for
                    // update turn arrow
                    // =-=-=-=-=-=-=-=-=-=-=-
                    // HERE
                    // =-=-=-=-=-=-=-=-=-=-=-
                } // if

                gameIVs.get(1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(1) + ".png").toExternalForm()));

                //check if (all on clickside are empty), then  move remaining
                //from other side to other main pocket
                //also create runnable which does getWinnerHandler
                //which sees if gameBoard 6 > 0 then displays player 1 wins
                //otherwise says player 2 wins
                //sets highscore to whichever player wons score
                //and has insert player name with text field
                //and save your highscore Y/N buttons
                //which writes it ti text if score isn't empty
            });

        gameIVs.get(2).setOnMouseClicked(e -> {
                int temp = gameBoard.get(2);
                gameBoard.set(2, 0);
                for (int i = 0; i < temp; i++) {
                    if (i > 8) {
                        gameBoard.set(i - 8, gameBoard.get(i - 8) + 1);
                        gameIVs.get(i - 8).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 8) + ".png").toExternalForm()));
                    } else { // if
                        gameBoard.set(i + 3, gameBoard.get(i + 3) + 1);
                        gameIVs.get(i + 3).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 3) + ".png").toExternalForm()));
                    } // else
                } // for

                if (temp != 4) {
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(false);
                    } // for
                } // if
                gameIVs.get(2).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(2) + ".png").toExternalForm()));
            });

        gameIVs.get(3).setOnMouseClicked(e -> {
                int temp = gameBoard.get(3);
                gameBoard.set(3, 0);
                for (int i = 0; i < temp; i++) {
                    if (i > 7) {
                        gameBoard.set(i - 7, gameBoard.get(i - 7) + 1);
                        gameIVs.get(i - 7).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 7) + ".png").toExternalForm()));
                    } else { // if
                        gameBoard.set(i + 4, gameBoard.get(i + 4) + 1);
                        gameIVs.get(i + 4).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 4) + ".png").toExternalForm()));
                    } // else
                } // for

                if (temp != 3) {
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(false);
                    } // for
                } // if
                gameIVs.get(3).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(3) + ".png").toExternalForm()));
            });

        gameIVs.get(4).setOnMouseClicked(e -> {
                int temp = gameBoard.get(4);
                gameBoard.set(4, 0);
                for (int i = 0; i < temp; i++) {
                    if (i > 6) {
                        gameBoard.set(i - 6, gameBoard.get(i - 6) + 1);
                        gameIVs.get(i - 6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 6) + ".png").toExternalForm()));
                    } else { // if
                        gameBoard.set(i + 5, gameBoard.get(i + 5) + 1);
                        gameIVs.get(i + 5).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 5) + ".png").toExternalForm()));
                    } // else
                } // for

                if (temp != 2) {
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(false);
                    } // for
                } // if
                gameIVs.get(4).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(4) + ".png").toExternalForm()));
            });

        gameIVs.get(5).setOnMouseClicked(e -> {
                int temp = gameBoard.get(5);
                gameBoard.set(5, 0);
                for (int i = 0; i < temp; i++) {
                    if (i > 5) {
                        gameBoard.set(i - 5, gameBoard.get(i - 5) + 1);
                        gameIVs.get(i - 5).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 5) + ".png").toExternalForm()));
                    } else { // if
                        gameBoard.set(i + 6, gameBoard.get(i + 6) + 1);
                        gameIVs.get(i + 6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 6) + ".png").toExternalForm()));
                    } // else
                } // for

                if (temp != 1) {
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(false);
                    } // for
                } // if
                gameIVs.get(5).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(5) + ".png").toExternalForm()));
            });

        gameIVs.get(7).setOnMouseClicked(e -> {
                int temp = gameBoard.get(7);
                gameBoard.set(7, 0);
                for (int i = 0; i < temp; i++) {
                    if (i > 3) {
                        if (i > 9) {
                            gameBoard.set(i - 3, gameBoard.get(i - 3) + 1);
                            gameIVs.get(i - 3).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 3) + ".png").toExternalForm()));
                        } else { // if
                            gameBoard.set(i - 4, gameBoard.get(i - 4) + 1);
                            gameIVs.get(i - 4).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 4) + ".png").toExternalForm()));
                        } // else
                    } else { // if
                        gameBoard.set(i + 8, gameBoard.get(i + 8) + 1);
                        gameIVs.get(i + 8).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 8) + ".png").toExternalForm()));
                    } // else
                } // for

                if (temp != 5) {
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(false);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                } // if
                gameIVs.get(7).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(7) + ".png").toExternalForm()));
            });

        gameIVs.get(8).setOnMouseClicked(e -> {
                int temp = gameBoard.get(8);
                gameBoard.set(8, 0);
                for (int i = 0; i < temp; i++) {
                    if (i > 2) {
                        if (i > 8) {
                            gameBoard.set(i - 2, gameBoard.get(i - 2) + 1);
                            gameIVs.get(i - 2).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 2) + ".png").toExternalForm()));
                        } else { // if
                            gameBoard.set(i - 3, gameBoard.get(i - 3) + 1);
                            gameIVs.get(i - 3).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 3) + ".png").toExternalForm()));
                        } // else
                    } else { // if
                        gameBoard.set(i + 9, gameBoard.get(i + 9) + 1);
                        gameIVs.get(i + 9).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 9) + ".png").toExternalForm()));
                    } // else
                } // for
                if (temp != 4) {
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(false);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                } // if
                gameIVs.get(8).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(8) + ".png").toExternalForm()));
            });

        gameIVs.get(9).setOnMouseClicked(e -> {
                int temp = gameBoard.get(9);
                gameBoard.set(9, 0);
                for (int i = 0; i < temp; i++) {
                    if (i > 1) {
                        if (i > 7) {
                            gameBoard.set(i - 1, gameBoard.get(i - 1) + 1);
                            gameIVs.get(i - 1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 1) + ".png").toExternalForm()));
                        } else { // if
                            gameBoard.set(i - 2, gameBoard.get(i - 2) + 1);
                            gameIVs.get(i - 2).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 2) + ".png").toExternalForm()));
                        } // if
                    } else { // if
                        gameBoard.set(i + 10, gameBoard.get(i + 10) + 1);
                        gameIVs.get(i + 10).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 10) + ".png").toExternalForm()));
                    } // else
                } // for

                if (temp != 3) {
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(false);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                } // if
                gameIVs.get(9).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(9) + ".png").toExternalForm()));
            });

        gameIVs.get(10).setOnMouseClicked(e -> {
                int temp = gameBoard.get(10);
                gameBoard.set(10, 0);
                for (int i = 0; i < temp; i++) {
                    if (i > 0) {
                        if (i > 6) {
                            gameBoard.set(i, gameBoard.get(i) + 1);
                            gameIVs.get(i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i) + ".png").toExternalForm()));
                        } else { // if
                            gameBoard.set(i - 1, gameBoard.get(i - 1) + 1);
                            gameIVs.get(i - 1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 1) + ".png").toExternalForm()));
                        } // else
                    } else { // if
                        gameBoard.set(i + 11, gameBoard.get(i + 11) + 1);
                        gameIVs.get(i + 11).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 11) + ".png").toExternalForm()));
                    } // else
                } // for

                if (temp != 2) {
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(false);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                } // if
                gameIVs.get(10).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(10) + ".png").toExternalForm()));
            });

        gameIVs.get(11).setOnMouseClicked(e -> {
                int temp = gameBoard.get(11);
                gameBoard.set(11, 0);
                for (int i = 0; i < temp; i++) {
                    if (i > 5) {
                        if (i > 10) {
                            gameBoard.set(i - 11, gameBoard.get(i - 11) + 1);
                            gameIVs.get(i - 11).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i - 11) + ".png").toExternalForm()));
                        } else { //if
                            gameBoard.set(i + 1, gameBoard.get(i + 1) + 1);
                            gameIVs.get(i + 1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 1) + ".png").toExternalForm()));
                        } // else
                    } else { // if
                        gameBoard.set(i, gameBoard.get(i) + 1);
                        gameIVs.get(i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i) + ".png").toExternalForm()));
                    } // else
                } // for

                if (temp != 1) {
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(false);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                } // if
                gameIVs.get(11).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(11) + ".png").toExternalForm()));
            });

        for (int i = 7; i < 12; i++) {
            gameIVs.get(i).setDisable(true);
        } // for

        // event handlers for menu and controls
        menuIV.setOnMouseEntered(e -> {
                root.setCursor(Cursor.HAND);
            });
        menuIV.setOnMouseExited(e -> {
                root.setCursor(Cursor.DEFAULT);
            });
        menuIV.setOnMouseClicked(getExitMenu());

        controlsIV.setOnMouseEntered(e -> {
                root.setCursor(Cursor.HAND);
            });
        controlsIV.setOnMouseExited(e -> {
                root.setCursor(Cursor.DEFAULT);
            });
        controlsIV.setOnMouseClicked(e -> {
                gameBoard.set(0, gameBoard.get(0) + 1);
                gameBoard.set(1, gameBoard.get(1) + 1);
                gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                gameIVs.get(1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(1) + ".png").toExternalForm()));
            });

    }

    //it'd probably be better if this took in an array of imageviews
    /*private void instIV() {

      }*/
    private ImageView setSizeIV(ImageView iv) {
        iv.setFitHeight(110);
        iv.setFitWidth(77);
        return iv;
    } // setSizeIV
    /*    private ImageView[] createIV() {
          ImageView[] out;

          return out;
          }*/

    public VBox getRoot() {
        String styleBG = "-fx-background-image: url(\'" + BG + "\')";
        Insets titlePad = new Insets(50.0, 0.0, 0.0, 00.0);

        title.getChildren().addAll(titleIV);
        root.getChildren().addAll(title, prompt, hbox);
        root.setMargin(title, titlePad);
        title.setAlignment(Pos.CENTER);
        root.setStyle(styleBG);
        return root;
    }

    /**
     * Returns exit menu.
     *
     * @return exitMenu
     */
    public EventHandler<MouseEvent> getExitMenu() {
        EventHandler<MouseEvent> exitMenu = event -> {
            // creates new window stage
            Stage newWindow = new Stage();

            // sets exit text and creates buttons
            Text exit = new Text("Are you sure you wish to quit Mancala?");
            Button yes = new Button("Yes");
            Button no = new Button("No");

            // adds text buttons to hboxes
            HBox exitBox = new HBox();
            exitBox.getChildren().addAll(exit);
            exitBox.setAlignment(Pos.CENTER);
            exitBox.setPadding(new Insets(10, 0, 0, 0));
            HBox yesNo = new HBox();
            yesNo.setSpacing(263);
            yesNo.setPadding(new Insets(20, 5, 5, 5));
            yesNo.getChildren().addAll(yes, no);

            // puts text and hbox into a vbox
            VBox vBox = new VBox();
            vBox.getChildren().addAll(exitBox, yesNo);
            vBox.setAlignment(Pos.TOP_CENTER);
            Scene exits = new Scene(vBox, 350, 80);

            // event handlers for buttons
            EventHandler<ActionEvent> noHandler = event2 -> {
                newWindow.close();
            };
            EventHandler<ActionEvent> yesHandler = event3 -> {
                Stage s = ArcadeApp.getMainStage();
                Scene sc = ArcadeApp.getMainScene();
                s.setScene(sc);
                newWindow.close();
            };

            // set hanlers to buttons
            no.setOnAction(noHandler);
            yes.setOnAction(yesHandler);

            // New window of stage
            newWindow.setMaxWidth(350);
            newWindow.setMaxHeight(80);
            newWindow.setMinWidth(350);
            newWindow.setMinHeight(80);

            newWindow.setTitle("Exit Mancala");
            newWindow.sizeToScene();
            newWindow.setScene(exits);
            newWindow.setResizable(false);

            // modality
            newWindow.initModality(Modality.APPLICATION_MODAL);

            newWindow.show();
        }; // exitMenu
        return exitMenu;
    } // getExitMenu()

}
