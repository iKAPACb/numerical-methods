package ru.projects.methods.TASK_04;

import java.util.Scanner;

public class InputVariables {

    private static double[][] Amatrix;
    private static double accuracy;

    public static void scan(){
        Scanner scan = new Scanner(System.in);
        System.out.println("МЕНЮ:\n 1.ПОДСЧЕТ 5ГО ВАРИАНТА\n 2.ВВОД СВОЕЙ МАТРИЦЫ\n Введите 1 или 2\n");
        int choice = scan.nextInt();
        //инициализация
        int numberOfEquation = 0;
        switch (choice) {
            case 1:
                Amatrix = new double[][]{{4,2,1}, {2,5,3}, {1,3,6}};
                accuracy = 0.5;
                break;
            case 2:
                System.out.print("Введите размер матрицы / кол-во уравнений: ");
                //размер массива
                numberOfEquation = scan.nextInt();
                Amatrix = new double[numberOfEquation][numberOfEquation];
                for (int i = 0; i < Amatrix.length; i++) {
                    int Stroka = i + 1;
                    System.out.print("Введите коэфициэнты " + Stroka + " строки: ");
                    for (int j = 0; j < Amatrix.length; j++) {
                        Amatrix[i][j] = scan.nextDouble();
                    }
                }
                //Точность
                System.out.println("Введите точность (Через запятую)");
                accuracy = scan.nextDouble();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public static double[][] getAmatrix() {
        return Amatrix;
    }

    public static double getAccuracy() {
        return accuracy;
    }
}
