package tictactoe;

import java.util.Random;

public class TicTacToe {
    // -2 - Error!; -1 ,0 ,1 , 2 - not Error
    char[][] pole;
    private boolean error;
    static String firstPlayerLevel;
    static String secondPlayerLevel;

    TicTacToe(int[] input) {
        //1 - AI only; 2 - user only; 3 - AI user; 4 - user AI;
        this.pole = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pole[i][j] = '_';
            }
        }

        hodOfX = true;
        firstPlayerLevel = Level.getByValue(input[0]);
        secondPlayerLevel = Level.getByValue(input[1]);
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    static boolean hodOfX;
    static final String coordinate = "Enter the coordinates: > ";
    static final String moveByPc = "Making move level ";
    public void EnterTheCoordinates() {
        print(this.pole);
        if (exam(this.pole) == -1&& (firstPlayerLevel.equals(Level.USER.getText()) && hodOfX || secondPlayerLevel.equals(Level.USER.getText()) &&!hodOfX)) {
            System.out.print(coordinate);
        } else if (exam(this.pole) == 0) {
            System.out.print("Draw");
        } else if (exam(this.pole) == 1) {
            System.out.print("X wins");
        } else if(exam(this.pole) == 2){
            System.out.print("O wins");
        }else{

            if(hodOfX) {
                System.out.println(moveByPc+"\""+firstPlayerLevel+"\"");
                setPoleByPc(Level.getValueByText(firstPlayerLevel));
            }else{
                System.out.println(moveByPc+"\""+secondPlayerLevel+"\"");
                setPoleByPc(Level.getValueByText(secondPlayerLevel));
            }
        }

    }

    public char[][] getPole() {
        return pole;
    }

    public boolean isNeedInput(){
        if(hodOfX && firstPlayerLevel.equals( Level.USER.getText())){
            return true;
        }else if(!hodOfX && secondPlayerLevel.equals(Level.USER.getText())){
            return true;
        }else {
            return false;
        }
    }

    public void print(char[][] pole) {
        System.out.print("---------");
        for (int i = 0; i < pole.length; i++) {
            System.out.println();
            for (int j = 0; j < pole[i].length; j++) {
                if (j == 0 ) {
                    System.out.print("| ");
                }
                System.out.printf("%c ", pole[i][j] == '_' ? ' ' : pole[i][j]);
                if(j == pole[i].length - 1){
                    System.out.print("|");
                }
            }

        }
        System.out.println();
        System.out.println("---------");
    }

    public static int countOfSymbols(char[][] pole) {
        // -1 - End of game; 0 - equals; 1 - X > O; 2 - O > X
        int[] count = new int[3];
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[i].length; j++) {
                if (pole[i][j] == 'X') {
                    ++count[0];
                } else if (pole[i][j] == 'O') {
                    ++count[1];
                } else if (pole[i][j] == '_') {
                    ++count[2];
                }
            }
        }

        if (count[2] == 0) {
            return -1;
        } else if (count[0] == count[1]) {
            return 0;
        } else if (count[0] > count[1]) {
            return 1;
        } else {
            return 2;
        }
    }

    public static int exam(char[][] pole) {
        // -1 - Game not ends; 0 - tie; 1 - X wins; 2 - O wins
        for (int i = 0; i < pole.length; i++) {
            if ((pole[i][0] == pole[i][1] && pole[i][1] == pole[i][2] && (pole[i][1] == 'X' || pole[i][1] == 'O')) || (pole[0][i] == pole[1][i] && pole[1][i] == pole[2][i] && (pole[1][i] == 'X' || pole[1][i] == 'O'))) {
                return pole[i][1] == 'X' ? 1 : 2;
            }
        }
        if (pole[0][0] == pole[1][1] && pole[1][1] == pole[2][2] && (pole[1][1] == 'X' || pole[1][1] == 'O') || pole[0][2] == pole[1][1] && pole[1][1] == pole[2][0] && (pole[1][1] == 'X' || pole[1][1] == 'O')) {
            return pole[1][1] == 'X' ? 1 : 2;
        } else {
            if (countOfSymbols(pole) == -1) {
                return 0;
            }
        }
        return -1;
    }

    private boolean isAllSymbolsGood(String symbols) {
        for (int i = 0; i < symbols.length(); i++) {
            if (!(symbols.charAt(i) == 'X' || symbols.charAt(i) == 'O' || symbols.charAt(i) == '_')) {
                return false;
            }
        }
        return true;
    }

    public void setPoleByPc(int num){
        if(num == Level.EASY.getValue()) {
            Random rand = new Random();
            String x;
            do {
                x = String.format("%d %d", rand.nextInt(3) + 1, rand.nextInt(3) + 1);
            } while (setPole(x) != 0);
        }else if(num == Level.MEDIUM.getValue()){

            if(!canWin().equals("false") || !canBlock().equals("false")){
                if(!canWin().equals("false")){
                    setPole(canWin());
                }else {
                    setPole(canBlock());
                }

            }else {
                Random rand = new Random();
                String x;
                do {

                    x = String.format("%d %d", rand.nextInt(3) + 1, rand.nextInt(3) + 1);
                } while (setPole(x) != 0);
            }
        } else if (num == Level.HARD.getValue()){
            MiniMax coordinates = new MiniMax();
            setPole(coordinates.getMiniMax(pole,hodOfX ? 'X': 'O'));

        }
    }
    private String canWin(){
        String can = "false";
        char mark = (hodOfX) ? 'X' : 'O';
        int x = 0, y = 0, x1 = 2, y1 = 2;
        while (x != 3 || y != 2 || x1 != -1 || y1 != 0){

            if(pole[x][y] == pole[x1][y1] && pole[x][y] == mark && pole[1][1] == '_'){
                return String.format("%d %d",2,2);
            }else if(pole[x][y] == pole[1][1] && pole[1][1] == mark && pole[x1][y1] == '_'){
                return String.format("%d %d",x1+1,y1+1);
            }else if(pole[x1][y1] == pole[1][1] && pole[1][1] == mark && pole[x][y] == '_'){
                return String.format("%d %d",x+1,y+1);
            }

            if(y == 2){
                x++;
            }else{
                y++;
            }
            if(y1==0){
                x1--;
            }else{
                y1--;
            }
        }
        return can;
    }
    private String canBlock(){
        String can = "false";
        char mark = (hodOfX) ? 'O' : 'X';
        int x = 0, y = 0, x1 = 2, y1 = 2;
        while (x != 3 || y != 2 || x1 != -1 || y1 != 0){

            if(pole[x][y] == pole[x1][y1] && pole[x][y] == mark && pole[1][1] == '_'){
                return String.format("%d %d",2,2);
            }else if(pole[x][y] == pole[1][1] && pole[1][1] == mark && pole[x1][y1] == '_'){
                return String.format("%d %d",x1+1,y1+1);
            }else if(pole[x1][y1] == pole[1][1] && pole[1][1] == mark && pole[x][y] == '_'){
                return String.format("%d %d",x+1,y+1);
            }

            if(y == 2){
                x++;
            }else{
                y++;
            }
            if(y1==0){
                x1--;
            }else{
                y1--;
            }
        }
        return can;
    }
    public char[][] CopyPole(){
        char[][] copiedPole = new char[3][3];
        for(int i = 0; i < pole.length; i ++) {
            for (int j = 0; j < pole[i].length; j ++) {
                copiedPole[i][j] = pole[i][j];
            }
        }
        return copiedPole;
    }

    public static int exe(char[][] pole){
        //-1 - not ends; 0 - tie; 1 - x wins; 2 - o wins;
        char mark = (hodOfX) ? 'X' : 'O';
        int x = 0, y = 0, x1 = 2, y1 = 2;
        while (x != 3 || y != 2 || x1 != -1 || y1 != 0){

            if(pole[x][y] == pole[x1][y1] && pole[1][1] == pole[x][y] && pole[1][1] != '_'){
                    return pole[1][1] == 'X' ? 1 : 2;
            }

            if(y == 2){
                x++;
            }else{
                y++;
            }
            if(y1==0){
                x1--;
            }else{
                y1--;
            }
        }
        for (int i = 0; i < pole.length; i++){
            for(int j = 0; j < pole[0].length; j++){
                if(pole[i][j] == '_'){
                    return -1;
                }
            }
        }
        return 0;
    }
    public int setPole(String text) {
        // -3 - cell is occupied; -2 - Coordinates not in range; -1 - text entered; 0 - all is good;
        int x, y;
        try {
            text = text.replace(" ", "");
            x = Integer.parseInt(text.substring(0, 1));
            y = Integer.parseInt(text.substring(1));
        } catch (Exception e) {
            if((hodOfX&&firstPlayerLevel.equals(Level.USER.getText())) || (!hodOfX && secondPlayerLevel.equals(Level.USER.getText())) ){
                System.out.println("You should enter numbers!");
                System.out.print(coordinate);
            }
            return -1;
        }
        if (x > 3 || x < 1 || y > 3 || y < 1) {
            if((hodOfX&&firstPlayerLevel.equals(Level.USER.getText())) || (!hodOfX && secondPlayerLevel.equals(Level.USER.getText())) ) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.print(coordinate);
            }
            return -2;
        } else if (pole[x - 1][y - 1] != '_') {
            if((hodOfX&&firstPlayerLevel.equals(Level.USER.getText())) || (!hodOfX && secondPlayerLevel.equals(Level.USER.getText())) ) {
                System.out.println("This cell is occupied! Choose another one!");
                System.out.print(coordinate);
            }
            return -3;
        } else {
            pole[x - 1][y - 1] = hodOfX ? 'X' : 'O';
            hodOfX = !hodOfX;
            return 0;
        }

    }
    public static char[][] setPolee(String text,char[][] pole,char mark) {
        // -3 - cell is occupied; -2 - Coordinates not in range; -1 - text entered; 0 - all is good;
        int x, y;
        char[][] polee = new char[3][3];

            text = text.replace(" ", "");
            x = Integer.parseInt(text.substring(0, 1));
            y = Integer.parseInt(text.substring(1));
        for(int i = 0; i < pole.length; i ++){
            for(int j = 0; j < pole[i].length; j++){
                if(i==x - 1 && j == y - 1){
                    polee[i][j] = mark;
                }else{
                    polee[i][j] = pole[i][j];
                }
            }
        }
        return polee;
    }
}
class MiniMax {
    private int score;
    private String coordinates;

