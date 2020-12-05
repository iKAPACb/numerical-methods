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
        return Fkoef(X0,X1,X2,X3)*(x-X1)*(x-X2)*(x-X3)+
                Skoef(X0,X1,X2,X3)*(x-X0)*(x-X2)*(x-X3)+
                Tkoef(X0,X1,X2,X3)*(x-X0)*(x-X1)*(x-X3)+
                ForthKoef(X0,X1,X2,X3)*(x-X0)*(x-X1)*(x-X2);

    }

    public double Fист(double x){
        return Math.atan(x)+x;
    }

    private double ОМЕГА0(double X0, double X1, double X2, double X3){
        return (X0 - X1)*(X0 - X2)*(X0 - X3);
    }
    private double ОМЕГА1(double X0, double X1, double X2, double X3){
        return (X1 - X0)*(X1 - X2)*(X1 - X3);
    }
    private double ОМЕГА2(double X0, double X1, double X2, double X3){
        return (X2 - X0)*(X2 - X1)*(X2 - X3);
    }
    private double ОМЕГА3(double X0, double X1, double X2, double X3){
        return (X3 - X0)*(X3 - X1)*(X3 - X2);
    }

    private double Fkoef(double X0, double X1, double X2, double X3){
        return Fист(X0) / ОМЕГА0(X0,X1,X2,X3);
    }
    private double Skoef(double X0, double X1, double X2, double X3){
        return Fист(X1) / ОМЕГА1(X0,X1,X2,X3);
    }
    private double Tkoef(double X0, double X1, double X2, double X3){
        return Fист(X2) / ОМЕГА2(X0,X1,X2,X3);
    }
    private double ForthKoef(double X0, double X1, double X2, double X3){return Fист(X3) / ОМЕГА3(X0,X1,X2,X3);}
}

