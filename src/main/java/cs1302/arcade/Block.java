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
    int position; // position of the block
    String type; // the type of the block
    private static int size = 50; // the size of each square in the grid

/**
 * Takes in specified name and uses a switch case to determine
 * which Block to construct. Each block is made up of 4 squares that are
 * layed out in the shape of the desired block.
 * @param name specified type of the block that will be constructor
 */
    public Block(String name) {
        r1 = new Rectangle(size - 1, size - 1); // the dimensions are 24x24
        r2 = new Rectangle(size - 1, size - 1); // this is so that there is
        r3 = new Rectangle(size - 1, size - 1); // an outline around each
        r4 = new Rectangle(size - 1, size - 1); // square of the block
        position = 1;
        switch (name) {
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
            r1.setY(0);
            r1.setFill(Color.GOLD);
            r2.setX(225);
            r2.setY(0);
            r2.setFill(Color.GOLD);
            r3.setX(175);
            r3.setY(-50);
            r3.setFill(Color.GOLD);
            r4.setX(225);
            r4.setY(-50);
            r4.setFill(Color.GOLD);
            break;
        } // end of switch-case
    } //Block(string)

/**
 * Helper method to shorten the constructor. Sets the squares for blocks
 * of type I or type L or type J;
 * @param r1 the first square of the block
 * @param r2 the second square of the block
 * @param r3 the third square of the block
 * @param r4 the fourth square of the block
 * @param type the string related to shape of configuration
 */
    private static void makeTypeILJ(Rectangle r1, Rectangle r2, Rectangle r3,
                                    Rectangle r4, String type) {
        if (type == "I") {
// creates 4 squares in a configuration of
// {r1, r2, r3, r4}
            r1.setX(125);
            r1.setY(-50);
            r1.setFill(Color.MAGENTA);
            r2.setX(175);
            r2.setY(-50);
            r2.setFill(Color.MAGENTA);
            r3.setX(225);
            r3.setY(-50);
            r3.setFill(Color.MAGENTA);
            r4.setX(275);
            r4.setY(-50);
            r4.setFill(Color.MAGENTA);
        } else if (type == "J") {
// {r1, r2, r3}
// {--, --, r4}
            r1.setX(175);
            r1.setY(-50);
            r1.setFill(Color.LIME);
            r2.setX(225);
            r2.setY(-50);
            r2.setFill(Color.LIME);
            r3.setX(275);
            r3.setY(-50);
            r3.setFill(Color.LIME);
            r4.setX(275);
            r4.setY(0);
            r4.setFill(Color.LIME);
        } else if (type == "L") {
// {r1, r2, r3}
// {r4, --, --}
            r4.setX(175);
            r4.setY(0);
            r4.setFill(Color.AQUA);
            r1.setX(175);
            r1.setY(-50);
            r3.setFill(Color.AQUA);
            r2.setX(225);
            r2.setY(-50);
            r2.setFill(Color.AQUA);
            r3.setX(275);
            r3.setY(-50);
            r1.setFill(Color.AQUA);
        }
    } //makeTypeILJ

/**
 * Helper method to shorten the constructor. Sets the squares for blocks
 * of type S or type Z or type T;
 * @param r1 the first square of the block
 * @param r2 the second square of the block
 * @param r3 the third square of the block
 * @param r4 the fourth square of the block
 * @param type the string related to shape of configuration
 */
    private static void makeTypeSZT(Rectangle r1, Rectangle r2, Rectangle r3,
                                    Rectangle r4, String type) {
        if (type == "S") {
// creates 4 squares in a configuration of
// {--, r3, r4}
// {r1, r2, --}
            r1.setX(175);
            r1.setY(0);
            r1.setFill(Color.DARKVIOLET);
            r2.setX(225);
            r2.setY(0);
            r2.setFill(Color.DARKVIOLET);
            r3.setX(225);
            r3.setY(-50);
            r3.setFill(Color.DARKVIOLET);
            r4.setX(275);
            r4.setY(-50);
            r4.setFill(Color.DARKVIOLET);
        } else if (type == "Z") {
// {r1, r2, --}
// {--, r3, r4}
            r1.setX(175);
            r1.setY(-50);
            r1.setFill(Color.DARKORANGE);
            r2.setX(225);
            r2.setY(-50);
            r2.setFill(Color.DARKORANGE);
            r3.setX(225);
            r3.setY(0);
            r3.setFill(Color.DARKORANGE);
            r4.setX(275);
            r4.setY(0);
            r4.setFill(Color.DARKORANGE);
        } else if (type == "T") {
// {--, r4, --}
// {r1, r2, r3}
            r1.setX(175);
            r1.setY(0);
            r1.setFill(Color.RED);
            r2.setX(225);
            r2.setY(0);
            r2.setFill(Color.RED);
            r3.setX(275);
            r3.setY(0);
            r3.setFill(Color.RED);
            r4.setX(225);
            r4.setY(-50);
            r4.setFill(Color.RED);
        }
    } //makeTypeSZT

