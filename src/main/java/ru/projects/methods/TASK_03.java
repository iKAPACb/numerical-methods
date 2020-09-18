package ru.projects.methods;

public class TASK_03 {
    public static void main(String[] args) {
        double i = 0.001;
        double max = Double.NEGATIVE_INFINITY;
        double value = 0;
        while (i <= Math.PI){
            value = Math.sin(i) - Math.exp(i) / 2.0;
            max = Math.max(value, max);
            i += 0.001;
        }
        System.out.printf("Максимальное значение графика = %.3f",max);

    }
}
