package cs1302.arcade;

import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import javafx.scene.text.TextAlignment;
import javafx.scene.input.MouseEvent;
import javafx.scene.Cursor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.lang.IllegalArgumentException;
import java.lang.ArrayIndexOutOfBoundsException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.scene.input.KeyEvent;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class which initiates the JavaFx game, Tetris.
 * The class uses Block to create and control the pieces of the game.
 * The board contains a space where Blocks are controled and a 2d boolean array
 * which keeps track of where the blocks are set.
 */
public class Tetris {

    int size = 50; // size of each rectangle of the blocks
    int gridsizeX = size * 9;
    int gridsizeY = size * 14;
    boolean[][] grid = new boolean[9][15];
    private static int score;
    private int level;
    private Integer[] checkRows; // follow ints are used for row deletion
    private int deletedRows;
    private int highestRow = 0;
    private static int turns; // essential when configuring the rotation of the first block
    private static Block currentBlock;
    private static Block nextBlock;
    private static long levelTime;
    private Text scoreTxt;
    private Text levelTxt;
    private VBox root;
    private Pane pane;
    private Stage stage;
    private Scene mainScene = ArcadeApp.getMainScene();
    private Scene tetScene = ArcadeApp.getTScene();
    private Timer gravity;
    //private static Block[] blockArr;
    //private static Image nextImage;
    //private static ImageView nextIV;

    // strings with image locations
    private String bG = "file:resources/tetris/tetrisBG.png";
    private String menuStr = "file:resources/tetris/tetMenu.png";
    private String controlsStr = "file:resources/tetris/tetControls.png";
    private String iStr = "file:resources/tetris/blockI.png";
    private String jStr = "file:resources/tetris/blockJ.png";
    private String lStr = "file:resources/tetris/blockL.png";
    private String oStr = "file:resources/tetris/blockO.png";
    private String sStr = "file:resources/tetris/blockS.png";
    private String tStr = "file:resources/tetris/blockT.png";
    private String zStr = "file:resources/tetris/blockZ.png";

    /**
     * Constructor of Tetris. Creates the roots and sets the stage and scene.
     * Creates the 2d boolean grid, and by default the 2d array is filled with false values.
     * The constructor also spawns the first block and calls the TimerTask method to
     * start animation.
     */
    public Tetris() {
        root = new VBox();
        pane = new Pane();
        stage = ArcadeApp.getMainStage();
        tetScene = new Scene(getRoot(), 700, 700);
        stage.setScene(tetScene);

        // fills the grid with default false values
        for (int i = 0; i < grid.length; i++) {
            for ( int j = 0; j < grid[i].length; j++) {
                grid[i][j] = false;
            } // for j
        } // for i

        score = 0;

        // variables for menu and controls
        Image menuImage = new Image(menuStr);
        ImageView menuIV = new ImageView(menuImage);
        Image controlsImage = new Image(controlsStr);
        ImageView controlsIV = new ImageView(controlsImage);
        currentBlock = new Block(randomBlockType());

        menuIV.setX(490);
        menuIV.setY(280);
        menuIV.setOnMouseEntered(e -> {
            tetScene.setCursor(Cursor.HAND);
        });
        menuIV.setOnMouseExited(e -> {
            tetScene.setCursor(Cursor.DEFAULT);
        });
        menuIV.setOnMouseClicked(getExitMenu());
        controlsIV.setX(490);
        controlsIV.setY(350);
        controlsIV.setOnMouseEntered(e -> {
            tetScene.setCursor(Cursor.HAND);
        });
        controlsIV.setOnMouseExited(e -> {
            tetScene.setCursor(Cursor.DEFAULT);
        });
        controlsIV.setOnMouseClicked(getControlMenu());

        scoreTxt = new Text("Score: 0");
        scoreTxt.setStyle("-fx-font-size: 22px; -fx-fill: white;");
        scoreTxt.setY(150);
        scoreTxt.setX(500);
        levelTxt = new Text("Level: 0");
        levelTxt.setStyle("-fx-fill: white;");
        levelTxt.setY(200);
        levelTxt.setX(500);
        pane.getChildren().addAll(menuIV, controlsIV, scoreTxt, levelTxt);

        //currentBlock = new Block("Z"); // test code for specific block
        pane.getChildren().addAll(currentBlock.r1, currentBlock.r2,
                                  currentBlock.r3, currentBlock.r4);
        turns = 0;
        gravity = new Timer();
        startGravity(gravity, currentBlock, scoreTxt, levelTxt, 500);

    } // Tetris()