/**
 * Returns the String which references what the type of block is.
 * @return String of block type
 */
    public String getType() {
        return type;
    } // getType

    /**
     * Moves the specified square to the right one.
     * @param r the specified square
     */
    private static void moveSquareRight(Rectangle r) {
        // this if statement blocks the sqaures from going out of left-bounds
        if (r.getX() + 50 < 450) {
            r.setX(r.getX() + 50);
        }
    } // moveSquareRight(rect)

    /**
     * Method which takes two specified squares and compares them to each other
     * by their location.
     * @return if negative, block is vertical, if position block is horizontal
     * @param r1 the first specified square
     * @param r2 the second specified square
     */
    private static int position(Rectangle r1, Rectangle r2) {
        if (r1.getX() + 50 == r2.getX()) { // if r1 and r2 are horizontally respectively
            return 1;
        } else if (r2.getX() + 50 == r1.getX()) { // if r2 and r1 hori
            return 2;
        } else if (r1.getY() + 50 == r2.getY()) { // if r1 is above r2
            return -1;
        } else if (r2.getY() + 50 == r1.getY()) { // if r2 is aboce r1
            return -2;
        }
        return 0;
    } // isHorizontal

    /**
     * Handles the rotation of the specified block. When called, rotates
     * the block once. Moves each square of the blocks
     * individually. Each block type is handled differently.
     */
    public void rotateI() {
        //System.out.println("r1.getX: " + r1.getX()); code for debugging
        if (position(r1, r2) == 1) { // if I is horizontal
            if (r1.getX() == 0) { // if I is on the far left
                moveSquareDown(r1);
                moveSquareRight(r1);
                moveSquareUp(r3);
                moveSquareLeft(r3);
                moveSquareUp(r4);
                moveSquareUp(r4);
                moveSquareLeft(r4);
                moveSquareLeft(r4);
            } else { // else if I is on the far right
                moveSquareUp(r1);
                moveSquareUp(r1);
                moveSquareRight(r1);
                moveSquareRight(r1);
                moveSquareUp(r2);
                moveSquareRight(r2);
                moveSquareDown(r4);
                moveSquareLeft(r4);
            } // ensures that block has space to rotate
        } else { // else if I is not horizonatal, vertical
            if (r1.getX() == 25) { // if the vertical I is on the far left
                moveSquareDown(r1);
                moveSquareRight(r2);
                moveSquareRight(r3);
                moveSquareRight(r3);
                moveSquareUp(r3);
                moveSquareRight(r4);
                moveSquareRight(r4);
                moveSquareRight(r4);
                moveSquareUp(r4);
                moveSquareUp(r4);
            } else if (r1.getX() == 425) { // if the vertical I is on the far right
                moveSquareDown(r1);
                moveSquareLeft(r2);
                moveSquareLeft(r3);
                moveSquareLeft(r3);
                moveSquareUp(r3);
                moveSquareLeft(r4);
                moveSquareLeft(r4);
                moveSquareLeft(r4);
                moveSquareUp(r4);
                moveSquareUp(r4);
            } else {
                moveSquareDown(r1);
                moveSquareDown(r1);
                moveSquareLeft(r1);
                moveSquareLeft(r1);
                moveSquareDown(r2);
                moveSquareLeft(r2);
                moveSquareUp(r4);
                moveSquareRight(r4);
            }
        } // large if-else
    } // rotateI()

    /**
     * Handles the rotation of the specified block. When called, rotates
     * the block once. Moves each square of the blocks
     * individually. Each block type is handled differently.
     */
    public void rotateJ() {
        if (position(r1, r2) == 1) { // horizontal with right peg down
            moveSquareUp(r1); // 1
            moveSquareRight(r1);
            moveSquareDown(r3);
            moveSquareLeft(r3);
            moveSquareLeft(r4);
            moveSquareLeft(r4);
        } else if (position(r1, r2) == -1) { // block is vertical with r1 on top
            if (r4.getX() == 25 || r1.getX() != 425) { // if shape is vertical not on far right
                moveSquareDown(r1); // 2a
                moveSquareRight(r1);
                moveSquareLeft(r3);
                moveSquareUp(r3);
                moveSquareUp(r4);
                moveSquareUp(r4);
            } else if (r1.getX() == 425) { // if shape is vertical on far right
                moveSquareDown(r1); // 2b
                moveSquareLeft(r2);
                moveSquareLeft(r3);
                moveSquareLeft(r3);
                moveSquareUp(r3);
                moveSquareLeft(r4);
                moveSquareUp(r4);
                moveSquareUp(r4);
            }
        } else if (position(r1, r2) == 2) { // if horizontal r2 and r1 respectively
            moveSquareDown(r1); // 3
            moveSquareLeft(r1);
            moveSquareUp(r3);
            moveSquareRight(r3);
            moveSquareRight(r4);
            moveSquareRight(r4);
        } else if (position(r1, r2) == -2) { // block is vertical with r2 on top
            if (r1.getX() == 25) { // if vertical shape is on far left
                moveSquareUp(r1); // 4a
                moveSquareRight(r2);
                moveSquareRight(r3);
                moveSquareDown(r3);
                moveSquareRight(r3);
                moveSquareRight(r4);
                moveSquareDown(r4);
                moveSquareDown(r4);
            } else { // if shape is not on far left
                moveSquareLeft(r1); // 4b
                moveSquareUp(r1);
                moveSquareRight(r3);
                moveSquareDown(r3);
                moveSquareDown(r4);
                moveSquareDown(r4);
            }
        } // larger else-if
    } // rotateJ

    /**
     * Handles the rotation of the specified block. When called, rotates
     * the block once. Moves each square of the blocks
     * individually. Each block type is handled differently.
     */
    public void rotateL() {
        if (position(r1, r2) == 1) { // if block is in default position
            moveSquareUp(r1); // 1
            moveSquareRight(r1);
            moveSquareDown(r3);
            moveSquareLeft(r3);
            moveSquareUp(r4);
            moveSquareUp(r4);
        } else if (position(r1, r2) == -1) { // if block is vertical with peg top left
            if (r1.getX() == 425) { // if block is in -1 position on the far right
                moveSquareDown(r1); // 2b
                moveSquareLeft(r2);
                moveSquareLeft(r3);
                moveSquareLeft(r3);
                moveSquareUp(r3);
                moveSquareRight(r4);
            } else { // if shape is in -1 pos and is on the left side/anywhere
                moveSquareRight(r1); // 2a
                moveSquareDown(r1);
                moveSquareLeft(r3);
                moveSquareUp(r3);
                moveSquareRight(r4);
                moveSquareRight(r4);
            }
        } else if (position(r1, r2) == 2) { // if block is in pos 2
            moveSquareDown(r1); // 3
            moveSquareLeft(r1);
            moveSquareUp(r3);
            moveSquareRight(r3);
            moveSquareDown(r4);
            moveSquareDown(r4);
        } else if (position(r1, r2) == -2) { // if block is vert in pos -2
            if (r4.getX() == 425) { // if block in pos -2 and far right
                moveSquareUp(r1); // 4b
                moveSquareLeft(r1);
                moveSquareDown(r3);
                moveSquareRight(r3);
                moveSquareLeft(r4);
                moveSquareLeft(r4);
            } else {
                moveSquareUp(r1);
                moveSquareRight(r2);
                moveSquareRight(r3);
                moveSquareRight(r3);
                moveSquareDown(r3);
                moveSquareLeft(r4);
            }
        } // larger if-else
    } // rotateL

    /**
     * Handles the rotation of the specified block. When called, rotates
     * the block once. Moves each square of the blocks
     * individually. Each block type is handled differently.
     */
    public void rotateS() {
        if (position(r1, r2) == 1) { // if horizontal
            moveSquareUp(r1); // 1
            moveSquareUp(r1);
            moveSquareUp(r2);
            moveSquareLeft(r2);
            moveSquareDown(r4);
            moveSquareLeft(r4);
        } else if (position(r1, r2) == -1) { // if vertical
            if (r1.getX() == 25) { // if vertical and far left
                moveSquareDown(r1); // 2a
                moveSquareRight(r2);
                moveSquareUp(r3);
                moveSquareUp(r4);
                moveSquareUp(r4);
                moveSquareRight(r4);
            } else { // if vertical and not on far left
                moveSquareDown(r1); // 2b
                moveSquareDown(r1);
                moveSquareLeft(r1);
                moveSquareDown(r2);
                moveSquareLeft(r3);
                moveSquareUp(r4);
            }
        }
    } // rotateS()

    /**
     * Handles the rotation of the specified block. When called, rotates
     * the block once. Moves each square of the blocks
     * individually. Each block type is handled differently.
     */
    public void rotateZ() {
        if (position(r1, r2) == 1) { // if horizontal
            moveSquareRight(r1); // 1
            moveSquareRight(r1);
            moveSquareUp(r1);
            moveSquareRight(r2);
            moveSquareUp(r3);
            moveSquareLeft(r4);
        } else if (position(r1, r2) == -1) {
            if (r3.getX() == 25) { // if block is vertical and on far left
                moveSquareDown(r1); // 2a
                moveSquareLeft(r1);
                moveSquareRight(r3);
                moveSquareDown(r3);
                moveSquareRight(r4);
                moveSquareRight(r4);
            } else { // if block is vertical and not on far left
                moveSquareLeft(r1);
                moveSquareLeft(r1);
                moveSquareDown(r1);
                moveSquareLeft(r2);
                moveSquareDown(r3);
                moveSquareRight(r4);
            }
        }
    } // rotateZ()

    /**
     * Handles the rotation of the specified block. When called, rotates
     * the block once. Moves each square of the blocks
     * individually. Each block type is handled differently.
     */
    public void rotateT() {
        if (position(r1, r2) == 1) { // if in position 1
            moveSquareUp(r1); // 1
            moveSquareUp(r1);
            moveSquareLeft(r2);
            moveSquareUp(r2);
            moveSquareLeft(r3);
            moveSquareLeft(r3);
        } else if (position(r1, r2) == -1) { // if position -1
            if (r1.getX() == 25) { // if vertical on far left
                moveSquareRight(r1); // 2a
                moveSquareRight(r1);
                moveSquareUp(r2);
                moveSquareRight(r2);
                moveSquareUp(r3);
                moveSquareUp(r3);
            } else {
                moveSquareRight(r1); // 2b
                moveSquareUp(r2);
                moveSquareUp(r3);
                moveSquareUp(r3);
                moveSquareLeft(r3);
                moveSquareLeft(r4);
            }
        } else if (position(r1, r2) == 2) { // if position 2, horizontal upsidedown
            moveSquareDown(r1); // 3
            moveSquareRight(r2);
            moveSquareRight(r3);
            moveSquareRight(r3);
            moveSquareUp(r3);
            moveSquareUp(r4);
        } else if (position(r1, r2) == -2) { // if pos -2, vertical -2
            if (r4.getX() == 25) { // if far left
                moveSquareUp(r1); // 4a
                moveSquareLeft(r1);
                moveSquareRight(r3);
                moveSquareDown(r3);
                moveSquareUp(r4);
                moveSquareRight(r4);
            } else {
                moveSquareUp(r1); // 4b
                moveSquareLeft(r1);
                moveSquareLeft(r1);
                moveSquareLeft(r2);
                moveSquareDown(r3);
                moveSquareUp(r4);
            }
        } // larger else-if
    } // rotateT()

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
 * @param r specified square
 */
    private static void moveSquareLeft(Rectangle r) {
        // this if statement blocks the sqaures from going out of left-bounds
        if (r.getX() - 50 > 0) {
            r.setX(r.getX() - 50);
        }
    } // moveSquareLeft(rect)

    /**
     * Moves block to the left once.
     */
    public void moveLeft() {
        if (r1.getX() - 50 > 0 && r2.getX() - 50 > 0 &&
            r3.getX() - 50 > 0 && r4.getX() - 50 > 0) {
            moveSquareLeft(r1);
            moveSquareLeft(r2);
            moveSquareLeft(r3);
            moveSquareLeft(r4);
        }
    } // moveLeft()

    /**
     * Moves specified square down one.
     * @param r the specified square
     */
    private static void moveSquareDown(Rectangle r) {
        // this if statement is to make sure that the block stops going down once
        // it hits the bottom of the board
        if (r.getY() + 50 < 700) {
            r.setY(r.getY() + 50);
        } // if
    } // moveSquaresDown

/**
 * Handles the repositioning of each individual square contained within the block.
 * Moves the block/squares to the down one block.
 */
    public void moveDown() {
        if (r1.getY() + 50 < 700 && r2.getY() + 50 < 700 &&
            r3.getY() + 50 < 700 && r4.getY() + 50 < 700) {
            moveSquareDown(r1);
            moveSquareDown(r2);
            moveSquareDown(r3);
            moveSquareDown(r4);
        } // if
    } // moveDown()

    /**
     * Moves specified square up once.
     * @param r specified square
     */
    private static void moveSquareUp(Rectangle r) {
        if (r.getY() - 50 > 0) {
            r.setY(r.getY() - 50);
        } // if
    } // moveSquareUp

} // END OF CLASS
