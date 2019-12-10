package cs1302.arcade;

// import java.lang.Object.ListNode;
import cs1302.arcade.ArcadeApp;
import javafx.scene.control.TextField;
import javafx.application.Platform;
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
import javafx.scene.text.TextAlignment;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.lang.InterruptedException;
import javafx.scene.text.Font;
import java.awt.Color;

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
    private ImageView filler1;
    private ImageView filler2;
    private ImageView filler3;
    private ImageView filler4;
    private ImageView titleIV;
    private GridPane prompt;
    private GridPane players;
    private ImageView[] marblesIV;
    private LinkedList<Integer> gameBoard;
    private int topRow;
    private int botRow;

    private String menuStr = Mancala.class.getResource("/mancala/menu.png").toExternalForm();
    private String controlsStr = Mancala.class.getResource("/mancala/controls.png").toExternalForm();
    private String player1Str = Mancala.class.getResource("/mancala/player1.png").toExternalForm();
        private String player2Str = Mancala.class.getResource("/mancala/player2.png").toExternalForm();
        private String rArrowStr = Mancala.class.getResource("/mancala/rightArrow.png").toExternalForm();
    private String lArrowStr = Mancala.class.getResource("/mancala/leftArrow.png").toExternalForm();


    // variables for menu and controlls
    Image menuImage = new Image(menuStr);
    ImageView menuIV = new ImageView(menuImage);
    Image controlsImage = new Image(controlsStr);
    ImageView controlsIV = new ImageView(controlsImage);

    // variables for player1/2 and left/right arrow
    Image player1Image = new Image(player1Str);
    ImageView player1IV = new ImageView(player1Image);
    Image player2Image = new Image(player2Str);
    ImageView player2IV = new ImageView(player2Image);
    Image rArrowImage = new Image(rArrowStr);
    ImageView rArrowIV = new ImageView(rArrowImage);
    Image lArrowImage = new Image(lArrowStr);
    ImageView lArrowIV = new ImageView(lArrowImage);

    public Mancala() {

        GridPane gpane = new GridPane();
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
        prompt.add(menuIV, 0, 0);
        prompt. add(filler, 1, 0);
        prompt.add(controlsIV, 2, 0);

        // creates gridpane for player turn
        ImageView arrow = lArrowIV;
        filler1 = new ImageView();
        filler1.setFitWidth(50);
        filler1.setFitHeight(200);
        filler2 = new ImageView();
        filler2.setFitWidth(50);
        filler2.setFitHeight(200);
        filler3 = new ImageView();
        filler3.setFitWidth(50);
        filler3.setFitHeight(200);
        filler4 = new ImageView();
        filler4.setFitWidth(50);
        filler4.setFitHeight(200);
        players = new GridPane();
        players.add(filler1, 0, 0);
        players.add(player1IV, 1, 0);
        players.add(filler2, 2, 0);
        players.add(arrow, 3, 0);
        players.add(filler3, 4, 0);
        players.add(player2IV, 5, 0);
        players.add(filler4, 6, 0);

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

        // fills 2x5 gridpane with all values from gameBoard
        // and also sets hover handlers for each IV
        int increaser = 1;
        for (int i = 1; i > 0; i--) {
            for (int j = 0; j < 5; j++) {
                gpane.add(gameIVs.get(increaser), j, i);
                gameIVs.get(increaser).setOnMouseEntered(e -> {
                        root.setCursor(Cursor.HAND);
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

        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // event handler for IVs in all of gpane
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
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

                // check if temp = 11, thus a full circle into same empty pocket
                if (temp == 11) {
                    //add 1, for gameBoard(1), and gameBoard(11) to gameBoard(6) and empties those pockets
                    gameBoard.set(6, gameBoard.get(6) + gameBoard.get(11) + 1);
                    gameBoard.set(11, 0);
                    gameBoard.set(1, 0);
                    gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                    gameIVs.get(1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(1) + ".png").toExternalForm()));
                    gameIVs.get(11).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(11) + ".png").toExternalForm()));
                }
                // checks remaining 4 pockets of row to see if bead landed into an empty pocket
                // and if so empties said pocket and pocket across board into right main pocket
                for (int i = 1; i < 5; i++) {
                    if (temp == i && gameBoard.get(i + 1) == 1) {
                        gameBoard.set(6, gameBoard.get(6) + gameBoard.get(11 - i) + 1);
                        gameBoard.set(11 - i, 0);
                        gameBoard.set(i + 1, 0);
                        gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                        gameIVs.get(11 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(11 - i) + ".png").toExternalForm()));
                        gameIVs.get(i + 1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 1) + ".png").toExternalForm()));
                    } // if
                }

                // checks if a row is empty and ends game
                checkWinner(gameBoard, gameIVs);

                if (temp != 5) {
                    arrow.setImage(new Image(Mancala.class.getResource("/mancala/rightArrow.png").toExternalForm()));
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(false);
                        if (gameBoard.get(i) == 0) {
                            gameIVs.get(i).setDisable(true);
                        } // if
                    } // for
                } // if

                gameIVs.get(1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(1) + ".png").toExternalForm()));
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

                // check if temp = 11, thus a full circle into same empty pocket
                if (temp == 11) {
                    //add 1, for gameBoard(2), and gameBoard(10) to gameBoard(6) and empties those pockets
                    gameBoard.set(6, gameBoard.get(6) + gameBoard.get(10) + 1);
                    gameBoard.set(10, 0);
                    gameBoard.set(2, 0);
                    gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                    gameIVs.get(2).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(2) + ".png").toExternalForm()));
                    gameIVs.get(10).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(10) + ".png").toExternalForm()));
                }
                // checks 3 right  pockets of row to see if bead landed into an empty pocket
                // and if so empties said pocket and pocket across board into right main pocket
                for (int i = 1; i < 4; i++) {
                    if (temp == i && gameBoard.get(i + 2) == 1) {
                        gameBoard.set(6, gameBoard.get(6) + gameBoard.get(10 - i) + 1);
                        gameBoard.set(10 - i, 0);
                        gameBoard.set(i + 2, 0);
                        gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                        gameIVs.get(10 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(10 - i) + ".png").toExternalForm()));
                        gameIVs.get(i + 2).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 2) + ".png").toExternalForm()));
                    } // if
                }
                // performs check on pocket 1
                if (temp == 10 && gameBoard.get(1) == 1) {
                    gameBoard.set(6, gameBoard.get(6) + gameBoard.get(11) + 1);
                    gameBoard.set(11, 0);
                    gameBoard.set(1, 0);
                    gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                    gameIVs.get(1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(1) + ".png").toExternalForm()));
                    gameIVs.get(11).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(11) + ".png").toExternalForm()));
                } // if

                // checks if a row is empty and ends game
                checkWinner(gameBoard, gameIVs);

                if (temp != 4) {
                    arrow.setImage(new Image(Mancala.class.getResource("/mancala/rightArrow.png").toExternalForm()));
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(false);
                        if (gameBoard.get(i) == 0) {
                            gameIVs.get(i).setDisable(true);
                        } // if
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

                // check if temp = 11, thus a full circle into same empty pocket
                if (temp == 11) {
                    //add 1, for gameBoard(3), and gameBoard(9) to gameBoard(6) and empties those pockets
                    gameBoard.set(6, gameBoard.get(6) + gameBoard.get(9) + 1);
                    gameBoard.set(9, 0);
                    gameBoard.set(3, 0);
                    gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                    gameIVs.get(3).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(3) + ".png").toExternalForm()));
                    gameIVs.get(9).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(9) + ".png").toExternalForm()));
                }
                // checks 2 right  pockets of row to see if bead landed into an empty pocket
                // and if so empties said pocket and pocket across board into right main pocket
                for (int i = 1; i < 3; i++) {
                    if (temp == i && gameBoard.get(i + 3) == 1) {
                        gameBoard.set(6, gameBoard.get(6) + gameBoard.get(9 - i) + 1);
                        gameBoard.set(9 - i, 0);
                        gameBoard.set(i + 3, 0);
                        gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                        gameIVs.get(9 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(9 - i) + ".png").toExternalForm()));
                        gameIVs.get(i + 3).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i + 3) + ".png").toExternalForm()));
                    } // if
                }
                // performs check on pockets  1 and 2
                for (int i = 1; i < 3; i++) {
                    if (temp == 8 + i && gameBoard.get(i) == 1) {
                        gameBoard.set(6, gameBoard.get(6) + gameBoard.get(12 - i) + 1);
                        gameBoard.set(12 - i, 0);
                        gameBoard.set(i, 0);
                        gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                        gameIVs.get(i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i) + ".png").toExternalForm()));
                        gameIVs.get(12 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(12 - i) + ".png").toExternalForm()));
                    } // if
                } // for

                // checks if a row is empty and ends game
                checkWinner(gameBoard, gameIVs);

                if (temp != 3) {
                    arrow.setImage(new Image(Mancala.class.getResource("/mancala/rightArrow.png").toExternalForm()));
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(false);
                        if (gameBoard.get(i) == 0) {
                            gameIVs.get(i).setDisable(true);
                        } // if
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

                // check if temp = 11, thus a full circle into same empty pocket
                if (temp == 11) {
                    //add 1, for gameBoard(4), and gameBoard(8) to gameBoard(6) and empties those pockets
                    gameBoard.set(6, gameBoard.get(6) + gameBoard.get(8) + 1);
                    gameBoard.set(8, 0);
                    gameBoard.set(4, 0);
                    gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                    gameIVs.get(4).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(4) + ".png").toExternalForm()));
                    gameIVs.get(8).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(8) + ".png").toExternalForm()));
                }
                // checks 1 right pockets of row to see if bead landed into an empty pocket
                // and if so empties said pocket and pocket across board into right main pocket
                for (int i = 1; i < 2; i++) {
                    if (temp == i && gameBoard.get(i + 4) == 1) {
                        gameBoard.set(6, gameBoard.get(6) + gameBoard.get(7) + 1);
                        gameBoard.set(7, 0);
                        gameBoard.set(5, 0);
                        gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                        gameIVs.get(7).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(7) + ".png").toExternalForm()));
                        gameIVs.get(5).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(5) + ".png").toExternalForm()));
                    } // if
                }
                // performs check on pockets  1 - 3
                for (int i = 1; i < 4; i++) {
                    if (temp == 7 + i && gameBoard.get(i) == 1) {
                        gameBoard.set(6, gameBoard.get(6) + gameBoard.get(12 - i) + 1);
                        gameBoard.set(12 - i, 0);
                        gameBoard.set(i, 0);
                        gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                        gameIVs.get(i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i) + ".png").toExternalForm()));
                        gameIVs.get(12 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(12 - i) + ".png").toExternalForm()));
                    } // if
                } // for

                // checks if a row is empty and ends game
                checkWinner(gameBoard, gameIVs);

                if (temp != 2) {
                    arrow.setImage(new Image(Mancala.class.getResource("/mancala/rightArrow.png").toExternalForm()));
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(false);
                        if (gameBoard.get(i) == 0) {
                            gameIVs.get(i).setDisable(true);
                        } // if
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

                // check if temp = 11, thus a full circle into same empty pocket
                if (temp == 11) {
                    //add 1, for gameBoard(6), and gameBoard(7) to gameBoard(6) and empties those pockets
                    gameBoard.set(6, gameBoard.get(6) + gameBoard.get(7) + 1);
                    gameBoard.set(7, 0);
                    gameBoard.set(5, 0);
                    gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                    gameIVs.get(5).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(5) + ".png").toExternalForm()));
                    gameIVs.get(7).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(7) + ".png").toExternalForm()));
                }
                // performs check on pockets  1 - 4
                for (int i = 1; i < 5; i++) {
                    if (temp == 6 + i && gameBoard.get(i) == 1) {
                        gameBoard.set(6, gameBoard.get(6) + gameBoard.get(12 - i) + 1);
                        gameBoard.set(12 - i, 0);
                        gameBoard.set(i, 0);
                        gameIVs.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6) + ".png").toExternalForm()));
                        gameIVs.get(i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(i) + ".png").toExternalForm()));
                        gameIVs.get(12 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(12 - i) + ".png").toExternalForm()));
                    } // if
                } // for

                // checks if a row is empty and ends game
                checkWinner(gameBoard, gameIVs);

                if (temp != 1) {
                    arrow.setImage(new Image(Mancala.class.getResource("/mancala/rightArrow.png").toExternalForm()));
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(true);
                    } // for
                    for (int i = 7; i < 12; i++) {
                        gameIVs.get(i).setDisable(false);
                        if (gameBoard.get(i) == 0) {
                            gameIVs.get(i).setDisable(true);
                        } // if
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

                // check if temp = 11, thus a full circle into same empty pocket
                if (temp == 11) {
                    //add 1, for gameBoard(7), and gameBoard(5) to gameBoard(0) and empties those pockets
                    gameBoard.set(0, gameBoard.get(0) + gameBoard.get(5) + 1);
                    gameBoard.set(7, 0);
                    gameBoard.set(5, 0);
                    gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                    gameIVs.get(5).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(5) + ".png").toExternalForm()));
                    gameIVs.get(7).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(7) + ".png").toExternalForm()));
                }
                // performs check on pockets  8 - 11
                for (int i = 1; i < 5; i++) {
                    if (temp == i && gameBoard.get(i + 7) == 1) {
                        gameBoard.set(0, gameBoard.get(0) + gameBoard.get(5 - i) + 1);
                        gameBoard.set(5 - i, 0);
                        gameBoard.set(7 + i, 0);
                        gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                        gameIVs.get(5 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(5 - i) + ".png").toExternalForm()));
                        gameIVs.get(7 + i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(7 + i) + ".png").toExternalForm()));
                    } // if
                } // for

                // checks if a row is empty and ends game
                checkWinner(gameBoard, gameIVs);

                if (temp != 5) {
                    arrow.setImage(new Image(Mancala.class.getResource("/mancala/leftArrow.png").toExternalForm()));
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(false);
                        if (gameBoard.get(i) == 0) {
                            gameIVs.get(i).setDisable(true);
                        } // if
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

                // check if temp = 11, thus a full circle into same empty pocket
                if (temp == 11) {
                    //add 1, for gameBoard(8), and gameBoard(4) to gameBoard(0) and empties those pockets
                    gameBoard.set(0, gameBoard.get(0) + gameBoard.get(4) + 1);
                    gameBoard.set(8, 0);
                    gameBoard.set(4, 0);
                    gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                    gameIVs.get(4).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(4) + ".png").toExternalForm()));
                    gameIVs.get(8).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(8) + ".png").toExternalForm()));
                }
                // performs check on pockets  9 - 11
                for (int i = 1; i < 4; i++) {
                    if (temp == i && gameBoard.get(i + 8) == 1) {
                        gameBoard.set(0, gameBoard.get(0) + gameBoard.get(4 - i) + 1);
                        gameBoard.set(4 - i, 0);
                        gameBoard.set(8 + i, 0);
                        gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                        gameIVs.get(4 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(4 - i) + ".png").toExternalForm()));
                        gameIVs.get(8 + i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(8 + i) + ".png").toExternalForm()));
                    } // if
                } // for
                // checks pocket 7
                if (temp == 10 && gameBoard.get(7) == 1) {
                        gameBoard.set(0, gameBoard.get(0) + gameBoard.get(5) + 1);
                        gameBoard.set(5, 0);
                        gameBoard.set(7, 0);
                        gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                        gameIVs.get(5).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(5) + ".png").toExternalForm()));
                        gameIVs.get(7).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(7) + ".png").toExternalForm()));
                } // if

                // checks if a row is empty and ends game
                checkWinner(gameBoard, gameIVs);

                if (temp != 4) {
                    arrow.setImage(new Image(Mancala.class.getResource("/mancala/leftArrow.png").toExternalForm()));
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(false);
                        if (gameBoard.get(i) == 0) {
                            gameIVs.get(i).setDisable(true);
                        } // if
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

                // check if temp = 11, thus a full circle into same empty pocket
                if (temp == 11) {
                    //add 1, for gameBoard(9),  and gameBoard(3) to gameBoard(0) and empties those pockets
                    gameBoard.set(0, gameBoard.get(0) + gameBoard.get(3) + 1);
                    gameBoard.set(9, 0);
                    gameBoard.set(3, 0);
                    gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                    gameIVs.get(3).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(3) + ".png").toExternalForm()));
                    gameIVs.get(9).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(9) + ".png").toExternalForm()));
                }
                // performs check on pockets  10 - 11
                for (int i = 1; i < 3; i++) {
                    if (temp == i && gameBoard.get(i + 9) == 1) {
                        gameBoard.set(0, gameBoard.get(0) + gameBoard.get(3 - i) + 1);
                        gameBoard.set(3 - i, 0);
                        gameBoard.set(9 + i, 0);
                        gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                        gameIVs.get(3 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(3 - i) + ".png").toExternalForm()));
                        gameIVs.get(9 + i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(9 + i) + ".png").toExternalForm()));
                    } // if
                } // for
                // checks pocket 7 - 8
                for (int i = 1; i < 3; i++) {
                    if (temp == 8 + i && gameBoard.get(6 + i) == 1) {
                        gameBoard.set(0, gameBoard.get(0) + gameBoard.get(6 - i) + 1);
                        gameBoard.set(6 - i, 0);
                        gameBoard.set(6 + i, 0);
                        gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                        gameIVs.get(6 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6 - i) + ".png").toExternalForm()));
                        gameIVs.get(6 + i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6 + i) + ".png").toExternalForm()));
                    } // if
                } // for

                // checks if a row is empty and ends game
                checkWinner(gameBoard, gameIVs);

                if (temp != 3) {
                    arrow.setImage(new Image(Mancala.class.getResource("/mancala/leftArrow.png").toExternalForm()));
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(false);
                        if (gameBoard.get(i) == 0) {
                            gameIVs.get(i).setDisable(true);
                        } // if
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

                // check if temp = 11, thus a full circle into same empty pocket
                if (temp == 11) {
                    //add 1, for gameBoard(10),  and gameBoard(2) to gameBoard(0) and empties those pockets
                    gameBoard.set(0, gameBoard.get(0) + gameBoard.get(2) + 1);
                    gameBoard.set(10, 0);
                    gameBoard.set(2, 0);
                    gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                    gameIVs.get(2).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(2) + ".png").toExternalForm()));
                    gameIVs.get(10).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(10) + ".png").toExternalForm()));
                }
                // performs check on pocket 11
                    if (temp == 1 && gameBoard.get(11) == 1) {
                        gameBoard.set(0, gameBoard.get(0) + gameBoard.get(1) + 1);
                        gameBoard.set(1, 0);
                        gameBoard.set(11, 0);
                        gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                        gameIVs.get(1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(1) + ".png").toExternalForm()));
                        gameIVs.get(11).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(11) + ".png").toExternalForm()));
                    } // if
                // checks pocket 7 - 9
                for (int i = 1; i < 4; i++) {
                    if (temp == 7 + i && gameBoard.get(6 + i) == 1) {
                        gameBoard.set(0, gameBoard.get(0) + gameBoard.get(6 - i) + 1);
                        gameBoard.set(6 - i, 0);
                        gameBoard.set(6 + i, 0);
                        gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                        gameIVs.get(6 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6 - i) + ".png").toExternalForm()));
                        gameIVs.get(6 + i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6 + i) + ".png").toExternalForm()));
                    } // if
                } // for

                // checks if a row is empty and ends game
                checkWinner(gameBoard, gameIVs);

                if (temp != 2) {
                    arrow.setImage(new Image(Mancala.class.getResource("/mancala/leftArrow.png").toExternalForm()));
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(false);
                        if (gameBoard.get(i) == 0) {
                            gameIVs.get(i).setDisable(true);
                        } // if
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

                // check if temp = 11, thus a full circle into same empty pocket
                if (temp == 11) {
                    //add 1, for gameBoard(11),  and gameBoard(1) to gameBoard(0) and empties those pockets
                    gameBoard.set(0, gameBoard.get(0) + gameBoard.get(1) + 1);
                    gameBoard.set(11, 0);
                    gameBoard.set(1, 0);
                    gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                    gameIVs.get(1).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(1) + ".png").toExternalForm()));
                    gameIVs.get(11).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(11) + ".png").toExternalForm()));
                }
                // checks pocket 7 - 10
                for (int i = 1; i < 5; i++) {
                    if (temp == 6 + i && gameBoard.get(6 + i) == 1) {
                        gameBoard.set(0, gameBoard.get(0) + gameBoard.get(6 - i) + 1);
                        gameBoard.set(6 - i, 0);
                        gameBoard.set(6 + i, 0);
                        gameIVs.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(0) + ".png").toExternalForm()));
                        gameIVs.get(6 - i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6 - i) + ".png").toExternalForm()));
                        gameIVs.get(6 + i).setImage(new Image(Mancala.class.getResource("/mancala/m" + gameBoard.get(6 + i) + ".png").toExternalForm()));
                    } // if
                } // for

                // checks if a row is empty and ends game
                checkWinner(gameBoard, gameIVs);

                if (temp != 1) {
                    arrow.setImage(new Image(Mancala.class.getResource("/mancala/leftArrow.png").toExternalForm()));
                    for (int i = 1; i < 6; i++) {
                        gameIVs.get(i).setDisable(false);
                        if (gameBoard.get(i) == 0) {
                            gameIVs.get(i).setDisable(true);
                        } // if
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
        controlsIV.setOnMouseClicked(getControlMenu());

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
        root.getChildren().addAll(title, prompt, hbox, players);
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

    /**
     * Returns control menu.
     *
     * @return controlMenu
     */
    public EventHandler<MouseEvent> getControlMenu() {
        EventHandler<MouseEvent> controlMenu = event -> {
            // creates new window stage
            Stage newWindow = new Stage();

            // sets exit text and creates buttons
            Text controls = new Text(
                "Object: \n" +
                "The object of the game is to collect the most \npieces by the end of the game. \n \n" +
                "Controls: \n" +
                "1. The game begins with player one (bottom) picking \nup all of the pieces in any one of the pockets on his/her side. \n" +
                "2. Moving counter-clockwise, the player deposits one \nof the stones in each pocket until the stones run out. \n" +
                "3. If you run into your own Mancala (store), deposit one \npiece in it. If you run into your opponent's Mancala, \nskip it and continue moving to the next pocket. \n" +
                "4. If the last piece you drop is in your own Mancala, \nyou take another turn. \n" +
                "5. If the last piece you drop is in an empty pocket on \nyour side, you capture that piece and any pieces in \nthe pocket directly opposite.");

            controls.setTextAlignment(TextAlignment.LEFT);

            // adds controls to controlBox
            HBox controlBox = new HBox();
            controlBox.getChildren().addAll(controls);

            controlBox.setAlignment(Pos.TOP_CENTER);
            Scene control = new Scene(controlBox, 420, 280);

            // New window of stage
            newWindow.setMaxWidth(420);
            newWindow.setMaxHeight(280);
            newWindow.setMinWidth(420);
            newWindow.setMinHeight(280);

            newWindow.setTitle("Mancala Rule/Controls");
            newWindow.sizeToScene();
            newWindow.setScene(control);
            newWindow.setResizable(false);

            // modality
            newWindow.initModality(Modality.APPLICATION_MODAL);

            newWindow.show();
        }; // controlMenu
        return controlMenu;
    } // getControlMenu()

    /**
     * Returns Winner menu.
     *
     * @return winnerMenu
     */
    public void getWinnerMenu(LinkedList<Integer> x) {
        // creates new window stage
        Stage newWindow = new Stage();

        String winner = "";
        int score = 0;
        if (x.get(0) > x.get(6)) {
            winner = "Player 2";
            score = x.get(0);
        } else if (x.get(0) < x.get(6)) { // if
            winner = "Player 1";
            score = x.get(6);
        } else { // else if
            winner = "TIE";
            score = x.get(0);
        } // else

        // sets winner text and creates buttons
        Text winText = new Text("The Winner is\n" + winner + "\n with "
                                + score + " points!!!");
        winText.setTextAlignment(TextAlignment.CENTER);
        Button save = new Button("Save");
        Button menu = new Button("Return to Main Menu");
        TextField winnerName = new TextField("Enter Winner's Name");

        // adds text buttons to hboxes
        HBox winBox = new HBox();
        winBox.getChildren().addAll(winText);
        winBox.setAlignment(Pos.CENTER);
        winBox.setPadding(new Insets(10, 0, 0, 0));
        HBox saveName = new HBox();
        saveName.setSpacing(10);
        saveName.setPadding(new Insets(20, 5, 5, 5));
        saveName.getChildren().addAll(winnerName, save, menu);
        saveName.setAlignment(Pos.CENTER);

        // puts text and hbox into a vbox
        VBox vBox = new VBox();
        vBox.getChildren().addAll(winBox, saveName);
        vBox.setAlignment(Pos.TOP_CENTER);
        Scene exits = new Scene(vBox, 450, 120);

        // event handlers for buttons
        EventHandler<ActionEvent> saveHandler = event2 -> {
            //newWindow.close();
        };
        EventHandler<ActionEvent> menuHandler = event3 -> {
            Stage s = ArcadeApp.getMainStage();
            Scene sc = ArcadeApp.getMainScene();
            s.setScene(sc);
            newWindow.close();
        };

        // set hanlers to buttons
        save.setOnAction(saveHandler);
        menu.setOnAction(menuHandler);

        // New window of stage
        newWindow.setMaxWidth(450);
        newWindow.setMaxHeight(120);
        newWindow.setMinWidth(450);
        newWindow.setMinHeight(120);

        newWindow.setTitle("Winner");
        newWindow.sizeToScene();
        newWindow.setScene(exits);
        newWindow.setResizable(false);

        // modality
        newWindow.initModality(Modality.APPLICATION_MODAL);

        newWindow.show();

    } // getWinnerMenu()

    /**
     * Returns Winner menu.
     *
     * @return winnerMenu
     */
    public void checkWinner(LinkedList<Integer> x, LinkedList<ImageView> y) {
        // top and bot row sums
        botRow = x.get(1) + x.get(2) + x.get(3) +
            x.get(4) + x.get(5);
        topRow = x.get(7) + x.get(8) + x.get(9) +
            x.get(10) + x.get(11);

        // if botRow is empty move all of top row to left mancala
        // and display winner
        if (botRow == 0) {
            for (int i = 7; i < 12; i++) {
                x.set(0, x.get(0) + x.get(i));
                x.set(i, 0);
                y.get(i).setImage(new Image(Mancala.class.getResource("/mancala/m" + x.get(i) + ".png").toExternalForm()));
            } // for
            y.get(0).setImage(new Image(Mancala.class.getResource("/mancala/m" + x.get(0) + ".png").toExternalForm()));
            getWinnerMenu(x);
        }
        // if topRow is empty move all of top row to right mancala
        // and display winner
        if (topRow == 0) {
            for (int i = 1; i < 6; i++) {
                x.set(6, x.get(6) + x.get(i));
                x.set(i, 0);
                y.get(i).setImage(new Image(Mancala.class.getResource("/mancala/m" + x.get(i) + ".png").toExternalForm()));
            } // for
            y.get(6).setImage(new Image(Mancala.class.getResource("/mancala/m" + x.get(6) + ".png").toExternalForm()));
            getWinnerMenu(x);
        }
    } // checkWinner
} // Mancala
