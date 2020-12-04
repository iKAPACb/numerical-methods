package ru.projects.methods.TASK_09_10;

public class Lagrange {

    private double X0;
    private double X1;
    private double X2;
    private double X3;

    public Lagrange(double x0, double x1, double x2, double x3) {
        X0 = x0;
        X1 = x1;
        X2 = x2;
        X3 = x3;
    }


    public double calculateLagrange(double x){
        return firstKOEF(X0,X1,X2,X3)*(x-X1)*(x-X2)*(x-X3)+
                secondKOEF(X0,X1,X2,X3)*(x-X0)*(x-X2)*(x-X3)+
                thirdKOEF(X0,X1,X2,X3)*(x-X0)*(x-X1)*(x-X3)+
                fourthKOEF(X0,X1,X2,X3)*(x-X0)*(x-X1)*(x-X2);

    }

    public double Func(double x){
        return Math.atan(x)+x;
    }

    private double Omega0(double X0, double X1, double X2, double X3){
        return (X0 - X1)*(X0 - X2)*(X0 - X3);
    }
    private double Omega1(double X0, double X1, double X2, double X3){
        return (X1 - X0)*(X1 - X2)*(X1 - X3);
    }
    private double Omega2(double X0, double X1, double X2, double X3){
        return (X2 - X0)*(X2 - X1)*(X2 - X3);
    }
    private double Omega3(double X0, double X1, double X2, double X3){
        return (X3 - X0)*(X3 - X1)*(X3 - X2);
    }

    private double firstKOEF(double X0, double X1, double X2, double X3){
        return Func(X0) / Omega0(X0,X1,X2,X3);
    }
    private double secondKOEF(double X0, double X1, double X2, double X3){
        return Func(X1) / Omega1(X0,X1,X2,X3);
    }
    private double thirdKOEF(double X0, double X1, double X2, double X3){
        return Func(X2) / Omega2(X0,X1,X2,X3);
    }
    private double fourthKOEF(double X0, double X1, double X2, double X3){
        return Func(X3) / Omega3(X0,X1,X2,X3);
    }
}