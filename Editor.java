/**
 * Name         : LOW SI JIE
 * Matric. No   : A0223074N
*/

import java.util.*;

/**
 * This is an Editor class that simulates a text editor.
 */
public class Editor {

    /**
     * Usage of Kattio to increase the speed of running the program.
     */
    private static Kattio io;

    /**
     * Instantiation of three linked lists.
     * The names of the linked lists represents the relative positions of
     * the text with reference to the cursor.
     * `left` is a linked list with characters that are on the left of the
     * cursor.
     * `middle` is a linked list with characters that are between the cursor
     * and the marker.
     * `right` is a linked list with characters that are on the right of the
     * cursor.
     */
    LinkedList<String> left = new LinkedList<String>();
    LinkedList<String> middle = new LinkedList<String>();
    LinkedList<String> right = new LinkedList<String>();

    /**
     * Integers to keep track of the number of characters that are before
     * the marker and cursor.
     */
    int marker = 0;
    int cursor = 0;

    /**
     * The main method is a driver class method that runs the program.
     */
    public static void main(String args[]) {
        Editor runner = new Editor();
        io = new Kattio(System.in, System.out);

        /**
         * An integer which indicates the number of lines of operation
         * input by the user.
         */
        int Q = io.getInt();

        /**
         * A for loop that reads each line of input and performs the
         * operations by calling functions.
         */
        for(int i = 0; i < Q; i++) {
            String input = io.getWord();
            if (input.equals("LEFT")) {
                runner.moveLeft();
            } else if (input.equals("RIGHT")) {
                runner.moveRight();
            } else if (input.equals("TYPE")) {
                String text = io.getWord();
                runner.typeCharacter(text);
            } else if (input.equals("MARKER")) {
                runner.positionMarker();
            } else if (input.equals("SHIFTFRONT")) {
                runner.shiftFront();
            } else if (input.equals("SHIFTBACK")) {
                runner.shiftBack();
            } else if (input.equals("PRINT")) {
                runner.printResult();
            }
        }   
        io.close();
    }
    
    /**
     * This method moves the cursor one position to the left.
     */
    private void moveLeft() {
        if (cursor > 0) {
            if (marker >= cursor) {
                String element = left.removeLast();
                middle.addFirst(element);
                cursor--;
            } else if (marker < cursor) {
                String element = middle.removeLast();
                right.addFirst(element);
                cursor--;
            }
        }
    }

    /**
     * This method moves the cursor one position to the right.
     */
    private void moveRight() {
        int totalSize = left.size() + middle.size() + right.size();
        if (cursor < totalSize) {
            if (marker > cursor) {
                String element = middle.removeFirst();
                left.addLast(element);
                cursor++;
            } else if (marker <= cursor) {
                String element = right.removeFirst();
                middle.addLast(element);
                cursor++;
            }
        }
    }

    /**
     * This method adds a new character at the current position of the
     * cursor.
     * After adding the character, the cursor will be on the right of
     * the new character.
     * @param text new character that will be added.
     */
    private void typeCharacter(String text) {
        if (marker > cursor) {
            left.addLast(text);
            cursor++;
            marker++;
        } else if (marker <= cursor) {
            middle.addLast(text);
            cursor++;
        }
    }

    /**
     * The marker will be positioned at the current position of the
     * cursor.
     * Nothing happens if the marker and cursor are already at the same
     * postion.
     */
    private void positionMarker() {
        if (marker != cursor) {
            if (marker > cursor) {
                while (middle.size() != 0) {
                    String element = middle.removeLast();
                    right.addFirst(element);
                }
                marker = cursor;
            } else if (marker < cursor) {
                while (middle.size() != 0) {
                    String element = middle.removeFirst();
                    left.addLast(element);
                }
                marker = cursor;
            }
        }
    }

    /**
     * Text between cursor and marker will be shifted to the front of
     * the text.
     */
    private void shiftFront() {
        if (marker > cursor) {
            cursor = marker;
            while (middle.size() != 0) {
                String element = middle.removeLast();
                left.addFirst(element);
            }
        } else if (marker < cursor) {
            marker = cursor;
            while (middle.size() != 0) {
                String element = middle.removeLast();
                left.addFirst(element);
            }
        }
    }

    /**
     * Text between cursor and marker will be shifted to the back of the
     * text.
     */
    private void shiftBack() {
        if (marker > cursor) {
            marker = cursor;
            while (middle.size() != 0) {
                String element = middle.removeFirst();
                right.addLast(element);
            }
        } else if (marker < cursor) {
            cursor = marker;
            while (middle.size() != 0) {
                String element = middle.removeFirst();
                right.addLast(element);
            }
        }
    }

    /**
     * All the text that is in the text editor will be printed out as an
     * output.
     */
    private void printResult() {
        for (String s : left) {
            io.print(s);
        }
        for (String s : middle) {
            io.print(s);
        }
        for (String s : right) {
            io.print(s);
        }
        io.println();
        io.flush();
    }
}
