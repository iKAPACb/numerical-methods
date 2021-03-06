package ru.projects.methods.TASK_03;

import java.util.Scanner;

public class TASK_3_Yakobi {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("МЕНЮ:\n 1.ПОДСЧЕТ 5ГО ВАРИАНТА\n 2.ВВОД СВОЕЙ МАТРИЦЫ\n Введите 1 или 2");
        int choice = scan.nextInt();
        //инициализация
        double[][] matrix;
        double[] b;
        double e;
        int numberOfEquation = 0;
        switch (choice) {
            case 1:
                matrix = new double[][]{{20, 5, 7,1}, {-1, 13, 0,-7}, {4, -6, 17,5},{-9,8,4,-25}};
                b = new double[]{-117,-1,49,-21};
                e = 0.001;
                numberOfEquation = matrix.length;
                break;
            case 2:
                System.out.print("Введите размер матрицы / кол-во уравнений: ");
                //размер массива
                numberOfEquation = scan.nextInt();
                matrix = new double[numberOfEquation][numberOfEquation];
                b = new double[numberOfEquation];
                for (int i = 0; i < numberOfEquation; i++) {
                    int Stroka = i+1;
                    System.out.print("Введите коэфициэнты " + Stroka + " строки: ");
                    for (int j = 0; j < numberOfEquation; j++) {
                        matrix[i][j] = scan.nextDouble();
                    }
                }
                //вектор заполнение
                System.out.print("Введите свободные коэффициенты: ");
                for (int i = 0; i < numberOfEquation; i++) {
                    b[i] = scan.nextDouble();
                }
                //Точность
                System.out.println("Введите точность (Через запятую)");
                e = scan.nextDouble();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
        double[] m = new double[numberOfEquation];
        double[] max = new double[numberOfEquation];
        double[][] a = new double[numberOfEquation][numberOfEquation];
        //матрица
        double Ek;
        double [] delta = new double[numberOfEquation];
        double DeltaMax;
        double [] sum = new double[numberOfEquation];
        double [] helper = new double[numberOfEquation];
        double Ac =0;
        double Bc =0;
        for (int i = 0; i < numberOfEquation; i++) {
            for (int j = 0; j < numberOfEquation; j++) {
                a[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < numberOfEquation; i++) {
            for (int j = 0; j <numberOfEquation ; j++) {
                if(i==j){
                    m[i] = a[i][j];
                    b[i] = b[i]/m[i];
                    a[i][j] = 0;
                    if(b[i]==0){
                        b[i]=0;
                    }
                }
            }
        }
        for (int i = 0; i < numberOfEquation; i++) {
            for (int j = 0; j <numberOfEquation ; j++) {
                a[i][j] = a[i][j]/m[i] * -1;
                if (a[i][j]==0){
                    a[i][j]=0;
                }
            }
        }
        for (int i = 0; i < numberOfEquation; i++) {
            for (int j = 0; j < numberOfEquation; j++) {
                max[i] = max[i] + Math.abs(a[i][j]);
                if (Math.abs(b[i])>Bc){
                    Bc = Math.abs(b[i]);
                }
                if (max[i]>=1) {
                    System.out.println("Норма матрицы A >= 1");
                    break;                              //Проверка условия ||a||c<1
                }
            }
            if (Ac<max[i]){
                Ac = max[i];
            }
            max[i]=0;
        }
        System.out.println("Матрица А");
        for (int i = 0; i < numberOfEquation; i++) {
            for (int j = 0; j < numberOfEquation; j++) {
                System.out.printf("%7.3f",a[i][j]);
            }
            System.out.println();
        }
        System.out.println("Норма матрицы А "+Ac);
        System.out.println("Матрица B");
        for (int i = 0; i < numberOfEquation; i++) {
            System.out.printf("%7.3f\n",b[i]);
        }
        System.out.println("Норма матрицы B "+Bc);
        // Априорная оценка необходимого числа итераций k для достижения заданной точности
        double k = Math.floor((Math.log10(e)-Math.log10(Bc)+Math.log10(1-Ac))/(Math.log10(Ac)));
        double [] x = new double[numberOfEquation];
        for (int i = 0; i < numberOfEquation; i++) {
            x[i] = b[i];
        }
        int N = 0;
        do {
            DeltaMax = 0.0;
            for (int i = 0; i < numberOfEquation; i++) {
                sum [i] = b[i];
                for (int j = 0; j < numberOfEquation; j++) {
                    sum[i] = sum[i] + (a[i][j] * x[j]);
                }
            }
            for (int i = 0; i < numberOfEquation; i++) {
                if (N==0){
                    delta[i] = Math.abs(sum[i] - b[i]);
                } else {
                    delta[i] = Math.abs(sum[i] - x[i]);
                }
                if (delta[i]>DeltaMax){
                    DeltaMax = delta[i];
                }
            }
            Ek = ((Ac/(1-Ac)) * DeltaMax);
            System.out.printf("Решение уравнения с точностью = "+"%.5f\n", Ek);
            for (int i = 0; i < numberOfEquation; i++) {
                x[i] = sum[i];
                System.out.printf("%.4f\n",x[i]);
            }
            N = N+1;
        }while(Ek>e);
        System.out.println("необходимое число итераций = "+k);
        System.out.println("Потребовалось " + N + " итераций");
        System.out.println("Норма матрицы А "+Ac);
    }
}