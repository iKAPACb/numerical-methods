package ru.projects.methods.TASK_02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class RunMethod {

    private static int numberOfEquation;
    private static double[][] matrix;
    private static double[] vector;

    private static double[] alfa;
    private static double[] beta;
    private static double[] solution;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите размер матрицы / кол-во уравнений: ");
        //размер массива
        numberOfEquation = Integer.parseInt(scan.nextLine());
        //инициализация
        matrix = new double[numberOfEquation][numberOfEquation];
        vector = new double[numberOfEquation];

        alfa = new double[numberOfEquation];
        beta = new double[numberOfEquation];
        solution = new double[numberOfEquation];
        //матрица

        for (int i = 0; i < numberOfEquation; i++) {
            System.out.print("Введите коэфициэнты " + i + " строки: ");
            for (int j = 0; j < numberOfEquation; j++) {
                matrix[i][j] = scan.nextDouble();
            }
        }
        //вектор
        System.out.print("Введите свободные коэффициенты: ");
        for (int i = 0; i < numberOfEquation; i++) {
            vector[i] = scan.nextDouble();
        }

        alfaKoef();
        betaKoef();
        solution();
        System.out.println(Arrays.toString(solution));

    }
    private static void alfaKoef(){
        alfa[0] = - ( matrix[0][1] / matrix [0][0]);
        alfa[matrix.length - 1] = 0;
        for (int i = 1; i < matrix.length - 1; i++){
            //            C                  B              A
            alfa[i] = matrix[i][i + 1] / (matrix[i][i] - matrix[i][i - 1] * alfa[i - 1]);
        }
    }

    private static void betaKoef(){
        beta[0] = vector[0] / matrix [0][0];
        for (int i = 1; i < matrix.length ;i++){
            //           b             A                                 B               A
            beta[i] = (vector[i] - matrix[i][i - 1] * beta[i -1]) / (matrix[i][i] + matrix[i][i - 1] * alfa[i -1]);
        }
    }

    private static void solution(){
        for (int i = matrix.length - 1; i > 0; i --){
            solution[i] = (vector[i] + matrix[i][i - 1] * beta[i - 1]) / (matrix[i][i] - matrix[i][i - 1] * alfa[i - 1]);
        }
        solution[0] = alfa[0] * solution[1] + beta [0];
    }
}
