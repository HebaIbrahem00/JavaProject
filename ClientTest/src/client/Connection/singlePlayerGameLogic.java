package client.Connection;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package client.Connection;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import static java.util.Collections.list;
//import java.util.List;
//import java.util.Random;
//import java.util.Scanner;
//
///**
// *
// * @author lamis
// */
//public class TicTacToeAI {
//
//    static ArrayList<Integer> PlayerPositions = new ArrayList<Integer>();
//    static ArrayList<Integer> CpuPositions = new ArrayList<Integer>();
//
//    public static void main(String[] args) {
//        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
//        {'-', '+', '-', '+', '-'},
//        {' ', '|', ' ', '|', ' '},
//        {'-', '+', ' ', '+', '-'},
//        {' ', '|', ' ', '|', ' '}};
//
//        printGameBoard(gameBoard);
//        while (true) {
//            Scanner scan = new Scanner(System.in);
//            System.out.println("enter your placement(1-9):");
//            int playerpos = scan.nextInt();
//            while (PlayerPositions.contains(playerpos) || CpuPositions.contains(playerpos)) {
//                System.out.println("Position taken! Enter a correct position");
//                playerpos = scan.nextInt();
//            }
//            placePiece(gameBoard, playerpos, "player");
//            Random rand = new Random();
//            int CpuPos = rand.nextInt(9) + 1;
//            while (PlayerPositions.contains(CpuPos) || CpuPositions.contains(CpuPos)) {
//                System.out.println("Position taken! Enter a correct position");
//                CpuPos = rand.nextInt(9) + 1;;
//            }
//
//            placePiece(gameBoard, CpuPos, "cpu");
//            printGameBoard(gameBoard);
//            String result = checkWinner();
//            System.out.println(result);
//            //while(PlayerPositions.contains(PlayerPos)||CpuPositions.contains(playerPos))
//        }
//    }
//
//    public static void printGameBoard(char[][] gameBoard) {
//        for (char[] row : gameBoard) {
//            for (char c : row) {
//                System.out.print(c);
//            }
//            System.out.println();
//        }
//
//    }
//
//    public static void placePiece(char[][] gameBoard, int pos, String user) {
//        char symbol = 'X';
//        if (user.equals("player")) {
//            symbol = 'X';
//            PlayerPositions.add(pos);
//        } else if (user.equals("cpu")) {
//            symbol = 'O';
//            CpuPositions.add(pos);
//        }
//        switch (pos) {
//            case 1:
//                gameBoard[0][0] = symbol;
//                break;
//            case 2:
//                gameBoard[0][2] = symbol;
//                break;
//            case 3:
//                gameBoard[0][4] = symbol;
//                break;
//            case 4:
//                gameBoard[2][0] = symbol;
//                break;
//            case 5:
//                gameBoard[2][2] = symbol;
//                break;
//            case 6:
//                gameBoard[2][4] = symbol;
//                break;
//            case 7:
//                gameBoard[4][0] = symbol;
//                break;
//            case 8:
//                gameBoard[4][2] = symbol;
//                break;
//            case 9:
//                gameBoard[4][4] = symbol;
//                break;
//        }
//    }
//
//    public static String checkWinner() {
//        List topRow = Arrays.asList(1, 2, 3);
//        List midRow = Arrays.asList(4, 5, 6);
//        List botRow = Arrays.asList(7, 8, 9);
//        List leftCol = Arrays.asList(1, 4, 7);
//        List midCol = Arrays.asList(2, 5, 8);
//        List rightCol = Arrays.asList(3, 6, 9);
//        List cross1 = Arrays.asList(1, 5, 9);
//        List cross2 = Arrays.asList(7, 5, 3);
//
//        List<List> winning = new ArrayList<List>();
//        winning.add(topRow);
//        winning.add(midRow);
//        winning.add(botRow);
//        winning.add(leftCol);
//        winning.add(midCol);
//        winning.add(rightCol);
//        winning.add(cross1);
//        winning.add(cross2);
//
//        for (List l : winning) {
//            if (PlayerPositions.containsAll(l)) {
//                return "Congrats you won!";
//            } else if (CpuPositions.containsAll(l)) {
//                return "you lost, CPU Won!";
//            } else if (PlayerPositions.size() + CpuPositions.size() == 9) {
//                return "No one won it's a tie";
//            }
//        }
//        return "";
//    }
//}
