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
        double[][] Ures = new double[Amatrix.length][Amatrix.length];
        double E;
        do {
            // Создаем единичную матрицу
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.lenth; j++) {
                    if(i==j){
                        Umatrix[i][j] = 1;
                    }
                    else{
                        Umatrix[i][j]=0;
                    }
                }
            }
            // Ищем максимальный Элемент, находящийся выше побочной диагонали
            double MaxElement=0;
            int First=0;
            int Second=0;
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    if(i<j & MaxElement<Amatrix[i][j]){
                            MaxElement = Amatrix[i][j];
                            First = i;
                            Second = j;
                        }
                    }
                }
            System.out.println(MaxElement);
            System.out.println("First = "+First+" Second "+Second);
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
                    UTmatrix[i][j]=Umatrix[i][j];
                }
            }
            //Транспонирование матрицы
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = i+1; j < Amatrix.length; j++) {
                    double temp = UTmatrix[i][j];
                    UTmatrix[i][j] = UTmatrix[j][i];
                    UTmatrix[j][i] = temp;
                }
            }
            // Точность
            E = Math.pow(Sum,0.5);
            System.out.println("Точность = "+E);
            System.out.println("A матрица");
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    System.out.printf("%.4f ",Amatrix[i][j]);
                }
                System.out.println();
            }
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
            //Умножение матриц
            double [][] Res ={{0,0,0},{0,0,0},{0,0,0}} ;
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    for (int k = 0; k < Amatrix.length; k++) {
                        Res[i][j] += UTmatrix[i][k] * Amatrix[k][j];
                    }
                }
            }
            double [][] Res2 = {{0,0,0},{0,0,0},{0,0,0}} ;
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    for (int k = 0; k < Amatrix.length; k++) {
                        Res2[i][j] += Res[i][k] * Umatrix[k][j];
                    }
                }
            }
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    Amatrix[i][j] = Res2[i][j];
                }
            }
            //Полученная А матрица
            System.out.println("Ares");
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    System.out.printf("%.4f ",Amatrix[i][j]);
                }
                System.out.println();
            }
            System.out.println("Ures");
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    System.out.printf("%.4f ",Ures[i][j]);
                }
                System.out.println();
            }
            if (iteration == 0){
                for (int i = 0; i <Amatrix.length ; i++) {
                    for (int j = 0; j < Amatrix.length; j++) {
                        Ures[i][j]=Umatrix[i][j];
                    }
                }
            }else {
                for (int i = 0; i < Amatrix.length; i++) {
                    for (int j = 0; j < Amatrix.length; j++) {
                        for (int k = 0; k < Amatrix.length; k++) {
                            Ures[i][j] += Ures[i][k] * Umatrix[k][j];
                        }
                    }
                }
            }
            System.out.println("Ures");
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    System.out.printf("%.4f ",Ures[i][j]);
                }
                System.out.println();
            }
            iteration++;

        }while(E>accuracy);
    }
}