    public String getMiniMax(char[][] pole, char mark) {
        // -1 - Game not ends; 0 - tie; 1 - X wins; 2 - O wins
        int maxFound = -10_000_000; // 0 - in coords lose coordinates; 1 - Tie; 2 - win coordinates;
        String coords = null;
        boolean isHodOfX = mark == 'X' ? true : false;
        for (int i = 0 ; i < pole.length; i ++) {
            for (int j = 0; j < pole[i].length; j ++) {
                char[][] newPole;
                int chislo = mark == 'X' ? 1: 2;
                if(pole[i][j] == '_') {
                    newPole = TicTacToe.setPolee(String.format("%d %d", i + 1, j + 1), pole, isHodOfX? 'X': 'O');

                    if (TicTacToe.exe(newPole) != -1) {
                        if (TicTacToe.exe(newPole) == chislo) {
                            coords = String.format("%d %d", i + 1, j + 1);
                            return coords;
                        } else if (TicTacToe.exe(newPole) == 0) {
                            maxFound = 1;
                            coords = String.format("%d %d", i + 1, j + 1);
                        } else if (maxFound < 0) {
                            coords = String.format("%d %d", i + 1, j + 1);
                        }
                    } else {
                        int newMax = getMiniMaxRecoursion(newPole, mark,!isHodOfX);
                        if (newMax > maxFound) {
                            maxFound = newMax;
                            coords = String.format("%d %d", i + 1, j + 1);
                        }
                    }
                }
            }
        }
        return coords;
    }

    private int getMiniMaxRecoursion(char[][] pole, char mark, boolean isHodOfX){
        int maxFound = 0;
        boolean lose = true;
                            // 0 - in coords lose coordinates; 1 - Tie; 2 - win coordinates;
        for (int i = 0 ; i < pole.length; i ++) {
            for (int j = 0; j < pole[i].length; j ++) {
                char[][] newPole = new char[3][3];

                if(pole[i][j] == '_') {

                    newPole = TicTacToe.setPolee(String.format("%d %d", i + 1, j + 1), pole, (isHodOfX ? 'X' : 'O'));
                    if (TicTacToe.exe(newPole) != -1) {
                        if (TicTacToe.exe(newPole) == ((mark == 'X') ? 1 : 2)) {
                            maxFound += 10;
                        } else if (TicTacToe.exe(newPole) == 0) {
                            maxFound += 0;
                        } else {
                            maxFound+= -10;
                        }
                    } else {
                        int max = getMiniMaxRecoursion(newPole, mark,!isHodOfX);
                        maxFound += max;
                    }
                }
            }
        }
        return maxFound;
    }
    public String getCoordinates() {
        return coordinates;
    }
}