package ru.gb.java1.lesson4;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp {
    public static char[][] MAP;
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (isWin(DOT_X)) {
                System.out.println("Вы победили");
                break;
            }

            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
            randomTurn();
            printMap();
            if (isWin(DOT_O)) {
                System.out.println("Победил рандом");
                break;
            }

            if (isFull()) {
                System.out.println("Ничья");
                break;
            }

        }

    }

    private static boolean isWin(char symbol) {

        int winDiagonal1 = 0;      //Для проверки первой диагонали
        int winDiagonal2 = 0;   //Для проверки второй диагонали

        for (int i = 0; i < SIZE; i++) {
            int winVertical = 0;    //Для проверки вертикалей
            int winGorizontal = 0;  //Для проверки горизонталей

            for (int j = 0; j < SIZE; j++) {

                if (i == j) {
                    if (MAP[i][j] == symbol) {
                        winDiagonal1 = winDiagonal1 + 1;
                    }
                }
                if (j == SIZE - 1 - i) {
                    if (MAP[i][j] == symbol) {
                        winDiagonal2 = winDiagonal2 + 1;
                    }
                }
                if (MAP[i][j] == symbol) {
                    winGorizontal = winGorizontal + 1;
                }
                if (MAP[j][i] == symbol) {
                    winVertical = winVertical + 1;
                }
            }
            if (winDiagonal1 == DOTS_TO_WIN) return true;
            if (winDiagonal2 == DOTS_TO_WIN) return true;
            if (winGorizontal == DOTS_TO_WIN) return true;
            if (winVertical == DOTS_TO_WIN) return true;
        }

        return false;
    }

    private static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void randomTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(SIZE - 1);
            y = random.nextInt(SIZE - 1);
        } while (!isTurnValid(x, y));
        MAP[y][x] = DOT_O;
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isTurnValid(x, y));
        MAP[y][x] = DOT_X;
    }

    private static boolean isTurnValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (MAP[y][x] == DOT_EMPTY) {
            return true;
        }
        return false;
    }


    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initMap() {
        MAP = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;
            }
        }
    }


}
