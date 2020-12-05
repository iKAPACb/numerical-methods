package ru.projects.methods.TASK_09_10;

public class Newton {
    private double X0;
    private double X1;
    private double X2;
    private double X3;

    private double funcVar ;
    private double funcVarX1 ;
    private double funcVarX2 ;
    private double funcVarX3 ;


    public Newton(double x0, double x1, double x2, double x3) {
        X0 = x0;
        X1 = x1;
        X2 = x2;
        X3 = x3;
        calculate();
    }

    public double Fист(double x){
        return Math.atan(x)+x;
    }

    public double calculateNewton(double x){
        return  funcVar + funcVarX1*(x-X0) + funcVarX2*(x-X0)*(x-X1) + funcVarX3*(x-X0)*(x-X1)*(x-X2)  ;
    }


    private void calculate(){
        calculateFisrt();
        calculateSecond();
        calculateThird();
        calculateFour();
    }
    private void calculateFisrt(){
        funcVar = Math.atan(X0)+X0;

    }

    private void calculateSecond(){
        funcVarX1 = (((Math.atan(X0)+X0)/(X0-X1)) + ((Math.atan(X1)+X1)/(X1-X0))) ;

    }

    private void calculateThird(){
        funcVarX2 = (((Math.atan(X0)+X0)/((X0-X1)*(X0-X2)))+((Math.atan(X1)+X1)/((X1-X0)*(X1-X2)))+
                ((Math.atan(X2)+X2)/((X2-X0)*(X2-X1))));

    }

    private void calculateFour(){
        funcVarX3 = ((Math.atan(X3)+X3)/((X3-X2)*(X3-X1)*(X3-X0))+
                (Math.atan(X2)+X2)/((X2-X3)*(X2-X1)*(X2-X0))+
                (Math.atan(X1)+X1)/((X1-X3)*(X1-X2)*(X1-X0))+
                (Math.atan(X0)+X0)/((X0-X3)*(X0-X2)*(X0-X1)));
    }

}
