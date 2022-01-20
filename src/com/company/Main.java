package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();

        Scanner scanner = new Scanner(System.in);
        while(!game.isGameOver()){
            game.board.printBoard();
            System.out.println("Make a new guess");
            String input =scanner.nextLine();
            if (PlayerInput.validateInput(input)){
                game.makeAGuess(PlayerInput.inputTranslator(input));
            }
            game.setGameOver(game.checkIfGameOver());
        }
    }



}
