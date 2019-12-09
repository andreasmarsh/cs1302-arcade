package cs1302.arcade;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.scene.input.KeyEvent;
import java.util.Arrays;
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
// fix the back ground grid
// work on rotation for each shape
// work on side ways collisions
// fix game over
// work on row deletion

/**
 * Class which initiates the JavaFx game, Tetris.
 * The class uses Block to create and control the pieces of the game.
 * The board contains a space where Blocks are controled and a 2d boolean array
 * which keeps track of where the blocks are set.
 */
public class Tetris {

    int size = 50;
    int gridsizeX = size * 9;
    int gridsizeY = size * 14;
    boolean[][] grid = new boolean[9][15];
    private Text scoreTxt;
    private int lines;
    private static Block currentBlock;
    private static Block nextBlock;
    private VBox root;
    private Pane pane;
    private Stage stage;
    private Scene mainScene = ArcadeApp.getMainScene();
    private Scene tetScene = ArcadeApp.getTScene();

    private Timer gravity;
    private String BG = Tetris.class.getResource("/tetris/tetrisBG.png").toExternalForm();

    /**
     * Constructor of Tetris. Creates the roots and sets the stage and scene.
     * Creates the 2d boolean grid, and by default the 2d array is filled with false values.
     * The constructor also spawns the first block.
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
/*        Text scoreTxt = new Text("score goes here");
        scoreTxt.setStyle("-fx-font: 25 arial;");
        scoreTxt.setY(286);
        scoreTxt.setX(550);
        Text lineTxt = new Text("lines go here");
        lineTxt.setY(330);
        lineTxt.setX(550);
        pane.getChildren().addAll(scoreTxt, lineTxt);
*/
        currentBlock = new Block(randomBlockType()); // just a test
        pane.getChildren().addAll(currentBlock.r1, currentBlock.r2, currentBlock.r3, currentBlock.r4);

        gravity = new Timer();
        startGravity(gravity, currentBlock, 500);

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
        System.out.println("random int: " + n);
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
    private void startGravity(Timer gravity, Block block, long timePeriod) {
        Runnable r = () -> {
            Block newBlock = block;

            // if block is at the bottom, or the block has collided with another block, set
            // the block into the grid and spawn a new block.
            if (isBlockAtBottom(newBlock) == true || blockCollided(newBlock) == true) {
                setBlockInGrid(newBlock);
                if (isGameOver() == true) {
                    System.out.println("Game Over!");
                    getGameOver();
                    gravity.cancel();
                } else { // if not game over, keep spawning blocks
                    nextBlock = new Block(randomBlockType());
                    newBlock = nextBlock;

                    pane.getChildren().addAll(newBlock.r1, newBlock.r2, newBlock.r3, newBlock.r4);
                }
            }
            // if the specified block does not collide with another one, it will keep moving down
            if (blockCollided(newBlock) == false) {
                    newBlock.moveDown();
                    playerInput(newBlock);
            }
        }; // Runnable r
        gravity.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(r);
                } // run()
            }, 0, timePeriod); // end of timer.schedule(timertask, 0, 300);
    } // startGravity

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
        grid[r1x.intValue() / 50][r1y.intValue() / 50] = true;
        grid[r2x.intValue() / 50][r2y.intValue() / 50] = true;
        grid[r3x.intValue() / 50][r3y.intValue() / 50] = true;
        grid[r4x.intValue() / 50][r4y.intValue() / 50] = true;

        // test code to print out the grid
        for (boolean[] row : grid) {
            for (boolean b : row) {
                System.out.print(b);
                System.out.print("\t");
            }
            System.out.println();
        }

        //System.out.println(r1y.intValue() / 50); // debug code
        /*if (r1y.intValue() / 50 == 0 || r2y.intValue() / 50 == 0 ||
            r3y.intValue() / 50 == 0 || r4y.intValue() / 50 == 0) {
            System.out.println("Game Over!"); // test code
            }*/

    }

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
    }

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
     * Determines how to rotate the block by the block type. Handled by switch-case.
     * @param block the specified block
     */
    private static void rotateBlock(Block block) {
        String type;
        if (nextBlock.getType() == null) {
            type = currentBlock.getType();
        } else {
            type = nextBlock.getType();
        }
        System.out.println("attempting to rotate block of type: " + type);
        switch (type) {
        case "I":
            block.rotateI();
            break;
        case "J":
            block.rotateJ();
            break;
        case "L":
            block.rotateL();
            break;
        case "S":
            block.rotateS();
            break;
        case "Z":
            block.rotateZ();
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
                    block.moveRight();
                    break;
                case LEFT:
                case A:
                    block.moveLeft();
                    break;
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
}
