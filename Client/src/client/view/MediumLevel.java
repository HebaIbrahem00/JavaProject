/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author pc
 */
public class MediumLevel {
    static ArrayList<Integer> PlayerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> CpuPositions = new ArrayList<Integer>();
    char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
    {'-', '+', '-', '+', '-'},
    {' ', '|', ' ', '|', ' '},
    {'-', '+', ' ', '+', '-'},
    {' ', '|', ' ', '|', ' '}};

    //funcction to check whether the position entered by the user is free or not
    public boolean checkPlayerPos(int position) {
        boolean status = true;
        int playerpos = position;
        if (PlayerPositions.contains(playerpos) || CpuPositions.contains(playerpos)) {
            System.out.println("Position taken! Enter a correct position");
            status = false;
        }
        if (status) {
            placePiece(gameBoard, playerpos, "player");
            printGameBoard(gameBoard);
        }
        return status;
    }
    
    //function to generate a random position for the cpu while checking this position is free or not
    public int CpuTurn() {
        Random rand = new Random();
        int CpuPos = rand.nextInt(9) + 1;
        if(PlayerPositions.contains(CpuPos) || CpuPositions.contains(CpuPos)){
        while (PlayerPositions.contains(CpuPos) || CpuPositions.contains(CpuPos)) {
            CpuPos = rand.nextInt(9) + 1;    
        }}
        else if((gameBoard[0][0] == 'X' && gameBoard[0][2] == 'X') || (gameBoard[0][2] == 'X' && gameBoard[0][0] == 'X'))
        {
            CpuPos=3;
        }
        else if((gameBoard[0][2] == 'X' && gameBoard[0][4] == 'X')||(gameBoard[0][4] == 'X' &&gameBoard[0][2] == 'X' ))
        {
            CpuPos=1;
        }
        else if((gameBoard[0][0] == 'X' && gameBoard[0][4] == 'X') || ( gameBoard[0][4] == 'X' && gameBoard[0][0] == 'X'))
        {
            CpuPos=2;
        }
        else if((gameBoard[2][0] == 'X' && gameBoard[2][2] == 'X') || (gameBoard[2][2] == 'X' && gameBoard[2][0] == 'X')){
            CpuPos=6;
        }
        else if((gameBoard[2][2] == 'X' && gameBoard[2][4] == 'X')||( gameBoard[2][4] == 'X' &&gameBoard[2][2] == 'X' )){
            CpuPos=4;
        }
        else if((gameBoard[2][0] == 'X' && gameBoard[2][4] == 'X')||(gameBoard[2][4] == 'X' && gameBoard[2][0] == 'X')){
            CpuPos=5;
        }
        else if((gameBoard[4][0] == 'X' && gameBoard[4][2] == 'X') || (gameBoard[4][2] == 'X' && gameBoard[4][0] == 'X')){
            CpuPos=9;
        }
        else  if((gameBoard[4][2] == 'X' && gameBoard[4][4] == 'X')||(gameBoard[4][4] == 'X'&&gameBoard[4][2] == 'X')){
            CpuPos=7;
        }
        else if((gameBoard[4][0] == 'X' && gameBoard[4][4] == 'X')||(gameBoard[4][4] == 'X'&&gameBoard[4][0] == 'X')){
            CpuPos=8;
        }
        else if((gameBoard[0][0] == 'X' && gameBoard[2][0] == 'X')||(gameBoard[2][0] == 'X' && gameBoard[0][0] == 'X'))
        {
            CpuPos=7;
        }
        else if((gameBoard[2][0] == 'X' && gameBoard[4][0] == 'X')||(gameBoard[4][0] == 'X' && gameBoard[2][0] == 'X'))
        {
            CpuPos=1;
        }
        else if((gameBoard[0][0] == 'X' && gameBoard[4][0] == 'X')||( gameBoard[4][0] == 'X' &&gameBoard[0][0] == 'X'))
        {
            CpuPos=4;
        }
        else if((gameBoard[0][2] == 'X' && gameBoard[2][2] == 'X')||(gameBoard[2][2] == 'X' && gameBoard[0][2] == 'X'))
        {
            CpuPos=8;
        }
        else if((gameBoard[2][2] == 'X' && gameBoard[4][2] == 'X')||(gameBoard[4][2] == 'X' && gameBoard[2][2] == 'X'))
        {
            CpuPos=2;
        }
        else  if((gameBoard[0][2] == 'X' && gameBoard[4][2] == 'X')||(gameBoard[4][2] == 'X' &&gameBoard[0][2] == 'X' ))
        {
            CpuPos=5;
        }
        else if((gameBoard[0][4] == 'X' && gameBoard[2][4] == 'X')||( gameBoard[2][4] == 'X' && gameBoard[0][4] == 'X'))
        {
            CpuPos=9;
        }
        else if((gameBoard[2][4] == 'X' && gameBoard[4][4] == 'X')||(gameBoard[4][4] == 'X'&&gameBoard[2][4] == 'X'))
        {
            CpuPos=3;
        }
        else if((gameBoard[0][4] == 'X' && gameBoard[4][4] == 'X')||(gameBoard[4][4] == 'X' && gameBoard[0][4] == 'X'))
        {
            CpuPos=6;
        }
        else if((gameBoard[0][4] == 'X' && gameBoard[2][2] == 'X')||(gameBoard[2][2] == 'X' && gameBoard[0][4] == 'X'))
        {
            CpuPos=7;
        }
        else if((gameBoard[2][2] == 'X' && gameBoard[4][0] == 'X')||(gameBoard[4][0] == 'X' && gameBoard[2][2] == 'X'))
        {
            CpuPos=3;
        } 
        else if((gameBoard[0][4] == 'X' && gameBoard[4][0] == 'X')||(gameBoard[4][0] == 'X' && gameBoard[0][4] == 'X'))
        {
            CpuPos=5;
        } 
        else if((gameBoard[0][0] == 'X' && gameBoard[2][2] == 'X')||(gameBoard[2][2] == 'X' && gameBoard[0][0] == 'X'))
        {
            CpuPos=9;
        } 
        else if((gameBoard[2][2] == 'X' && gameBoard[4][4] == 'X')||(gameBoard[4][4] == 'X' &&gameBoard[2][2] == 'X'))
        {
            CpuPos=1;
        } 
        else if((gameBoard[0][0] == 'X' && gameBoard[4][4] == 'X')||(gameBoard[4][4] == 'X' &&gameBoard[0][0] == 'X' ))
        {
            CpuPos=5;
        } 
        placePiece(gameBoard, CpuPos, "cpu");
        printGameBoard(gameBoard);
        return CpuPos;
    }
    
    //function to check if anybody won or a tie took place
    public String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

       int i, j = 0;
        for (List l : winning) {
            if (PlayerPositions.containsAll(l)) {
                for (i = 0; i <= 4; i += 2) {
                    for (j = 0; j <= 4; j += 2) {
                        gameBoard[i][j] = ' ';
                    }
                }
                PlayerPositions.clear();
                CpuPositions.clear();
                return "Congrats you won!";
            } else if (CpuPositions.containsAll(l)) {
                for (i = 0; i <= 4; i += 2) {
                    for (j = 0; j <= 4; j += 2) {
                        gameBoard[i][j] = ' ';
                    }
                }
                PlayerPositions.clear();
                CpuPositions.clear();
                return "you lost, CPU Won!";
            } else if (PlayerPositions.size() + CpuPositions.size() == 9) {
                for (i = 0; i <= 4; i += 2) {
                    for (j = 0; j <= 4; j += 2) {
                        gameBoard[i][j] = ' ';
                    }
                }
                PlayerPositions.clear();
                CpuPositions.clear();
                return "No one won it's a tie";
            }
        }
        return "";
    }
    
    //the rest of the functions are not important to understand I use them to help me keep track of what is happening
    public void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = 'X';
        if (user.equals("player")) {
            symbol = 'X';
            PlayerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            CpuPositions.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }
    
}
