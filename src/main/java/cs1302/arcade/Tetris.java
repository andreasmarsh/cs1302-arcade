package cs1302.arcade;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

// TODO:
// fix the back ground grid
// try to make shapes not overlap
// work on key presses

public class Tetris {

    int size = 50;
    int gridsizeX = size * 9;
    int gridsizeY = size * 14;
    boolean[][] grid = new boolean[10][15];
    private Text scoreTxt;
    private int lines;
    private Block currentBlock;
    private Block nextBlock;
    private VBox root;
    private Pane pane;

    private Timer gravity;
    private String BG = Tetris.class.getResource("/tetris/tetrisBG.png").toExternalForm();

    public Tetris() {
        root = new VBox();
        pane = new Pane();
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
        currentBlock = new Block("O"); // just a test
        pane.getChildren().addAll(currentBlock.r1, currentBlock.r2, currentBlock.r3, currentBlock.r4);

        gravity = new Timer();
        startGravity(gravity, currentBlock, 300);
    } // Tetris()

    public VBox getRoot() {
        String styleBG = "-fx-background-image: url(\'" + BG + "\')";
        root.getChildren().addAll(pane);
        root.setStyle(styleBG);
        return root;
    } // getRoot()

    /**
     * Using the random class, a random int from 0-69 is selected
     * and returns a randomBlockType determined by the random int.
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
     */
    private void startGravity(Timer gravity, Block block, long timePeriod) {
        Runnable r = () -> {
            Block newBlock = block;

            if (isBlockAtBottom(newBlock) == true) {
                setBlockInGrid(newBlock);

                nextBlock = new Block("L");
                newBlock = nextBlock;
                currentBlock = newBlock;
                pane.getChildren().addAll(newBlock.r1, newBlock.r2, newBlock.r3, newBlock.r4);

            }

            newBlock.moveDown();
        };
        gravity.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(r);
                } // run()
            }, 0, timePeriod); // end of timer.schedule(timertask, 0, 300);
    } // startGravity

    /**
     * This method places the block's position within the boolean grid.
     * A square on the grid is true when a square occupies the space on the grid.
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

        for (boolean[] row : grid) {
            for (boolean b : row) {
                System.out.print(b);
                System.out.print("\t");
            }
            System.out.println();
        }

    }

    /**
     * Returns true if the specified block is at the bottom of the board.
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
    } // isBlockAtBottom
}
