package cs1302.arcade;

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


// TODO:
// rotate T DONE
// sideways collisions DONE
// IV for next block
// set up menu button
// set up control button
// row deletion DONE
// score track DONE
// speed level adjustment DONE

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

    // strings with image locations
    private String BG = Tetris.class.getResource("/tetris/tetrisBG.png").toExternalForm();
    private String menuStr = Tetris.class.getResource("/tetris/tetMenu.png").toExternalForm();
    private String controlsStr = Tetris.class
        .getResource("/tetris/tetControls.png").toExternalForm();

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
        menuIV.setX(490);
        menuIV.setY(280);
        controlsIV.setX(490);
        controlsIV.setY(350);

        scoreTxt = new Text("Score: 0");
        scoreTxt.setStyle("-fx-font: 22 arial;");
        scoreTxt.setY(500);
        scoreTxt.setX(500);
        levelTxt = new Text("Level: 0");
        levelTxt.setY(550);
        levelTxt.setX(500);
        pane.getChildren().addAll(menuIV, controlsIV, scoreTxt, levelTxt);

        currentBlock = new Block(randomBlockType());
        //currentBlock = new Block("Z"); // test code for specific block
        pane.getChildren().addAll(currentBlock.r1, currentBlock.r2,
                                  currentBlock.r3, currentBlock.r4);

        turns = 0;
        gravity = new Timer();
        startGravity(gravity, currentBlock, scoreTxt, levelTxt, 500);

    } // Tetris()

    /**
     * Method which gets Tetris root. Used by main menu screen in scene controlling.
     * @return VBox the root of the tetris scene.
     */
    public VBox getRoot() {
        String styleBG = "-fx-background-image: url(\'" + BG + "\')";
        root.getChildren().addAll(pane);
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
     * @param Timer which schedules the TimerTask
     * @param block the initial block of the game
     * @param timePeriod controls the speed at which the blocks fall
     */
    private void startGravity(Timer gravity, Block block,
                              Text scoreTxt, Text levelTxt, long timePeriod) {
        Runnable r = () -> {

            if (isGameOver() == true) {
                gravity.cancel();
                getGameOver();
            } else {

                Block newBlock = block;

                // if block is at the bottom, or the block has collided with another block, set
                // the block into the grid and spawn a new block.
                if (isBlockAtBottom(newBlock) == true || blockCollided(newBlock) == true) {
                    setBlockInGrid(newBlock);
                    if (score != 0) {
                        scoreTxt.setText("Score: " + score);
                        level = score / 150;
                        System.out.println("level" + level);
                        if (level > 0) {
                            levelTxt.setText("Level: " + level);
                            increaseSpeed();
                        }
                    } // if score is not 0

                    nextBlock = new Block(randomBlockType());
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

    private void increaseSpeed() {
        gravity.cancel();
        gravity = new Timer();
        startGravity(gravity, currentBlock, scoreTxt, levelTxt, 500 - (150 * level));
    }

    // return true if a row has been deleted, used to check if
    // the method to lower the above row should be called
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
        checkRows = new Integer[] {r1y.intValue() / 50, r2y.intValue() / 50,
                                   r3y.intValue() / 50, r4y.intValue() / 50};

        grid[r1x.intValue() / 50][checkRows[0]] = true;
        grid[r2x.intValue() / 50][checkRows[1]] = true;
        grid[r3x.intValue() / 50][checkRows[2]] = true;
        grid[r4x.intValue() / 50][checkRows[3]] = true;

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
        Text gameOverTxt = new Text("Game Over!");
        Button yes = new Button ("Yes");
        Button no = new Button("No");

        HBox goHbox = new HBox();
        goHbox.getChildren().addAll(gameOverTxt);
        goHbox.setAlignment(Pos.CENTER);
        goHbox.setPadding(new Insets(10, 0, 0, 0));
        HBox yesNo = new HBox();
        yesNo.setSpacing(263);
        yesNo.setPadding(new Insets(20, 5, 5, 5));
        yesNo.getChildren().addAll(yes, no);

        VBox goVbox = new VBox();
        goVbox.getChildren().addAll(goHbox, yesNo);
        goVbox.setAlignment(Pos.TOP_CENTER);
        Scene gameOverScene = new Scene(goVbox, 350, 80);

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

        System.out.println("r1x: " + r1x  +"r2x: " + r2x + "r3x: " + r3x + "r4x: " + r4x);

        //if (r1x != 25.0 || r2x != 25.0 || r3x != 25.0 || r4x != 25.0) { // if not on left
        try {
            if (grid[(r1x.intValue() / 50) + 1][(r1y.intValue() / 50)] == true ||
                grid[(r2x.intValue() / 50) + 1][(r2y.intValue() / 50)] == true ||
                grid[(r3x.intValue() / 50) + 1][(r3y.intValue() / 50)] == true ||
                grid[(r4x.intValue() / 50) + 1][(r4y.intValue() / 50)] == true) {
                return true;
            }// if
        } catch (ArrayIndexOutOfBoundsException a) {
            if (grid[(r1x.intValue() / 50) - 1][(r1y.intValue() / 50)] == true ||
                grid[(r2x.intValue() / 50) - 1][(r2y.intValue() / 50)] == true ||
                grid[(r3x.intValue() / 50) - 1][(r3y.intValue() / 50)] == true ||
                grid[(r4x.intValue() / 50) - 1][(r4y.intValue() / 50)] == true) {
                return true;
            }// if
        }
            //}
            //if (r1x != 425.0 || r2x != 425.0 || r3x != 425.0 || r4x != 425.0) { // if not on right
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
            }// if
        }

        return false;

    }

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
                switch(e.getCode()) {
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
