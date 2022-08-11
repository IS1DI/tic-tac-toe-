package tictactoe;


public enum Level {
    USER(1, "user"),
    EASY(2, "easy"),
    MEDIUM(3, "medium"),
    HARD(4, "hard");
    private int value;
    private String text;

    Level(int value, String text) {
        this.value = value;
        this.text = text;
    }
    public static String getByValue(int value){
        for(Level level : Level.values()){
            if(level.value == value){
                return level.text;
            }
        }
        return "error";
    }
    public static int getValueByText(String text){
        for(Level level : Level.values()){
            if(level.text.equals(text)){
                return level.value;
            }
        }
        return 0;
    }
    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
