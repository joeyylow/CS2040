/**
 * Name         : LOW SI JIE
 * Matric. No   : A0223074N
*/

import java.util.*;

public class Editor {
    private void run() {
        Kattio io = new Kattio(System.in, System.out);
        LinkedList<String> left = new LinkedList<String>();
        LinkedList<String> middle = new LinkedList<String>();
        LinkedList<String> right = new LinkedList<String>();
        
        int Q = io.getInt();
        int marker = 0;
        int cursor = 0;

        for(int i = 0; i < Q; i++) {
            String input = io.getWord();
            if (input.equals("LEFT")) {
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
            } else if (input.equals("RIGHT")) {
                int totalSize = left.size() + middle.size() +
                right.size();
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
            } else if (input.equals("TYPE")) {
                String text = io.getWord();
                if (marker > cursor) {
                    left.addLast(text);
                    cursor++;
                    marker++;
                } else if (marker <= cursor) {
                    middle.addLast(text);
                    cursor++;
                }
            } else if (input.equals("MARKER")) {
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
            } else if (input.equals("SHIFTFRONT")) {
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
            } else if (input.equals("SHIFTBACK")) {
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
            } else if (input.equals("PRINT")) {
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
        io.close();
    }

    public static void main(String args[]) {
        Editor runner = new Editor();
        runner.run();
    }
}
