package cc.deadtornotor.binarytree.cli;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class CLI {
    private final Color defaultColor;
    private final Color defaultBackgroundColor;
    private final Runnable inputPrefixPrinter;
    private final Scanner sc = new Scanner(System.in);

    public CLI(Runnable inputPrefixPrinter, Color defaultColor, Color defaultBackgroundColor) {
        this.defaultColor = defaultColor;
        this.defaultBackgroundColor = defaultBackgroundColor;
        this.inputPrefixPrinter = inputPrefixPrinter == null ? () -> {
            this.print(System.getProperty("user.name"), Color.ORANGE);
            this.print("@");
            this.print("binarytree ", Color.CYAN);
            this.rainbowPrint("~/ $ ");
        } : inputPrefixPrinter;
    }

    public CLI(Runnable inputPrefixPrinter, Color defaultColor) {
        this(inputPrefixPrinter, defaultColor, null);
    }

    public CLI(Runnable inputPrefixPrinter) {
        this(inputPrefixPrinter, null, null);
    }

    public CLI() {
        this(null, null, null);
    }

    public void printInputPrefix() {
        inputPrefixPrinter.run();
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
            output.append(color.toString()).append(c);
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
            output.append(color);
        }

        if (backgroundColor != null) {
            output.append(backgroundColor);
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

    public Object input(Class<?> type) {
        return input(type, null);
    }

    public Object input(Class<?> type, Runnable promptPrinter) {
        while (true) {
            if (promptPrinter != null) {
                promptPrinter.run();
            }
            this.printInputPrefix();
            String input = sc.nextLine();

            try {
                if (type == null) {
                    return null;
                } else if (type == int.class || type == Integer.class) {
                    return Integer.parseInt(input);
                } else if (type == double.class || type == Double.class) {
                    return Double.parseDouble(input);
                } else if (type == boolean.class || type == Boolean.class) {
                    return Boolean.parseBoolean(input);
                } else if (type == String.class) {
                    return input;
                } else {
                    throw new UnsupportedOperationException("Unsupported parameter type: " + type.getSimpleName());
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again");
            }
        }
    }
}