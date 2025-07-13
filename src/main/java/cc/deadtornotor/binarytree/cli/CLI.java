package cc.deadtornotor.binarytree.cli;

import java.util.Random;

public class CLI {
    private final Color defaultColor;
    private final Color defaultBackgroundColor;

    public CLI(Color defaultColor, Color defaultBackgroundColor) {
        this.defaultColor = defaultColor;
        this.defaultBackgroundColor = defaultBackgroundColor;
    }

    public CLI(Color defaultColor) {
        this.defaultColor = defaultColor;
        this.defaultBackgroundColor = null;
    }

    public CLI() {
        this.defaultColor = null;
        this.defaultBackgroundColor = null;
    }

    public void println() {
        print(null, defaultColor, defaultBackgroundColor, true);
    }

    public void println(String message) {
        print(message, defaultColor, defaultBackgroundColor, true);
    }

    public void println(String message, Color color) {
        print(message, color, defaultBackgroundColor, true);
    }

    public void println(String message, Color color, Color backgroundColor) {
        print(message, color, backgroundColor, true);
    }

    public void print(String message) {
        print(message, defaultColor, defaultBackgroundColor, false);
    }

    public void print(String message, Color color) {
        print(message, color, defaultBackgroundColor, false);
    }

    public void print(String message, Color color, Color backgroundColor) {
        print(message, color, backgroundColor, false);
    }

    public void rainbowPrint(String message) {
        rainbowPrint(message, null, false);
    }

    public void rainbowPrint(String message, Color[] colors) {
        rainbowPrint(message, colors, false);
    }

    public void rainbowPrintln(String message) {
        rainbowPrint(message, null, true);
    }

    public void rainbowPrintln(String message, Color[] colors) {
        rainbowPrint(message, colors, true);
    }

    private void rainbowPrint(String message, Color[] colors, boolean newLine) {
        if (message == null) message = "";

        Color[] rainbowColors = colors != null ? colors : new Color[] {
            Color.RED,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            Color.BLUE,
            Color.PURPLE,
            Color.ORANGE,
            Color.VIOLET
        };

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            Random random = new Random();

            Color color = rainbowColors[random.nextInt(rainbowColors.length)];
            output.append(color.getCode()).append(c);
        }

        output.append(Color.RESET);

        if (newLine) {
            System.out.println(output);
        } else {
            System.out.print(output);
        }
    }

    private void print(String message, Color color, Color backgroundColor, boolean newLine) {
        if (message == null) {
            message = "";
        }

        StringBuilder output = new StringBuilder();

        if (color != null) {
            output.append(color.getCode());
        }

        if (backgroundColor != null) {
            output.append(backgroundColor.getCode());
        }

        output.append(message).append(Color.RESET);

        if (newLine) {
            System.out.println(output);
        } else {
            System.out.print(output);
        }
    }

    public void success(String message) {
        println();
        println("// " + message, Color.GREEN);
        println();
    }

    public void warning(String message) {
        println();
        println("// " + message, Color.YELLOW);
        println();
    }

    public void error(String message) {
        println();
        println("// " + message, Color.RED);
        println();
    }

    public void info(String message) {
        println();
        println("// " + message, Color.GRAY);
        println();
    }

    public void borderedPrintln(String message, Color color, Color backgroundColor) {
        borderedPrintln(message, color, backgroundColor, null);
    }

    public void borderedPrintln(String message, Color color) {
        borderedPrintln(message, color, null, null);
    }

    public void borderedPrintln(String message) {
        borderedPrintln(message, null, null, null);
    }

    public void borderedPrintln(String message, Color color, Color backgroundColor, Color borderColor) {
        String border = "━".repeat(message.length() + 10);

        println(border, borderColor, backgroundColor);
        println("     " + message + "     ", color, backgroundColor);
        println(border, borderColor, backgroundColor);
    }
}