/**
 * Name         : LOW SI JIE
 * Matric. No   : A0223074N
*/

import java.util.*;

/**
 * Consider adding javadocs comments here
 * Refer to https://se-education.org/guides/conventions/java/intermediate.html
 * for full coding standards
 */
public class Editor {

    private static Kattio io;

    /**
     * Please add javadocs comment here
     * I have noticed many other places without javadocs comment, please amend.
     * @param args
     */
    public static void main(String args[]) {
        Editor runner = new Editor();
        io = new Kattio(System.in, System.out);

        int Q = io.getInt();

        //Consider using switch case to improve readability
        for(int i = 0; i < Q; i++) {
            String input = io.getWord();
            if (input.equals("LEFT")) {
                runner.moveLeft();
            } else if (input.equals("RIGHT")) {
                runner.moveRight();
            } else if (input.equals("TYPE")) {
                String text = io.getWord();
                runner.type(text);
            } else if (input.equals("MARKER")) {
                runner.marker();
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
     * Wrong positioning of variable declaration
     * Please place at the top of the class structure
     */
    LinkedList<String> left = new LinkedList<String>();
    LinkedList<String> middle = new LinkedList<String>();
    LinkedList<String> right = new LinkedList<String>();

    int marker = 0;
    int cursor = 0;

    /**
     * please put javadocs comment
     * for all methods here
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
     * Please use more expressive function names
     * @param text
     */
    private void type(String text) {
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
     * Please use more expressive function names
     * I have noticed multiple instances of this issue throughout the code, please amend.
     */
    private void marker() {
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