    /**
     * writes player's input name to tetScores.txt.
     *
     * @param name the player inputed name
     */
    public void tetrisSaveScore(String name) {
        try {
            File file = new File("tetScores.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
            output.newLine();
            output.append(score + ":" + name);
            output.close();
        } catch (IOException e) {
            System.out.print("");
        }
    } // tetrisSaveScore

/**
     * Returns control menu.
     *
     * @return controlMenu
     */
    public EventHandler<MouseEvent> getControlMenu() {
        EventHandler<MouseEvent> controlMenu = event -> {
            // creates new window stage
            gravity.cancel();
            Stage newWindow = new Stage();

            // sets exit text and creates buttons
            Text controls = new Text(
                "Object: \n" +
                "The object of the game is to fill all empty space" +
                " \nwithin a row. Your score will increment up" +
                " \nas you fill a row. Once a row is filled" +
                " \nthe row will clear and the blocks above it will fall." +
                " \nEvery 3 rows you fill you will move on to the next level." +
                " \nEach level will increase the speed of the following blocks. \n \n" +
                "Controls: \n" +
                "1. You may use WASD or Arrow Keys to move the falling block. \n" +
                "- W/Up : Rotates the block \n" +
                "- A/Left : Moves block to the left \n" +
                "- S/Down : Moves the block down \n" +
                "- D/Right : Moves the block to the right");

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

            newWindow.setTitle("Tetris Rule/Controls");
            newWindow.sizeToScene();
            newWindow.setScene(control);
            newWindow.setResizable(false);

            // modality
            newWindow.initModality(Modality.APPLICATION_MODAL);

            newWindow.show();
            newWindow.setOnCloseRequest(close -> {
                Tetris tet = new Tetris();
                newWindow.close();
            });
        }; // controlMenu
        return controlMenu;
    } // getControlMenu()


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
            Text exit = new Text("Are you sure you wish to quit Tetris?");
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
                gravity.cancel();
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
     * Method which gets Tetris root. Used by main menu screen in scene controlling.
     * @return VBox the root of the tetris scene.
     */
    public VBox getRoot() {
        String styleBG = "-fx-background-image: url(\'" + bG + "\')";

        root.getChildren().addAll(pane);

        root.getChildren();
        root.setStyle(styleBG);
        return root;
    } // getRoot()

    /**
     * Using the random class, a random int from 0-69 is selected
     * and returns a randomBlockType determined by the random int.
     * @return String value of a block type
     */
    private static String randomBlockType() {
        Random rand = new Random();
        int n = rand.nextInt(70); //random int between 0-69
        if (n < 10) {
            return "O";
        } else if (n < 20 && n >= 10) {
            return "I";
        } else if (n < 30 && n >= 20) {
            return "J";
        } else if (n < 40 && n >= 30) {
            return "L";
        } else if (n < 50 && n >= 40) {
            return "Z";
        } else if (n < 60 && n >= 50) {
            return "S";
        } else if (n < 70 && n >= 60) {
            return "T";
        }
        return "I";
    } // randomBlockType()

    /**
     * Handles the Timer and TimerTask that runs the game.
     * @param gravity Timer which schedules the TimerTask
     * @param block the initial block of the game
     * @param scoreTxt text that displays score
     * @param levelTxt text that displays level
     * @param timePeriod controls the speed at which the blocks fall
     */
    private void startGravity(Timer gravity, Block block,
                              Text scoreTxt, Text levelTxt, long timePeriod) {
        Runnable r = () -> {

            if (isGameOver() == true) {
                gravity.cancel();
                getGameOver();
            } else {
                /*if (turns == 0) {
                    blockArr = new Block[2];
                    blockArr[0] = block;
                    blockArr[1] = new Block(randomBlockType());
                } else { // if odd
                    blockArr[0] = blockArr[1];
                    blockArr[1] = new Block(randomBlockType());
                    }*/

                Block newBlock = block;
                //Block newBlock = new Block(blockArr[0].getType());
                //nextIV.setImage(displayNext(blockArr[1]));

                // if block is at the bottom, or the block has collided with another block, set
                // the block into the grid and spawn a new block.
                if (isBlockAtBottom(newBlock) == true || blockCollided(newBlock) == true) {
                    setBlockInGrid(newBlock);

                    if (score != 0) {
                        scoreTxt.setText("Score: " + score);
                        level = score / 150;
                        if (level > 0) {
                            levelTxt.setText("Level: " + level);
                            increaseSpeed();
                        }
                    } // if score is not 0

                    nextBlock = new Block(randomBlockType());
                    //nextBlock = new Block(blockArr[1].getType());
                    //nextBlock = new Block("S"); // test code for specific blocks
                    newBlock = nextBlock;
                    // method to display next block IV here
                    pane.getChildren().addAll(newBlock.r1, newBlock.r2, newBlock.r3, newBlock.r4);
                    turns = 1;
                } // if at bottom or collided

                // if the specified block does not collide with another one,
                // it will keep moving down
                if (blockCollided(newBlock) == false) {
                    newBlock.moveDown();
                    playerInput(newBlock);
                }

            } // if not gameover

        }; // Runnable r
        gravity.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(r);
                } // run()
            }, 0, timePeriod); // end of timer.schedule(timertask, 0, 300);
    } // startGravity

    /**
     * Takes in a block and return image to display.
     * @param block the type of block to display
     * @return output the image to be displayed
     */
    private Image displayNext(Block block) {
        System.out.println("in displayN");
        String imageType = block.getType();
        Image output = new Image(iStr);
        switch (imageType) {
        case "I":
            output = new Image(iStr);
            break;
        case "J":
            output = new Image(jStr);
            break;
        case "L":
            output = new Image(lStr);
            break;
        case "S":
            output = new Image(sStr);
            break;
        case "Z":
            output = new Image(zStr);
            break;
        case "T":
            output = new Image(tStr);
            break;
        case "O":
            output = new Image(oStr);
            break;
        } // switch-case
        return output;
    }

    /**
     * Stops the current Timer and creates a new one with an adjusted speed.
     * The speed decreases in increments of 75 depending on the level.
     */
    private void increaseSpeed() {
        gravity.cancel();
        gravity = new Timer();
        startGravity(gravity, currentBlock, scoreTxt, levelTxt, 500 - (75 * level));
    }

    /**
     * Checks the specified row if it needs to be deleted. If true, the score is
     * incremented along with a level update. For every rectangle node in the pane
     * at the specified coordinate of the row, the initial grid value is set
     * to false and the rectangle nodes are deleted.
     * @return true if the row was deleted, false if row did not need to be deleted
     * @param row the specified row to be deleted
     */
    private boolean rowDeletion(int row) {
        boolean isFull = true;
        long tPeriod = 500;
        ArrayList<Node> squaresToDelete = new ArrayList<Node>();
        // loop through row to check if it is full
        for (int i = 0; i < 9; i++) {
            if (grid[i][row] == false) { // if there is a blank in the row
                isFull = false;
                score = score;
            }
        } // loop through row

        if (isFull == true) {
            score += 50;

            level = score / 150;
            if (level > 0) {
                levelTime = tPeriod + (150 * level);

            }

            Rectangle r = new Rectangle();
            for (Node n : pane.getChildren()) {
                if (n.getClass().equals(Rectangle.class)) {
                    squaresToDelete.add(n);
                }
            } // put nodes in arraylist

            for (Node n : squaresToDelete) {
                r = (Rectangle) n; // could not find alternative to casting
                // if the Y coord of the rect matches Y coord of the full row
                if (r.getY() == row * 50) {
                    // set grid to false i.e empty
                    // and remove the rect from the pane
                    Double rx = r.getX();
                    Double ry = r.getY();
                    grid[rx.intValue() / 50][ry.intValue() / 50] = false;
                    pane.getChildren().remove(r);
                }
            } // for each node in list
        } // if row is full
        return isFull;
    } // rowDeletion


    /**
     * Method to be used to bring down the row above the deleted row(s).
     * @param row the specified row that was just deleted.
     */
    private void dropRow(int row) {
        Rectangle r = new Rectangle();
        for (Node n : pane.getChildren()) {
            if (n.getClass().equals(Rectangle.class)) {
                r = (Rectangle) n; // could not find alt to casting
            } // if the node is a rectangle

            // if rect is in specified row
            if (r.getY() == row * 50) {
                Double rx = r.getX();
                Double ry = r.getY();
                Double ryUpdated = r.getY() + (50 * deletedRows);

                grid[rx.intValue() / 50][ry.intValue() / 50] = false;
                // after clearing the grid on the specified row,
                // set the rectangle in the new row
                r.setY(ryUpdated);
                // update the grid
                grid[rx.intValue() / 50][ryUpdated.intValue() / 50] = true;
            }
        } // for each node in pane
    } // dropRow(int)

    /**
     * This method places the block's position within the boolean grid.
     * A square on the grid is true when a square occupies the space on the grid.
     * @param block the block which is being set to the boolean 2d grid
     */
    private void setBlockInGrid(Block block) {
        Double r1x = block.r1.getX();
        Double r1y = block.r1.getY();
        Double r2x = block.r2.getX();
        Double r2y = block.r2.getY();
        Double r3x = block.r3.getX();
        Double r3y = block.r3.getY();
        Double r4x = block.r4.getX();
        Double r4y = block.r4.getY();
        try {
            checkRows = new Integer[] {r1y.intValue() / 50, r2y.intValue() / 50,
                                       r3y.intValue() / 50, r4y.intValue() / 50};

            grid[r1x.intValue() / 50][checkRows[0]] = true;
            grid[r2x.intValue() / 50][checkRows[1]] = true;
            grid[r3x.intValue() / 50][checkRows[2]] = true;
            grid[r4x.intValue() / 50][checkRows[3]] = true;
        } catch (ArrayIndexOutOfBoundsException a) {
            checkRows = new Integer[0];
        }
        // test code to print out the grid
        /*for (boolean[] row : grid) {
            for (boolean b : row) {
                System.out.print(b);
                System.out.print("\t");
            }
            System.out.println();
        }*/

        // assists in keeping track of which rows have blocks in them
        Integer[] checkRowsDistinct = Stream.of(checkRows)
            .distinct().toArray(Integer[]::new);

        int rowsToDrop = 0;
        deletedRows = 0;

        // loop through checkRows to see if rows need to be deleted, delete the rows
        for (int i = 0; i < checkRowsDistinct.length; i++) {
            if (highestRow > checkRowsDistinct[i]) {
                highestRow = checkRowsDistinct[i];
            } // set highestRow to use with dropRows
            if (rowDeletion(checkRowsDistinct[i]) == true) {
                deletedRows++;
                rowsToDrop = checkRowsDistinct[i];
            }
        }

        if (deletedRows > 0) {
            for (int i = rowsToDrop - 1; i > highestRow; i--) {
                //System.out.println("attempted to drop row: " + i); test code
                dropRow(i);
            }
        } // drop the rows that are above the deleted rows

    } // setBlockInGrid

    /**
     * Returns true if the specified block is at the bottom of the board.
     * @param block the specified block
     * @return true if block is at the bottom of the board
     */
    private boolean isBlockAtBottom(Block block) {
        // because the total height of the board is 700, and each
        // square is 50, the bottom would be 650
        if (block.r1.getY() == 650 || block.r2.getY() == 650 ||
            block.r3.getY() == 650 || block.r4.getY() == 650) {
            return true;
        } else {
            return false;
        } // if-else
    } // isBlockAtBottom(block)

    /**
     * Boolean method which tests if the game is over.
     * @return true if the block is set at the top of the board
     */
    private boolean isGameOver() {
        for (int i = 0; i < 9; i++) {
            if (grid[i][0] == true) {
                return true;
            }
        }
        return false;
    } // isGameover()

    /**
     * Method which alerts the player that the game is over. Alerts player
     * by showing a pop up stage. Gives the player the options to save
     * their scores to high scores.
     */
    private void getGameOver() {
        Stage gOverWindow = new Stage();
        Text gameOverTxt = new Text("Game Over! Your score was: " + score);
        gameOverTxt.setTextAlignment(TextAlignment.CENTER);
        //Button yes = new Button ("Yes");
        //Button no = new Button("No");

        Button save = new Button("Save");
        Button menu = new Button("Return to Main Menu");

        TextField winnerName = new TextField("Enter Winner's Name");
        HBox goHbox = new HBox();
        goHbox.getChildren().addAll(gameOverTxt);
        goHbox.setAlignment(Pos.CENTER);
        goHbox.setPadding(new Insets(10, 0, 0, 0));
        HBox saveName = new HBox();
        saveName.setSpacing(10);
        saveName.setPadding(new Insets(20, 5, 5, 5));
        saveName.getChildren().addAll(winnerName, save, menu);
        saveName.setAlignment(Pos.CENTER);

        VBox goVbox = new VBox();
        goVbox.getChildren().addAll(goHbox, saveName);
        goVbox.setAlignment(Pos.TOP_CENTER);
        Scene gameOverScene = new Scene(goVbox, 450, 120);

        // event handlers for buttons
        EventHandler<ActionEvent> saveHandler = event2 -> {
            if (winnerName.getText().equals("Enter Winner's Name") ||
                winnerName.getText().equals("PLEASE ENTER NAME")) {
                winnerName.setText("PLEASE ENTER NAME");
            } else {
                tetrisSaveScore(winnerName.getText());
                Stage s = ArcadeApp.getMainStage();
                Scene sc = ArcadeApp.getMainScene();
                s.setScene(sc);
                gOverWindow.close();
            }
        };
        EventHandler<ActionEvent> menuHandler = event3 -> {
            Stage s = ArcadeApp.getMainStage();
            Scene sc = ArcadeApp.getMainScene();
            s.setScene(sc);
            gOverWindow.close();
        };

        // set hanlers to buttons
        save.setOnAction(saveHandler);
        menu.setOnAction(menuHandler);

        // New window of stage
        gOverWindow.setMaxWidth(450);
        gOverWindow.setMaxHeight(120);
        gOverWindow.setMinWidth(450);
        gOverWindow.setMinHeight(120);

        gOverWindow.setTitle("Game Over");
        gOverWindow.sizeToScene();
        gOverWindow.setResizable(false);

        gOverWindow.setScene(gameOverScene);
        gOverWindow.initModality(Modality.APPLICATION_MODAL);
        gOverWindow.show();
    } // getGameOver()

    /**
     * Checks to see if the squares under the specified block are occupied.
     * @param block specified block
     * @return true if block has collided vertically with another block
     */
    private boolean blockCollided(Block block) {
        Double r1x = block.r1.getX();
        Double r1y = block.r1.getY();
        Double r2x = block.r2.getX();
        Double r2y = block.r2.getY();
        Double r3x = block.r3.getX();
        Double r3y = block.r3.getY();
        Double r4x = block.r4.getX();
        Double r4y = block.r4.getY();
        // if any of the squares under the specified block contain a square of another block,
        // the specfied block will not overlap it
        if (grid[r1x.intValue() / 50][(r1y.intValue() / 50) + 1] == true ||
            grid[r2x.intValue() / 50][(r2y.intValue() / 50) + 1] == true ||
            grid[r3x.intValue() / 50][(r3y.intValue() / 50) + 1] == true ||
            grid[r4x.intValue() / 50][(r4y.intValue() / 50) + 1] == true) {
            return true;
        }
        return false;
    } // blockCollided(block)

    /**
     * Ensures that blocks can not overlap with other blocks when
     * attempting to enter their space from the left or right.
     * @return true if the block will collide with another
     * @param block specified block that is being moved
     */
    private boolean sideCollision(Block block) {
        Double r1x = block.r1.getX();
        Double r1y = block.r1.getY();
        Double r2x = block.r2.getX();
        Double r2y = block.r2.getY();
        Double r3x = block.r3.getX();
        Double r3y = block.r3.getY();
        Double r4x = block.r4.getX();
        Double r4y = block.r4.getY();
        // if any of the squares to the right or left of  the specified block
        // contain a square of another block, the specfied block will not overlap it
        // try catch is implemented to handle when the blocks are on the edge of the board
        try {
            if (grid[(r1x.intValue() / 50) + 1][(r1y.intValue() / 50)] == true ||
                grid[(r2x.intValue() / 50) + 1][(r2y.intValue() / 50)] == true ||
                grid[(r3x.intValue() / 50) + 1][(r3y.intValue() / 50)] == true ||
                grid[(r4x.intValue() / 50) + 1][(r4y.intValue() / 50)] == true) {
                return true;
            } // if
        } catch (ArrayIndexOutOfBoundsException a) {
            if (grid[(r1x.intValue() / 50) - 1][(r1y.intValue() / 50)] == true ||
                grid[(r2x.intValue() / 50) - 1][(r2y.intValue() / 50)] == true ||
                grid[(r3x.intValue() / 50) - 1][(r3y.intValue() / 50)] == true ||
                grid[(r4x.intValue() / 50) - 1][(r4y.intValue() / 50)] == true) {
                return true;
            } // if
        }

        try {
            if (grid[(r1x.intValue() / 50) - 1][(r1y.intValue() / 50)] == true ||
                grid[(r2x.intValue() / 50) - 1][(r2y.intValue() / 50)] == true ||
                grid[(r3x.intValue() / 50) - 1][(r3y.intValue() / 50)] == true ||
                grid[(r4x.intValue() / 50) - 1][(r4y.intValue() / 50)] == true) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException a) {
            if (grid[(r1x.intValue() / 50) + 1][(r1y.intValue() / 50)] == true ||
                grid[(r2x.intValue() / 50) + 1][(r2y.intValue() / 50)] == true ||
                grid[(r3x.intValue() / 50) + 1][(r3y.intValue() / 50)] == true ||
                grid[(r4x.intValue() / 50) + 1][(r4y.intValue() / 50)] == true) {
                return true;
            } // if
        }
        return false;
    } // sideCollision

    /**
     * Determines how to rotate the block by the block type. Handled by switch-case.
     * @param block the specified block
     */
    private static void rotateBlock(Block block) {
        String type;
        if (turns == 0) {
            type = block.getType();
        } else {
            type = nextBlock.getType();
        }
        switch (type) {
        case "I":
            if (block.r1.getY() / 50 > 2) {
                block.rotateI();
            }
            break;
        case "J":
            if (block.r3.getY() / 50 > 1) {
                block.rotateJ();
            }
            break;
        case "L":
            if (block.r3.getY() / 50 > 1) {
                block.rotateL();
            }
            break;
        case "S":
            if (block.r3.getY() / 50 > 1) {
                block.rotateS();
            }
            break;
        case "Z":
            if (block.r2.getY() / 50 > 1) {
                block.rotateZ();
            }
            break;
        case "T":
            if (block.r4.getY() / 50 > 1) {
                block.rotateT();
            }
            break;
        } // switch-case
    }

    /**
     * This method handles player input with a switch case. If player
     * inputs right, the block will move right, if left, the block will move left.
     * If player presses up the specified block with rotate with the rotateBlock method.
     * @param block the specified block
     */
    private void playerInput(Block block) {
        this.tetScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case RIGHT:
            case D:
                if (sideCollision(block)) {
                    break;
                } else {
                    block.moveRight();
                    break;
                }
            case LEFT:
            case A:
                if (sideCollision(block)) {
                    break;
                } else {
                    block.moveLeft();
                    break;
                }
            case DOWN:
            case S:
                if (!blockCollided(block)) {
                    block.moveDown();
                }
                break;
            case UP:
            case W:
                //System.out.println("you've pressed up"); code for debugging
                rotateBlock(block);
            } // switch case
        });
    } // playerInput(block)
} // END OF CLASS
