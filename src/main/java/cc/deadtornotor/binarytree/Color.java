package cc.deadtornotor.binarytree;

public final class Color {
    private static final String ESC = "\u001B[";
    public static final String RESET = ESC + "0m";

    private final String code;

    private Color(String code) {
        this.code = code;
    }

    private Color(int r, int g, int b, boolean isBackground) {
        this.code = rgbToAnsi256(r, g, b, isBackground);
    }

    public static final Color BLACK   = Color.fromRGB(24, 24, 24);
    public static final Color RED     = Color.fromRGB(239, 68, 68);
    public static final Color GREEN   = Color.fromRGB(34, 197, 94);
    public static final Color YELLOW  = Color.fromRGB(250, 204, 21);
    public static final Color BLUE    = Color.fromRGB(59, 130, 246);
    public static final Color PURPLE  = Color.fromRGB(147, 51, 234);
    public static final Color CYAN    = Color.fromRGB(6, 182, 212);
    public static final Color WHITE   = Color.fromRGB(248, 250, 252);
    public static final Color ORANGE  = Color.fromRGB(249, 115, 22);
    public static final Color VIOLET  = Color.fromRGB(124, 58, 237);
    public static final Color GRAY    = Color.fromRGB(128, 128, 128);

    public static final Color BLACK_BG   = Color.fromRGBBackground(15, 15, 15);
    public static final Color RED_BG     = Color.fromRGBBackground(185, 28, 28);
    public static final Color GREEN_BG   = Color.fromRGBBackground(21, 128, 61);
    public static final Color YELLOW_BG  = Color.fromRGBBackground(202, 138, 4);
    public static final Color BLUE_BG    = Color.fromRGBBackground(30, 64, 175);
    public static final Color PURPLE_BG  = Color.fromRGBBackground(88, 28, 135);
    public static final Color CYAN_BG    = Color.fromRGBBackground(8, 145, 178);
    public static final Color WHITE_BG   = Color.fromRGBBackground(226, 232, 240);
    public static final Color ORANGE_BG  = Color.fromRGBBackground(194, 65, 12);
    public static final Color VIOLET_BG  = Color.fromRGBBackground(91, 33, 182);
    public static final Color GRAY_BG    = Color.fromRGBBackground(64, 64, 64);

    public static Color fromRGB(int r, int g, int b) {
        return new Color(r, g, b, false);
    }

    public static Color fromRGBBackground(int r, int g, int b) {
        return new Color(r, g, b, true);
    }

    private static String rgbToAnsi256(int r, int g, int b, boolean isBackground) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException("RGB values must be between 0 and 255");
        }

        int r6 = Math.round(r * 5 / 255f);
        int g6 = Math.round(g * 5 / 255f);
        int b6 = Math.round(b * 5 / 255f);
        int colorIndex = 16 + 36 * r6 + 6 * g6 + b6;

        return ESC + (isBackground ? "48" : "38") + ";5;" + colorIndex + "m";
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}