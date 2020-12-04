package ru.projects.methods.TASK_09_10;

public class Newton {
    private double X0;
    private double X1;
    private double X2;
    private double X3;

    private double[] funcVar = new double[4];
    private double[] funcVarX1 = new double[3];
    private double[] funcVarX2 = new double[2];
    private double[] funcVarX3 = new double[1];


    public Newton(double x0, double x1, double x2, double x3) {
        X0 = x0;
        X1 = x1;
        X2 = x2;
        X3 = x3;
        calculate();
    }

    public double Func(double x){
        return Math.atan(x)+x;
    }

    public double calculateNewton(double x){
        return funcVar[0] + funcVarX1[0]*(x-X0) + funcVarX2[0]*(x-X0)*(x-X1) + funcVarX3[0]*(x-X0)*(x-X1)*(x-X2)  ;
    }


    private void calculate(){
        calculateFisrt();
        calculateSecond();
        calculateThird();
        calculateFourth();
    }
    private void calculateFisrt(){
        funcVar[0] = Math.atan(X0)+X0;

    }

    private void calculateSecond(){
        funcVarX1[0] = (Math.atan(X0)+X0)/(X0-X1) + (Math.atan(X1)+X1)/(X1-X0) ;

    }

    private void calculateThird(){
        funcVarX2[0] = ((Math.atan(X0)+X0)/((X0-X1)*(X0-X2)))+((Math.atan(X1)+X1)/((X1-X0)*(X1-X2)))+
                ((Math.atan(X2)+X2)/((X2-X0)*(X2-X1)));

    }

    private void calculateFourth(){
        funcVarX3[0] = (Math.atan(X3)+X3)/((X3-X2)*(X3-X1)*(X3-X0))+
                (Math.atan(X2)+X2)/((X2-X3)*(X2-X1)*(X2-X0))+
                (Math.atan(X1)+X1)/((X1-X3)*(X1 - X2)*(X1-X0))+
                (Math.atan(X0)+X0)/((X0-X3)*(X0 - X2)*(X0-X1));
    }

}
