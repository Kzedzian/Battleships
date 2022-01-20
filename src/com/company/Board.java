package com.company;

import java.util.Arrays;

public class Board {
    public GameField[][] GameBoard;

    public Board() {
        GameBoard = new GameField[10][10];


        for(int i =0; i <10; i++){
            for(int j=0; j<10; j++){
               GameBoard[i][j] = new GameField();
            }
        }
    }

    public void modifyField(int coord1, int coord2, Symbol symbol){
        GameBoard[coord1][coord2].setSymbol(symbol);
    }

    public GameField[][] getGameBoard() {
        return GameBoard;
    }

    public void setGameBoard(GameField[][] gameBoard) {
        GameBoard = gameBoard;
    }

    public void printBoard(){

        int counter =1;
        for(GameField[] array : getGameBoard()){
            System.out.println(Arrays.toString(array) + " [" +counter +"]");
            counter ++;
        }


        char[] array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.println(Arrays.toString(array));

    }
}
