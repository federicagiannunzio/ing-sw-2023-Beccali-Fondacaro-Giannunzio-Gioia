package it.polimi.ingsw.model;
/**
 * This enum defines tile's colors.
 */
public enum Color {
    //Color end string, color reset
    RESET("\033[0m"),
    CLEAR("\033[H\033[2J"),
    // Colors for the tiles
    GREEN("\033[38;5;28m"),
    BLUE("\033[0;34m"),
    YELLOW("\033[0;33m"),
    PURPLE("\033[0;35m"),
    WHITE("\033[0;37m"),
    CYAN("\033[0;36m"),
    BLACK("\033[0;30m"),

    // Colors for the shelf and the board
    YELLOW_BOLD("\033[1;33m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    /**
     * Parse to string a color (the color of the tiles).
     * */
    public String parseToString(Color color) {
        String colorString = null;
        switch(color) {
            case YELLOW:
                colorString = "YELLOW";
                break;
            case CYAN:
                colorString = "CYAN";
                break;
            case GREEN:
                colorString = "GREEN";
                break;
            case WHITE:
                colorString = "WHITE";
                break;
            case PURPLE:
                colorString = "PURPLE";
                break;
            case BLUE:
                colorString = "BLUE";
                break;
            case BLACK:
                colorString = "BLACK";
        }
        return colorString;
    }
}
