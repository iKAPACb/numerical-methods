package ru.projects.methods.Task_08;

import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Какой корень вы хотите найти");
        System.out.println("Если положительный, то введите 1, если отрицательный, то 2");
        int choice = scan.nextInt();
        switch (choice){
            case 1:
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
                if (x1<1.5||x1>2||x2>=2||x2<1.5||y1<1.65||y1>2||y2<1.65||y2>2){
                    System.out.println("В данном интервале не соблюдается условие сходимости");
                    System.out.println("Введите интервал соблюдающий условие Х=(1,5;1,9] Y=[1,65;2]");
                    return;
                }else {
                    double x0 =(x1+x2)/2;
                    double y0 =(y1+y2)/2;
                    double q;
                    double helperfi1 = Math.abs(-1.0/(4*Math.sqrt(4-(x1*x1/4))));
                    double helperfi12 = Math.abs(-1.0/(4*Math.sqrt(4-(x2*x2/4))));
                    double helperfi2 = Math.abs(-1.0/(4*y1-x1))+Math.abs(4.0/(4*y1-x1));
                    double helperfi22 = Math.abs(-1.0/(4*y2-x2))+Math.abs(4.0/(4*y2-x2));
                    double q1 = Math.max(helperfi1,helperfi12);
                    double q2 = Math.max(helperfi2,helperfi22);
                    System.out.println(q1);
                    System.out.println(q2);
                    if (q1>1||q2>1){
                        System.out.println("Не выполняется условие сходимости");
                        return;
                    }else{
                        q = Math.max(q1,q2);
                    }
                    double e=10;
                    double Fi1;
                    double Fi2;
                    int Iteration = 0;
                    System.out.println(" |k|   x(k) |   y(k) |  Fi1(k) |  Fi2(k) |    e       |");
                    do {
                        if (Iteration==0){
                            //Fi1 = y = sqrt(4 - x^2/4)   Fi2 = x = ln(4y-x)
                            Fi1 = Math.sqrt(4-(Math.pow(x0,2)/4));
                            Fi2 = Math.log(4*y0-x0);
                            System.out.printf("|%2d|%2.6f|%2.6f|%2.6f|%2.6f  |    -      |\n",Iteration,x0,y0,Fi2,Fi1);
                            x0 = Fi2;
                            y0 = Fi1;
                        }else{
                            double x = x0;
                            double y = y0;
                            Fi1 = Math.sqrt(4-(Math.pow(x,2)/4));
                            Fi2 = Math.log(4*y-x);
                            e = (q/(q-1))*Math.max(Math.abs(y-Fi1),Math.abs(x-Fi2));
                            System.out.printf("|%2d|%2.6f|%2.6f|%2.6f|%2.6f  |%2.10f|\n",Iteration,x,y,Fi2,Fi1,e);
                            y0=Fi1;
                            x0=Fi2;
                        }
                        Iteration++;
                    }while(Math.abs(e)>Accuracy);
                }
                break;
            case 2:
                System.out.println("Введите Левую границу отрезка x1 (через запятую)");
                x1 = scan.nextDouble();
                System.out.println("Введите Правую границу отрезка x2 (через запятую)");
                x2 = scan.nextDouble();
                System.out.println("Введите Левую границу отрезка y1 (через запятую)");
                y1 = scan.nextDouble();
                System.out.println("Введите Правую границу отрезка y2 (через запятую)");
                y2 = scan.nextDouble();
                System.out.println("Введите точность (через запятую)");
                Accuracy = scan.nextDouble();
                if (x1<=-4.0||x1>-3.0||x2>-3.0||x2<=-4.0||y1<=-2.0||y1>=0||y2<=-2.0||y2>=0){
                    System.out.println("В данном интервале не соблюдается условие сходимости");
                    System.out.println("Введите интервал соблюдающий условие Х=(-4;-3] Y=(-1,5;0)");
                    return;
                }else {
                    double x0 =(x1+x2)/2;
                    double y0 =(y1+y2)/2;
                    double q;
                    double helperfi1 = Math.abs(-4.0/(4*Math.sqrt(16-(4*y1*y1))));
                    double helperfi12 = Math.abs(-4.0/(4*Math.sqrt(16-(4*y2*y2))));
                    double helperfi2 = Math.abs((1.0/4.0)*Math.exp(x1)+(1.0/4.0));
                    double helperfi22 = Math.abs((1.0/4.0)*Math.exp(x2)+(1.0/4.0));
                    double q1 = Math.max(helperfi1,helperfi12);
                    double q2 = Math.max(helperfi2,helperfi22);
                    System.out.println(q1);
                    System.out.println(q2);
                    if (q1>1||q2>1){
                        System.out.println("Не выполняется условие сходимости");
                        return;
                    }else{
                        q = Math.max(q1,q2);
                    }
                    double e=10;
                    double x=0;
                    double y=0;
                    double Fi1;
                    double Fi2;
                    int Iteration = 0;
                    System.out.println(" |k|   x(k)  |   y(k)  |  Fi1(k)  |  Fi2(k)  |    e       |");
                    do {
                        if (Iteration==0){
                            //Fi1 = y = (e^x + x)/4   Fi2 = x = - sqrt(16 - 4y^2)
                            Fi1 = (1.0/4.0)*Math.exp(x0)+(1.0/4.0)*x0;
                            Fi2 = -1.0*Math.sqrt(16-4*y0*y0);
                            System.out.printf("|%2d|%2.6f|%2.6f|%2.6f|%2.6f  |    -      |\n",Iteration,x0,y0,Fi2,Fi1);
                            x = Fi2;
                            y = Fi1;
                        }else{
                            Fi1 = (1.0/4.0)*Math.exp(x)+(1.0/4.0)*x;
                            Fi2 = -1.0*Math.sqrt(16-4*y*y);
                            double max;
                            max = Math.max(Math.abs(y-Fi1),Math.abs(x-Fi2));
                            e = (q/(q-1))*max;
                            System.out.printf("|%2d|%2.6f|%2.6f|%2.6f|%2.6f  |%2.10f|\n",Iteration,x,y,Fi2,Fi1,Math.abs(e));
                            y=Fi1;
                            x=Fi2;
                        }
                        Iteration++;
                    }while(Math.abs(e)>Accuracy);
                    System.out.printf("|%2d|%2.6f|%2.6f|%2.6f|%2.6f  |%2.10f|\n",Iteration,Fi2,Fi1,Fi2,Fi1,e);
                }
                break;
        }

    }
}
