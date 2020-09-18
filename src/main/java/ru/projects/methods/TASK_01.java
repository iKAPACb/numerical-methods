package ru.projects.methods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TASK_01 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите размер матрицы / кол-во уравнений: ");
        //размер массива
        int numberOfEquation = Integer.parseInt(scan.nextLine());
        //инициализация
        double[][] matrix = new double[numberOfEquation][numberOfEquation];
        double[] rightAfterEqualSign = new double[numberOfEquation];
        double[] result = new double[matrix.length];

        for(int i = 0; i < numberOfEquation; i++){
            System.out.print("Введите коэфициэнты " + i + " строки: ");
            for(int j = 0; j < numberOfEquation; j++){
                matrix[i][j] = scan.nextDouble();
            }
        }

        System.out.print("Введите значения после знака равно: ");
        for(int i = 0; i < numberOfEquation; i++){
            rightAfterEqualSign[i] = scan.nextDouble();
        }


        System.out.println("ДО:");
        printArray(matrix);
        //приведение к диагональной
        for(int diagonal = 0; diagonal < matrix.length - 1; diagonal++){
            int row = diagonal + 1;
            while (row < matrix.length){
                double coefficient = matrix[row][diagonal] / matrix[diagonal][diagonal];
                for (int i = diagonal; i < matrix.length; i ++){
                    matrix[row][i] -= matrix[diagonal][i] * coefficient;
                }
                row++;
            }
        }

        //нахождение решений
        for(int i = matrix.length - 1; i >= 0; i--){
            for(int j = 0; j < matrix.length; j++){
                if (i != j){
                    rightAfterEqualSign[i] -= result[j] * matrix [i][j];
                }
            }
            result[i] = rightAfterEqualSign[i] / matrix [i][i];
        }

        System.out.println("ПОСЛЕ:");
        printArray(matrix);
        printResult(result);
    }

    static void printResult(double[] array){
        for(int i = 1; i<array.length + 1;i++){
            System.out.printf(i + " корень: %.3f\n", array[i - 1]);
        }
    }

    static void printArray(double[][] array){
        for(double[] row : array){
            for(double item : row){
                System.out.printf("%.2f ",item);
            }
            System.out.println();
        }
    }

}
