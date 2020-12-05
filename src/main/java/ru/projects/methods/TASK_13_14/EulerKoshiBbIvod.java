package ru.projects.methods.TASK_13_14;

import org.apache.commons.math3.util.Precision;

public class EulerKoshiBbIvod {
    private int i;
    private double h;
    private double x;
    private double y;
    private double z;

    private double yr;
    private double zr;

    private String delta_z;
    private String delta_y;
    private double istY;
    private double accuracy;

    public EulerKoshiBbIvod(int i, double h, double x, double y, double z, double yr, double zr, double delta_z, double delta_y, double istY, double accuracy) {
        this.i = i;
        this.h = h;
        this.x = Precision.round(x,4);
        this.y = Precision.round(y,4);
        this.z = Precision.round(z,4);
        this.yr = Precision.round(yr,4);
        this.zr = Precision.round(zr,4);
        if(delta_z == 0){
            this.delta_z = "-----";
        }else{
            this.delta_z = String.valueOf(Precision.round(delta_z,4));
        }

        if(delta_y == 0){
            this.delta_y = "-----";
        }else{
            this.delta_y = String.valueOf(Precision.round(delta_y,4));
        }
        this.istY = Precision.round(istY,4);;
        this.accuracy = Precision.round(accuracy,4);;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getYr() {
        return yr;
    }

    public void setYr(double yr) {
        this.yr = yr;
    }

    public double getZr() {
        return zr;
    }

    public void setZr(double zr) {
        this.zr = zr;
    }

    public String getDelta_z() {
        return delta_z;
    }

    public void setDelta_z(String delta_z) {
        this.delta_z = delta_z;
    }

    public String getDelta_y() {
        return delta_y;
    }

    public void setDelta_y(String delta_y) {
        this.delta_y = delta_y;
    }

    public double getIstY() {
        return istY;
    }

    public void setIstY(double istY) {
        this.istY = istY;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
