package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Tetris {

    int move = 50;
    int size = 50;
    int gridsizeX = size * 9;
    int gridsizeY = size * 14;
    int[][] grid = new int[10][15];
    private int score;
    private int lines;
    Block currentBlock;
    Block nextBlock;
    private VBox root;
    private String BG = Tetris.class.getResource("/tetris/tetrisBG.png").toExternalForm();

    public Tetris() {
        root = new VBox();
    } // Tetris()

    public VBox getRoot() {
        String styleBG = "-fx-background-image: url(\'" + BG + "\')";

        root.setStyle(styleBG);
        return root;
    } // getRoot()
}
