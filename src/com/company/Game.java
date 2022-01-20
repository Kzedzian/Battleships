package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    public Board board;
    private List<GameField[]> ships = new ArrayList<>();
    private boolean gameOver = false;
    private int shipsSinked = 0;

    static{
        System.out.println("Welcome to the Battleships! \nYour goal is to sink three ships");
    }
    public Game() {
        this.board = new Board();
        setShip(4);
        setShip(4);
        setShip(5);
        System.out.println("Ready to go!");
    }

    public GameField[] setShip(int length){

        GameField[] result = new GameField[length];

        Random random = new Random();
        int randomNumber = random.nextInt();
        boolean placeHorizontally = false;
        if(randomNumber %2 ==0){
           placeHorizontally = true;
        }
        int[] coords = checkIfPlaceAvailable(placeHorizontally,length);

        if(placeHorizontally) {
            for(int k=0; k<length; k++) {
                board.GameBoard[coords[0]][coords[1]+k].setEmpty(false);
                result[k] = board.GameBoard[coords[0]][coords[1]+k];
            }

        }else {
            for(int k=0; k<length; k++) {
                board.GameBoard[coords[0]+k][coords[1]].setEmpty(false);
                result[k] = board.GameBoard[coords[0]+k][coords[1]];
            }

        }
        System.out.println("Placed ship " + length +" fields long");

        ships.add(result);
        return result;
    }

    private int[] checkIfPlaceAvailable(boolean placeHorizontally, int length) {

        int[] result = new int[2];
        Random random = new Random();
        boolean readyToPlace = false;

        while (!readyToPlace) {
            int i = random.nextInt(10 - length);
            int j = random.nextInt(10 - length);

            int startJ = j;
            if(j-1 >=0){
                startJ = j-1;
            }
            int endJ = j;
            int startI = i;
            if(i-1 >=0){
                startI = i-1;
            }
            int endI = i;

            if (placeHorizontally)
            {
                endJ = j+length;
                if(j+length+1<10){
                    endJ = j+length+1;
                }
                if (i + 1 < 10) {
                        endI = i + 1;
                    }
            }else{
                if(j+1 < 10){
                    endJ +=1;
                }
                endI = i+length;
                if (i+length+1 <10){
                    endI +=1;
                }
            }

            int collisionCounter = 0;
            for (int k = startI; k <= endI; k++) {
                for (int m = startJ; m <= endJ; m++) {
                    if (!board.GameBoard[k][m].isEmpty()) {
                        collisionCounter++;
                    }
                }
            }

            if(collisionCounter == 0){
                readyToPlace = true;
                result[0] = i;
                result[1] = j;
            }
        }

        return result;
    }

    public void makeAGuess(int [] input){
        int coordA = input[0];
        int coordB = input[1];
        if(board.getGameBoard()[coordA][coordB].isEmpty()){
            System.out.println("Missed!");
            board.getGameBoard()[coordA][coordB].setSymbol(Symbol.X);
        }
        else{
            System.out.println("Hit a ship!");
            board.getGameBoard()[coordA][coordB].setSymbol(Symbol.H);
            board.getGameBoard()[coordA][coordB].setHit(true);

            for(GameField[] ship : ships){
                for (GameField gameField : ship) {
                    if (gameField.equals(board.getGameBoard()[coordA][coordB])) {
                        List<GameField> collect = Arrays.stream(ship).filter(o -> !o.isHit()).collect(Collectors.toList());
                        if (collect.isEmpty()) {
                            System.out.println("Ship sinked!");
                            shipsSinked++;

                        }
                    }
                }

            }
        }
    }

    public boolean checkIfGameOver(){
        if(shipsSinked == 3){
            System.out.println("Congratulations, you won!");
            return true;
        }else{
            return false;
        }
    }



    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getShipsSinked() {
        return shipsSinked;
    }

    public void setShipsSinked(int shipsSinked) {
        this.shipsSinked = shipsSinked;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
