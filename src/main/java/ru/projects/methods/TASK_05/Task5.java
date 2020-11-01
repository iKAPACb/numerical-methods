package ru.projects.methods.TASK_05;

import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Решение задачи:\n 1.Методом простых итераций\n 2.Методом половинного деления\n" +
                "Введите 1 или 2");
        int choice = scan.nextInt();
        System.out.println("Введите Левую границу отрезка (через запятую)");
        double a = scan.nextDouble();
        System.out.println("Введите Правую границу отрезка (через запятую)");
        double b = scan.nextDouble();
        System.out.println("Введите точность (через запятую)");
        double Accuracy = scan.nextDouble();
        double x = 0;
        double Max;
        double Fx;
        double Lyamda = 0;
        double Fixa;
        double Fixb;
        double xLast = 0;
        double f1a;
        double f1b;
        double p1a;
        double p1b;
        double q;
        double e;
        double c;
        double Rasnost = 0;
        int Iteration = 0;
        switch (choice) {
            case 1:
                System.out.println("Найдем решение уравнения: cosx + 0,25x - 0,5 = 0 На интервале " + a + " <x< " + b);
                if (a > -0.7 && a < 1.4 && b > 1.42 && b < 4.1) {
                    // f1(x) = arccos(0,5 - 0,25x) f1'(x) = 1/sqrt(-x^2+4x+12)
                    f1a = Math.acos(0.5 - 0.25 * a);
                    p1a = 1 / Math.sqrt(-Math.pow(a, 2) + 4 * a + 12);
                    f1b = Math.acos(0.5 - 0.25 * b);
                    p1b = 1 / Math.sqrt(-Math.pow(b, 2) + 4 * b + 12);
                    q = Math.max(Math.abs(p1a), Math.abs(p1a));
                    System.out.println();
                    if (Math.abs(p1a) < 1 && Math.abs(p1b) < 1 && f1a > a && f1b < b) {
                        System.out.println("Не выполняется условие сходимости");
                    }
                        do {
                            if (Iteration == 0) {
                                xLast = Math.acos(0.5 - 0.25 * ((a + b) * 0.5));
                                Rasnost = xLast - (a + b) * 0.5;
                            }
                            if (Iteration > 0) {
                                x = Math.acos(0.5 - 0.25 * xLast);
                                Rasnost = x - xLast;
                                xLast = x;
                            }
                            e = (q / (1 - q)) * Math.abs(Rasnost);
                            System.out.printf("Итерация %d X = %.5f Погрешность = %.5f\n", Iteration++, x, e);
                        } while (e > Accuracy);
                    } else {
                        // f(x) = cos(x) + 0,25x - 0,5 = 0
                        // Представим f(x)  в функцию х = ф(х) (x = x - Lyamda(cos(x) + 0,25x - 0,5)
                        // f'(x) = 0,25 - sin(x)
                        // f'(x)max = 1,25
                        Max = 0.25 - Math.sin(a);
                        for (double i = a; i <= b; i = i + 0.1) {
                            if (Max < (0.25 - Math.sin(i))) {
                                Max = 0.25 - Math.sin(i);
                            }
                        }
                        Lyamda = 1 / Max;
                        xLast = a;
                        Fixa = Math.cos(a) + 0.25 * a - 0.5;
                        Fixb = Math.cos(b) + 0.25 * b - 0.5;
                        if (Fixa * Fixb < 0) {
                            System.out.println("Корень лежит в интервале от a = " + a + " до b = " + b);
                        } else {
                            System.out.println("Корень не лежит в интервале от a = " + a + " до b = " + b +
                                    " или в данном интервале более 1 корня, попробуйте ввести другой интервал");
                            break;
                        }
                        do {
                            Fx = Math.cos(xLast) + 0.25 * xLast - 0.5;
                            x = xLast - Lyamda * (Fx);
                            xLast = x;
                            System.out.printf("Итерация %d Корень = %.5f Погрешность = %.5f\n", Iteration++, x, Fx);
                        } while (Math.abs(Fx) > Accuracy);
                    if ((Lyamda * (0.25 - Math.sin(x))) > 0) {
                        System.out.println("Выполняется условие сходимости (Lyamda*f'(x)>0)");
                    } else {
                        System.out.println("не выполняется условие сходимости (Lyamda*f'(x)>0)");
                        }
                    }
                    break;
            case 2:
                    System.out.println("Найдем решение уравнения: cosx + 0,25x - 0,5 = 0 На интервале"+ a+" <x< "+ b);
                    do {
                        c = (a + b) / 2;
                        double x1 = (Math.cos(a) + 0.25 * a - 0.5);
                        double x2 = (Math.cos(c) + 0.25 * c - 0.5);
                        double x3 = (Math.cos(b) + 0.25 * b - 0.5);
                        if ((x1 < 0 && x2 < 0) || (x1 > 0 && x2 > 0)) {
                            a = c;
                        } else if ((x2 > 0 && x3 > 0) || (x2 < 0 && x3 < 0)) {
                            b = c;
                        }
                        e = Math.abs(b - a);
                        System.out.printf("Итерация %d Корень = %.5f Погрешность = %.5f\n", Iteration++, c, e);
                    } while (e > Accuracy);
                    if ((c > -Math.PI && c < 0) || (c > 0 && c < Math.PI) || (c > Math.PI && c < 2 * Math.PI)) {
                    } else
                    System.out.println("Решение на заданном отрезке нет");
                    break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}

