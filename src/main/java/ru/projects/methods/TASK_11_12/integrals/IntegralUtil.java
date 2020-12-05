package ru.projects.methods.TASK_11_12.integrals;

public class IntegralUtil {
    public static double func(double x) {
        return Math.sqrt(x)/Math.pow(1+2*x,2);
    }
    public static double integralExactValue() {
        return 0;
    }
}