package ru.projects.methods.TASK_07;

import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите Левую границу отрезка x1 (через запятую)");
        double x1 = scan.nextDouble();
        System.out.println("Введите Правую границу отрезка x2 (через запятую)");
        double x2 = scan.nextDouble();
        System.out.println("Введите Левую границу отрезка y1 (через запятую)");
        double y1 = scan.nextDouble();
        System.out.println("Введите Правую границу отрезка y2 (через запятую)");
        double y2 = scan.nextDouble();
        System.out.println("Введите точность (через запятую)");
        double Accuracy = scan.nextDouble();
        double x = 0;
        double y = 0;
        double x0 = (x1+x2)/2;
        double y0 = (y1+y2)/2;
        double e = 10;
        double F1;
        double F2;
        double dF1dy;
        double dF1dx;
        double dF2dy;
        double dF2dx;
        if (y2>2||y1<-2){
            System.out.println("Введите допустимый X:[-4.0;4.0]");
            return;
        }
        if (x2>4||x1<-4){
            System.out.println("Введите допустимый X:[-2.0;2.0]");
            return;
        }
        double A1;
        double A2;
        double J;
        int Iteration = 0;
        System.out.println(" |k|   x(k) |   y(k) |  f1(k) |  f2(k) |dF1dy(k)|dF1dx(k)|" +
                "dF2dy(k)|" + "dF2dx(k)|  A1(k) |  A2(k) |  J (k) |    e    |");
        do {
            if (Iteration==0){
                F1 = Math.pow(x0,2)/16 + Math.pow(y0,2)/4 - 1;
                F2 = 4*y0 - Math.exp(x0) - x0;
                dF1dx = (1.0/8.0)*x0;
                dF1dy = (1.0/2.0)*y0;
                dF2dy = 4.0;
                dF2dx = -1*Math.exp(x0)-1;
                A1 = (F1 * dF2dy) - (F2 * dF1dy);
                A2 = (dF1dx * F2) - (F1 * dF2dx);
                J = (dF1dx * dF2dy) - (dF1dy * dF2dx);
                x = x0 - (A1 / J);
                y = y0 - (A2 / J);
                e = Math.max(Math.abs(y - y0),Math.abs(x - x0));
                System.out.printf("|%2d|%2.6f|%2.6f|%2.6f|%2.6f|%2.6f|%2.6f|%2.6f|" +
                        "%2.6f|%2.6f|%2.6f|%2.6f|   -   |\n",Iteration,x0,y0,F1,F2,dF1dy,dF1dx,dF2dy,dF2dx,A1,A2,J);
            }else{
                F1 = Math.pow(x,2)/16 + Math.pow(y,2)/4 - 1;
                F2 = 4*y - Math.exp(x) - x;
                dF1dy = (1.0/2.0)*y;
                dF1dx = (1.0/8.0)*x;
                dF2dy = 4.0;
                dF2dx = -1*Math.exp(x)-1;
                A1 = (F1 * dF2dy) - (F2 * dF1dy);
                A2 = (dF1dx * F2) - (F1 * dF2dx);
                J = (dF1dx * dF2dy) - (dF1dy * dF2dx);
                double a =x;
                double b =y;
                x = x - (A1 / J);
                y = y - (A2 / J);
                e = Math.max(Math.abs(y - b),Math.abs(x - a));
                System.out.printf("|%2d|%2.6f|%2.6f|%2.6f|%2.6f|%2.6f|%2.6f|%2.6f|" +
                        "%2.6f|%2.6f|%2.6f|%2.6f|%.6f|\n",Iteration,x,y,F1,F2,dF1dy,dF1dx,dF2dy,dF2dx,A1,A2,J,e);
            }
            Iteration++;
        }while(e>Accuracy);
        if(x < x1 || x > x2 || y < y1 || y > y2 || Double.isNaN(x) || Double.isNaN(y)){
            System.out.println("Корней в данном квадрате нет или функции сходтся к решению," +
                    " нележащему в данном квадрате." +
                    "\n Выберете другой квадрат.");
        }
    }
}
