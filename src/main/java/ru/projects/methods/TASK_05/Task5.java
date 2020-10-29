package ru.projects.methods.TASK_05;

import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Решение задачи:\n 1.Методом простых итераций\n 2.Методом половинного деления\n" +
                "Введите 1 или 2");
        int choice = scan.nextInt();
        System.out.println("Введите точность (через запятую)");
        double Accuracy = scan.nextDouble();
        double x;
        double f1;
        double f2;
        double p1;
        double p2;
        double X = 0;
        double Rasnost = 0;
        double e;
        double a;
        switch (choice) {
            case 1:
                System.out.println("Найдем решение уравнения: cosx + 0,25x - 0,5 = 0 На интервале 0<x<PI");
                x = 0;
                f1 = Math.acos(0.5-0.25*x);
                p1 = 1/Math.pow(-Math.pow(x,2)+4*x+12,0.5);
                x = Math.PI;
                f2 = Math.acos(0.5-0.25*x);
                p2 = 1/Math.pow(-Math.pow(x,2)+4*x+12,0.5);
                System.out.println( f1 +" "+ p1);
                System.out.println( f2 +" "+ p2);
                double q = Math.max(Math.abs(p1),Math.abs(p2));
                System.out.printf("q = %.4f \n",q);
                if(f1<0 && f2>Math.PI && p1>=1 && p2>=1){
                    System.out.println("Не соблюдается условие сходимости q<1");
                    break;
                }
                System.out.println("x =     "+" e =        ");
                int Iteration = 0;
                do {
                    if (Iteration == 0){
                        x = 0.5 - 0.25*2;
                        X=Math.acos(x);
                        Rasnost = X-2;
                    }
                    if(Iteration>0){
                        a = X;
                        x = 0.5 - 0.25*X;
                        X=Math.acos(x);
                        Rasnost = a-X;
                    }
                    e=(q/(1-q))*Math.abs(Rasnost);
                    System.out.printf("%7.6f",X);
                    System.out.print(" ");
                    System.out.printf("%7.6f\n",e);
                    Iteration ++;

                }while (e>Accuracy);
                break;
            case 2:
                System.out.println("Найдем решение уравнения: cosx + 0,25x - 0,5 = 0 На интервале 0<x<PI");
                a =0;
                double b = 3.14;
                do {
                    double c = (a+b)/2;
                    double x1 =(Math.cos(a)+0.25*a-0.5);
                    double x2 = (Math.cos(c)+0.25*c-0.5);
                    double x3 = (Math.cos(b)+0.25*b-0.5);
                    if (x3<0 && x2<0){
                        b = c;
                    }else if(x2>0 && x1>0){
                        a = c;
                    }
                    e = Math.abs(b-a);
                    System.out.printf("%7.6f",c);
                    System.out.print(" ");
                    System.out.printf("%7.6f\n",e);
                }while (e>Accuracy);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}
