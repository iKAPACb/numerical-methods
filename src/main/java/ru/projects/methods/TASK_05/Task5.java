package ru.projects.methods.TASK_05;

import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Решение задачи:\n 1.Методом простых итераций\n 2.Методом половинного деления\n" +
                "Введите 1 или 2");
        int choice = scan.nextInt();
        System.out.println("Введите Левую границу отрезка");
        double a = scan.nextDouble();
        System.out.println("Введите Правую границу отрезка");
        double b = scan.nextDouble();
        System.out.println("Введите точность (через запятую)");
        double Accuracy = scan.nextDouble();
        double x;
        double f11;
        double f12;
        double f21;
        double f22;
        double p11;
        double p12;
        double p21;
        double p22;
        double q = 0;
        double q1;
        double q2;
        double X = 0;
        double Rasnost = 0;
        double e;
        double c = 0;
        int Iteration = 0;
        switch (choice) {
            case 1:
                System.out.println("Найдем решение уравнения: cosx + 0,25x - 0,5 = 0 На интервале "+ a +" <x< "+b);
                    // f1(x) = arccos(0,5 - 0,25x) f1'(x) = 1/sqrt(-x^2+4x+12)
                    f11 = Math.acos(0.5 - 0.25 * a);
                    p11 = 1 / Math.sqrt(-Math.pow(a, 2) + 4 * a + 12);
                    f12 = Math.acos(0.5 - 0.25 * b);
                    p12 = 1 / Math.sqrt(-Math.pow(b, 2) + 4 * b + 12);
                    System.out.println(f11 + " " + p11);
                    System.out.println();
                    System.out.println(f12 + " " + p12);
                    System.out.println();
                    q1 = Math.max(Math.abs(p11), Math.abs(p12));
                    System.out.printf("q = %.4f \n", q1);
                    System.out.println();
                
                    // f2(x) = 2 - 4*cos(x) f2'(x) = 4*sin(x)
                    f21 = 2 - 4*Math.cos(a);
                    p21 = 4 * Math.sin(a);
                    f22 = 2 - 4*Math.cos(b);
                    p22 = 4 * Math.sin(b);
                    System.out.println(f21 + " " + p21);
                    System.out.println();
                    System.out.println(f22 + " " + p22);
                    System.out.println();
                    q2 = Math.max(Math.abs(p21), Math.abs(p22));
                    System.out.printf("q = %.4f \n", q2);
                    System.out.println();
                // Проверка на условие сходимости
                if(Math.abs(p11)<1 && Math.abs(p12)<1 && f11>a && f12<b){
                    q = q1;
                    do {
                        System.out.println("x =     "+" e =        ");
                        if (Iteration == 0){
                            x = 0.5 - 0.25*((a+b)*0.5);
                            X=Math.acos(x);
                            Rasnost = X-(a+b)*0.5;
                        }
                        if(Iteration>0){
                            double Save = X;
                            x = 0.5 - 0.25*X;
                            X=Math.acos(x);
                            Rasnost = Save-X;
                        }
                        e=(q/(1-q))*Math.abs(Rasnost);
                        System.out.printf("%7.6f",X);
                        System.out.print(" ");
                        System.out.printf("%7.6f\n",e);
                        Iteration ++;
                    }while (e>Accuracy);
                }else if(Math.abs(p21)<1 && Math.abs(p22)<1 && f21>a && f22<b){
                    q = q2;
                    System.out.println("x =     "+" e =        ");
                    do {
                        if (Iteration == 0){
                            X= 2 - 4*Math.cos((a+b)*0.5);
                            Rasnost = X-(a+b)*0.5;
                        }
                        if(Iteration>0){
                            double Save = X;
                            X= 2 - 4 * Math.cos(X);
                            Rasnost = Save - X;
                        }
                        e=(q/(1-q))*Math.abs(Rasnost);
                        System.out.printf("%7.6f",X);
                        System.out.print(" ");
                        System.out.printf("%7.6f\n",e);
                        Iteration ++;
                    }while (e>Accuracy);
                }else{
                    System.out.println("Не выполняется условие сходимомти");
                }
                break;
            case 2:
                System.out.println("Найдем решение уравнения: cosx + 0,25x - 0,5 = 0 На интервале 0<x<PI");
                System.out.println("x =     "+" e =        ");
                do {
                    c = (a+b)/2;
                    double x1 =(Math.cos(a)+0.25*a-0.5);
                    double x2 = (Math.cos(c)+0.25*c-0.5);
                    double x3 = (Math.cos(b)+0.25*b-0.5);
                    if ((x1<0 && x2<0)||(x1>0 && x2>0)){
                        a = c;
                    }else if((x2>0 && x3>0)||(x2<0 && x3<0)){
                        b = c;
                    }
                    e = Math.abs(b-a);
                    System.out.printf("%7.6f",c);
                    System.out.print(" ");
                    System.out.printf("%7.6f\n",e);
                }while (e>Accuracy);
                if ((c > -Math.PI && c < 0)||(c > 0 && c < Math.PI )||(c > Math.PI && c<2*Math.PI)){
                }else{
                    System.out.println("Решение на заданном отрезке нет");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}

