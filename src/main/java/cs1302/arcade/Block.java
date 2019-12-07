package cs1302.arcade;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class handles the construction of blocks, and their movements.
 * There are a total of 7 types of blocks. Each named after
 * the shape that they take.
 */
public class Block {
    static Rectangle r1;
    static Rectangle r2;
    static Rectangle r3;
    static Rectangle r4;
    String type; // the type of the block
    private static int size = 50; // the size of each square in the grid

/**
 * Takes in specified name and uses a switch case to determine
 * which Block to construct. Each block is made up of 4 squares that are
 * layed out in the shape of the desired block.
 */
    public Block(String name) {
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
            r1.setX(175);
            r1.setY(50);
            r1.setFill(Color.GOLD);
            r2.setX(225);
            r2.setY(50);
            r2.setFill(Color.GOLD);
            r3.setX(175);
            r3.setFill(Color.GOLD);
            r4.setX(225);
            r4.setFill(Color.GOLD);
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
            r1.setX(125);
            r1.setFill(Color.MAGENTA);
            r2.setX(175);
            r2.setFill(Color.MAGENTA);
            r3.setX(225);
            r3.setFill(Color.MAGENTA);
            r4.setX(275);
            r4.setFill(Color.MAGENTA);
        } else if (type == "J") {
// {r1, r2, r3}
// {--, --, r4}
            r1.setX(175);
            r1.setY(50);
            r1.setFill(Color.LIME);
            r2.setX(225);
            r2.setY(50);
            r2.setFill(Color.LIME);
            r3.setX(275);
            r3.setY(50);
            r3.setFill(Color.LIME);
            r4.setX(275);
            r4.setFill(Color.LIME);
        } else if (type == "L") {
// {r2, r3, r4}
// {r1, --, --}
            r1.setX(175);
            //r1.setY(1 * size);
            r1.setFill(Color.DEEPSKYBLUE);
            r2.setX(175);
            r2.setY(50);
            r2.setFill(Color.DEEPSKYBLUE);
            r3.setX(225);
            r3.setY(50);
            r3.setFill(Color.DEEPSKYBLUE);
            r4.setX(275);
            r4.setY(50);
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
            r1.setX(175);
            r1.setFill(Color.DARKVIOLET);
            r2.setX(225);
            r2.setFill(Color.DARKVIOLET);
            r3.setX(225);
            r3.setY(50);
            r3.setFill(Color.DARKVIOLET);
            r4.setX(275);
            r4.setY(50);
            r4.setFill(Color.DARKVIOLET);
        } else if (type == "Z") {
// {r1, r2, --}
// {--, r3, r4}
            r1.setX(175);
            r1.setY(50);
            r1.setFill(Color.ORANGE);
            r2.setX(225);
            r2.setY(50);
            r2.setFill(Color.ORANGE);
            r3.setX(225);
            r3.setFill(Color.ORANGE);
            r4.setX(275);
            r4.setFill(Color.ORANGE);
        } else if (type == "T") {
// {r1, r2, r3}
// {--, r4, --}
            r1.setX(175);
            r1.setY(50);
            r1.setFill(Color.RED);
            r2.setX(225);
            r2.setY(50);
            r2.setFill(Color.RED);
            r3.setX(275);
            r3.setY(50);
            r3.setFill(Color.RED);
            r4.setX(225);
            r4.setFill(Color.RED);
        }
    } //makeTypeSZT

/**
 * Returns the String which references what the type of block is.
 */
    public String getType() {
        return type;
    } // getType

    private static void moveSquareRight(Rectangle r) {
        // this if statement blocks the sqaures from going out of left-bounds
        if (r.getX() + 50 < 450) {
            r.setX(r.getX() + 50);
        }
    } // moveSquareRight(rect)

/**
 * Handles the repositioning of each individual square contained within the block.
 * Moves the block/squares to the right one block.
 */
    public void moveRight() {
        if (r1.getX() + 50 < 450 && r2.getX() + 50 < 450 &&
            r3.getX() + 50 < 450 && r4.getX() + 50 < 450) {
            moveSquareRight(r1);
            moveSquareRight(r2);
            moveSquareRight(r3);
            moveSquareRight(r4);
        }
    } // moveRight(block)

/**
 * Handles the repositioning of each individual square contained within the block.
 * Moves the block/squares to the left one block.
 */
    private static void moveSquareLeft(Rectangle r) {
        // this if statement blocks the sqaures from going out of left-bounds
        if (r.getX() - 50 > 0) {
            r.setX(r.getX() - 50);
        }
    } // moveSquareLeft(rect)

    public void moveLeft() {
        if (r1.getX() - 50 > 0 && r2.getX() - 50 > 0 &&
            r3.getX() - 50 > 0 && r4.getX() - 50 > 0) {
            moveSquareLeft(r1);
            moveSquareLeft(r2);
            moveSquareLeft(r3);
            moveSquareLeft(r4);
        }
    } // moveLeft()

    private static void moveSquaresDown() {
        // this if statement is to make sure that the block stops going down once
        // it hits the bottom of the board
        if (r1.getY() + 50 < 700 && r2.getY() + 50 < 700 &&
            r3.getY() + 50 < 700 && r4.getY() + 50 < 700) {
            r1.setY(r1.getY() + 50);
            r2.setY(r2.getY() + 50);
            r3.setY(r3.getY() + 50);
            r4.setY(r4.getY() + 50);
        } // if
    } // moveSquaresDown

/**
 * Handles the repositioning of each individual square contained within the block.
 * Moves the block/squares to the down one block.
 */
    public void moveDown() {
        moveSquaresDown();
    } // moveDown()

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
