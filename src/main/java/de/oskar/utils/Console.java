package de.oskar.utils;

import java.util.Date;

public class Console {
    private String cursor;
    private String color;
    private char pause;

    private int timePerPause;
    private int timePerSpace;
    private int timePerChar;

    private int blinkTime;

    private int width;

    /**
     * Prints a message to the console with a typewriter effect
     * @param message The message to print
     */
    public void printProcedural(String message) {
        if(message == null) return;
        if(message.length() == 0) return;
        String[] lines = message.split("\n");
        if(lines.length > 1) {
            for(int i = 0; i < lines.length; i++) {
                printProcedural(lines[i]);
            }
            return;
        }
        if(message.length() > width) {
            printProcedural(message.substring(0, width));
            printProcedural(message.substring(width));
            return;
        }
        String current = "" + color;
        int i = 0;
        while (i < message.length()) {
            char currentChar = message.charAt(i);
            i++;

            if (currentChar == pause) {
                showCursor();
                continue;
            }

            current = current + currentChar;
            System.out.print("\r" + current);
            int sleep = getDurationForChar(currentChar);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    /**
     * Prints the message
     * @param message The message to print
     */
    public void print(String message) {
        System.out.println(color + message);
    }

    /**
     * Prints the Cursor
     */
    private void showCursor() {
        System.out.print(" ");
        try {
            Date start = new Date();
            while (new Date().getTime() - start.getTime() < timePerPause) {
                if (new Date().getTime() - start.getTime() > blinkTime - 10) {
                    System.out.print("\b ");
                    continue;
                }
                if (new Date().getTime() % (blinkTime * 2) < blinkTime) {
                    System.out.print("\b" + cursor);
                } else {
                    System.out.print("\b ");
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\b");
    }

    private int getDurationForChar(char c) {
        if (c == ' ') {
            return timePerSpace;
        } else {
            return timePerChar;
        }
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public char getPause() {
        return pause;
    }

    public void setPause(char pause) {
        this.pause = pause;
    }

    public int getTimePerPause() {
        return timePerPause;
    }

    public void setTimePerPause(int timePerPause) {
        this.timePerPause = timePerPause;
    }

    public int getTimePerSpace() {
        return timePerSpace;
    }

    public void setTimePerSpace(int timePerSpace) {
        this.timePerSpace = timePerSpace;
    }

    public int getTimePerChar() {
        return timePerChar;
    }

    public void setTimePerChar(int timePerChar) {
        this.timePerChar = timePerChar;
    }

    public int getBlinkTime() {
        return blinkTime;
    }

    public void setBlinkTime(int blinkTime) {
        this.blinkTime = blinkTime;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
