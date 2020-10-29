package ru.projects.methods.TASK_04;


import java.util.Arrays;
import java.util.Scanner;

public class RotationMethod {
    public static void main(String[] args) {
        double[][] Amatrix;
        double accuracy;
        int iteration = 0;
        //Объявление переменных
        InputVariables.scan();
        Amatrix = InputVariables.getAmatrix();
        accuracy = InputVariables.getAccuracy();
        double[][] Umatrix = new double[Amatrix.length][Amatrix.length];
        double[][] UTmatrix = new double[Amatrix.length][Amatrix.length];
        double E;
        do {
            // Создаем единичную матрицу
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    if(i==j){
                        Umatrix[i][j] = 1;
                    }
                    else{
                        Umatrix[i][j]=0;
                    }

                }
            }
            // Ищем максимальный Элемент, находящийся выше побочной диагонали
            double MaxElement = Amatrix[0][1];
            int First=0;
            int Second=0;
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    if(i<j){
                        if (MaxElement<Amatrix[i][j]){
                            MaxElement = Amatrix[i][j];
                            First = i;
                            Second = j;
                        }
                    }
                }
            }
            // Находим угол Fi и cosFi, sinFi
            double Fi = (1.0 / 2) * Math.atan(2*MaxElement/(Amatrix[First][First]-Amatrix[Second][Second]));
            double SinFi = Math.sin(Fi);
            double CosFI = Math.cos(Fi);
            // Получаем матрицу U
            Umatrix[First][First] = CosFI;
            Umatrix[Second][Second] = CosFI;
            Umatrix[First][Second] = -SinFi;
            Umatrix[Second][First] = SinFi;
            // Ищем погрешность
            double Sum = 0;
            for (int i = 0; i <Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    if(i>j){
                        Sum += Math.pow(Amatrix[i][j],2);
                    }
                }
            }
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    UTmatrix[i][j]=Umatrix[j][i];
                }
            }
            // Точность
            E = Math.pow(Sum,0.5);
            System.out.println("Точность = "+E);
            System.out.println("A матрица");
            System.out.println("U матрица");
            for (int i = 0; i < Umatrix.length; i++) {
                for (int j = 0; j < Umatrix.length; j++) {
                    System.out.printf("%.4f ",Umatrix[i][j]);
                }
                System.out.println();
            }
            System.out.println("UT матрица");
            for (int i = 0; i < UTmatrix.length; i++) {
                for (int j = 0; j < UTmatrix.length; j++) {
                    System.out.printf("%.4f ",UTmatrix[i][j]);
                }
                System.out.println();
            }
            double [][] Res ={{0,0,0},{0,0,0},{0,0,0}} ;
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    for (int k = 0; k < Amatrix.length; k++) {
                        Res[i][j] += Umatrix[i][k] * UTmatrix[k][j];
                    }
                }
            }
            double [][] Res2 = {{0,0,0},{0,0,0},{0,0,0}} ;
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    for (int k = 0; k < Amatrix.length; k++) {
                        Res2[i][j] += Res[i][k] * Amatrix[k][j];
                    }
                }
            }
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    Amatrix[i][j] = Res2[i][j];
                }
            }
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    System.out.printf("%.4f ",Res2[i][j]);
                }
                System.out.println();
            }
            break;
        }while(E>accuracy);
    }
}
