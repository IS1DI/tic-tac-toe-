package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        do {
            int[] input;
            do {
                System.out.print("Input command: > ");
                input = menu(scan.nextLine());
                if (input[0] == -1) {
                    exit = true;
                }
            } while (input[0] == 0&&!exit);
            if(!exit) {
                TicTacToe ticTacToe = new TicTacToe(input);
                while (ticTacToe.exam(ticTacToe.getPole()) == -1) {

                    if (ticTacToe.isNeedInput()) {
                        ticTacToe.EnterTheCoordinates();
                        do {
                            ticTacToe.setPole(scan.nextLine());
                        } while (ticTacToe.isNeedInput());

                    } else {
                        ticTacToe.EnterTheCoordinates();
                    }

                }
                ticTacToe.EnterTheCoordinates();
            }
        } while (!exit);
    }

    public static int[] menu(String input){
        // 0 0 - Bad; 1 - user; 2 - easy; 3 - medium; 4 - hard;


        int[] countOfMsg = new int[5]; // 5
        int firstHod = 0; // -1 - false; 0 - user; 1 - easy; 2 - medium; 3 - hard;
        int secondHod = 0;
        int[] output = new int[2];
        for(String r: input.split(" ")){
            if(r.equals("exit")){
                output[0] = -1;
                return output;
            } else if (r.equals("start")){
                ++countOfMsg[0];
            }else if(r.equals(Level.USER.getText())){
                ++countOfMsg[Level.USER.getValue()];
                secondHod = firstHod != 0 ? Level.USER.getValue() : 0;
                firstHod = firstHod == 0 ? Level.USER.getValue() : firstHod;
            }else if(r.equals(Level.EASY.getText())) {
                ++countOfMsg[Level.EASY.getValue()];
                secondHod = firstHod != 0 ? Level.EASY.getValue() : 0;
                firstHod = firstHod == 0 ? Level.EASY.getValue() : firstHod;
            } else if(r.equals(Level.MEDIUM.getText())){
                ++countOfMsg[Level.MEDIUM.getValue()];
                secondHod = firstHod != 0 ? Level.MEDIUM.getValue() : 0;
                firstHod = firstHod == 0 ? Level.MEDIUM.getValue() : firstHod;
            }else if(r.equals(Level.HARD.getText())){
                ++countOfMsg[Level.HARD.getValue()];
                secondHod = firstHod != 0 ? Level.HARD.getValue() : 0;
                firstHod = firstHod == 0 ? Level.HARD.getValue() : firstHod;
            } else{
                System.out.println("Bad parameters!");
                return output;
            }
        }
        int count = 0;
        for(int i = 1; i < countOfMsg.length; i ++){
            count += countOfMsg[i];
        }
        if(count == 2 && countOfMsg[0] == 1){
            output[0] = firstHod;
            output[1] = secondHod;
            return output;
        }else {
            System.out.println("Bad parameters");
            return output;
        }
    }
}

