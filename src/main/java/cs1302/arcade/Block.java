package cs1302.arcade;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class handles the construction of blocks, and their movements.
 * There are a total of 7 types of blocks. Each named after
 * the shape that they take.
 */
public class Block {
    Rectangle r1;
    Rectangle r2;
    Rectangle r3;
    Rectangle r4;
    String type; // the type of the block
    int size; // the size of each square in the grid

/**
 * Takes in specified name and uses a switch case to determine
 * which Block to construct.
 */
    public Block(String name) {
        size = 25; // 25
        r1 = new Rectangle(size - 1, size - 1); // the dimensions are 24x24
        r2 = new Rectangle(size - 1, size - 1); // this is so that there is
        r3 = new Rectangle(size - 1, size - 1); // an outline around each
        r4 = new Rectangle(size - 1, size - 1); // square of the block
        switch(name) {
        case "I":
            type = "I";
            makeTypeILJ(r1, r2, r3, r4, type);
            break;
        case "L":
            type = "L";
            makeTypeILJ(r1, r2, r3, r4, type);
            break;
        case "J":
            type = "J";
            makeTypeILJ(r1, r2, r3, r4, type);
            break;
        case "S":
            type = "S";
            makeTypeSZT(r1, r2, r3, r4, type);
            break;
        case "Z":
            type = "Z";
            makeTypeSZT(r1, r2, r3, r4, type);
            break;
        case "T":
            type = "T";
            makeTypeSZT(r1, r2, r3, r4, type);
            break;
        case "O":
         // {r1, r2}
         // {r3, r4}
            type = "O";
            r1.setX(1);
            r1.setY(2);
            r1.setFill(Color.GOLD);
            r2.setX(2);
            r2.setY(2);
            r2.setFill(Color.GOLD);
            r3.setX(1);
            r3.setY(1);
            r3.setFill(Color.GOLD);
            r4.setX(2);
            r4.setY(1);
            r3.setFill(Color.GOLD);
            break;
        } // end of switch-case
    } //Block(string)

/**
 * Helper method to shorten the constructor. Sets the squares for blocks
 * of type I or type L or type J;
 */
    private static void makeTypeILJ(Rectangle r1, Rectangle r2, Rectangle r3,
                                    Rectangle r4, String type) {
        if (type == "I") {
// creates 4 squares in a configuration of
// {r1, r2, r3, r4}
            r1.setX(1);
            r1.setFill(Color.MAGENTA);
            r2.setX(2);
            r2.setFill(Color.MAGENTA);
            r3.setX(3);
            r3.setFill(Color.MAGENTA);
            r4.setX(4);
            r4.setFill(Color.MAGENTA);
        } else if (type == "J") {
// {r1, r2, r3}
// {--, --, r4}
            r1.setX(1);
            r1.setY(2);
            r1.setFill(Color.LIME);
            r2.setX(2);
            r2.setY(2);
            r2.setFill(Color.LIME);
            r3.setX(3);
            r3.setY(2);
            r3.setFill(Color.LIME);
            r4.setX(3);
            r4.setY(1);
            r4.setFill(Color.LIME);
        } else if (type == "L") {
// {--, --, r4}
// {r1, r2, r3}
            r1.setX(1);
            r1.setY(1);
            r1.setFill(Color.DEEPSKYBLUE);
            r2.setX(2);
            r2.setY(1);
            r2.setFill(Color.DEEPSKYBLUE);
            r3.setX(3);
            r3.setY(1);
            r3.setFill(Color.DEEPSKYBLUE);
            r4.setX(3);
            r4.setY(2);
            r4.setFill(Color.DEEPSKYBLUE);
        }
    } //makeTypeILJ

/**
 * Helper method to shorten the constructor. Sets the squares for blocks
 * of type I or type L or type J;
 */
    private static void makeTypeSZT(Rectangle r1, Rectangle r2, Rectangle r3,
                                    Rectangle r4, String type) {
        if (type == "S") {
// creates 4 squares in a configuration of
// {--, r3, r4}
// {r1, r2, --}
            r1.setX(1);
            r1.setY(1);
            r1.setFill(Color.DARKVIOLET);
            r2.setX(2);
            r2.setY(1);
            r2.setFill(Color.DARKVIOLET);
            r3.setX(2);
            r3.setY(2);
            r3.setFill(Color.DARKVIOLET);
            r4.setX(3);
            r4.setY(2);
            r4.setFill(Color.DARKVIOLET);
        } else if (type == "Z") {
// {r1, r2, --}
// {--, r3, r4}
            r1.setX(1);
            r1.setY(2);
            r1.setFill(Color.ORANGE);
            r2.setX(2);
            r2.setY(2);
            r2.setFill(Color.ORANGE);
            r3.setX(2);
            r3.setY(1);
            r3.setFill(Color.ORANGE);
            r4.setX(3);
            r4.setY(1);
            r4.setFill(Color.ORANGE);
        } else if (type == "T") {
// {r1, r2, r3}
// {--, r4, --}
            r1.setX(1);
            r1.setY(2);
            r1.setFill(Color.RED);
            r2.setX(2);
            r2.setY(2);
            r2.setFill(Color.RED);
            r3.setX(3);
            r3.setY(2);
            r3.setFill(Color.RED);
            r4.setX(2);
            r4.setY(1);
            r4.setFill(Color.RED);
        }
    } //makeTypeSZT

/**
 * Returns the String which references what the type of block is.
 */
    public String getType() {
        return type;
    } // getType

/**
 * Handles the repositioning of each individual square contained within the block.
 * Moves the block/squares to the right one block.
 */
    public void moveRight(Block block) {
        int move = size; // move is the size of one block in the gameBoard
        throw new UnsupportedOperationException("not yet implemented");
    } // moveRight(block)

/**
 * Handles the repositioning of each individual square contained within the block.
 * Moves the block/squares to the left one block.
 */
    public void moveLeft(Block block) {
        int move = size; // move is the size of one block in the gameBoard
        throw new UnsupportedOperationException("not yet implemented");
    } // moveLeft(block)

/**
 * Handles the repositioning of each individual square contained within the block.
 * Moves the block/squares to the down one block.
 */
    public void moveDown(Block block) {
        int move = size; // move is the size of one block in the gameBoard
        throw new UnsupportedOperationException("not yet implemented");
    } // moveDown(block)

/**
 * Handles the rotation of the specified block. When called, rotates
 * the block in a clockwise rotation once. Moves each square of the blocks
 * individually.
 */
    public void rotateBlock(Block block) {
        int move = size; // move is the size of one block in the gameBoard
        throw new UnsupportedOperationException("not yet implemented");
    } // rotateBlock(block)

}
