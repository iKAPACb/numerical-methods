package ru.projects.methods.TASK_11_12.tables;

import org.apache.commons.math3.util.Precision;

public class IntegralTable {
    private String name;
    private double istZnachenie;
    private double left;
    private double leftPogreshn;
    private double right;
    private double rightPogreshn;
    private double middle;
    private double middlePogreshn;
    private double trapeze;
    private double trapezePogreshn;
    private double simpson;
    private double simpsonPogreshn;

    public IntegralTable(String name, double istZnachenie, double left, double leftPogreshn, double right, double rightPogreshn, double middle, double middlePogreshn, double trapeze, double trapezePogreshn, double simpson, double simpsonPogreshn) {
        this.name = name;
        this.istZnachenie = Precision.round(istZnachenie,5);
        this.left = Precision.round(left,5);
        this.leftPogreshn = Precision.round(leftPogreshn,5);
        this.right = Precision.round(right,5);
        this.rightPogreshn = Precision.round(rightPogreshn,5);
        this.middle = Precision.round(middle,5);
        this.middlePogreshn = Precision.round(middlePogreshn,5);
        this.trapeze = Precision.round(trapeze,5);
        this.trapezePogreshn = Precision.round(trapezePogreshn,5);
        this.simpson = Precision.round(simpson,5);
        this.simpsonPogreshn = Precision.round(simpsonPogreshn,5);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIstZnachenie() {
        return istZnachenie;
    }

    public void setIstZnachenie(double istZnachenie) {
        this.istZnachenie = istZnachenie;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getLeftPogreshn() {
        return leftPogreshn;
    }

    public void setLeftPogreshn(double leftPogreshn) {
        this.leftPogreshn = leftPogreshn;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public double getRightPogreshn() {
        return rightPogreshn;
    }

    public void setRightPogreshn(double rightPogreshn) {
        this.rightPogreshn = rightPogreshn;
    }

    public double getMiddle() {
        return middle;
    }

    public void setMiddle(double middle) {
        this.middle = middle;
    }

    public double getMiddlePogreshn() {
        return middlePogreshn;
    }

    public void setMiddlePogreshn(double middlePogreshn) {
        this.middlePogreshn = middlePogreshn;
    }

    public double getTrapeze() {
        return trapeze;
    }

    public void setTrapeze(double trapeze) {
        this.trapeze = trapeze;
    }

    public double getTrapezePogreshn() {
        return trapezePogreshn;
    }

    public void setTrapezePogreshn(double trapezePogreshn) {
        this.trapezePogreshn = trapezePogreshn;
    }

    public double getSimpson() {
        return simpson;
    }

    public void setSimpson(double simpson) {
        this.simpson = simpson;
    }

    public double getSimpsonPogreshn() {
        return simpsonPogreshn;
    }

    public void setSimpsonPogreshn(double simpsonPogreshn) {
        this.simpsonPogreshn = simpsonPogreshn;
    }
}
