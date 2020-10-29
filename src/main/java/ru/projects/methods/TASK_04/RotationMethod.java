package ru.projects.methods.TASK_04;

public class RotationMethod {
    public static void main(String[] args) {
        double[][] Amatrix;
        double accuracy;
        //Объявление переменных
        InputVariables.scan();
        Amatrix = InputVariables.getAmatrix();
        accuracy = InputVariables.getAccuracy();
        double[][] Umatrix = new double[Amatrix.length][Amatrix.length];
        double[][] UTmatrix = new double[Amatrix.length][Amatrix.length];
        double[][] Ures = new double[Amatrix.length][Amatrix.length];
        double e;

        double[] Lyamda = new double[Amatrix.length];
        double[] Vector = new double[Amatrix.length];
        int iteration = 0;
        do {
            for (int i = 0; i < Umatrix.length; i++) {
                for (int j = 0; j < Umatrix.length; j++) {
                    if (i==j){
                        Umatrix[i][j]=1;
                    }else{
                        Umatrix[i][j]=0;
                    }
                }
            }
            int First = 0;
            int Second = 1;
            //Находим максимальный элемент и его местоположение,(макс элемент выше главной диагонали)
            double Max = Math.abs(Amatrix[0][1]);
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = i+1; j < Amatrix.length; j++) {
                    if(Math.abs(Max)<Math.abs(Amatrix[i][j])){
                        Max =Math.abs(Amatrix[i][j]);
                        First = i;
                        Second = j;
                    }
                }
            }
            //Находим угол Fi
            double Fi = 1/2*Math.atan(2*Max/(Amatrix[First][First]-Amatrix[Second][Second]));
            double cosFi = Math.cos(Fi);
            double sinFi = Math.sin(Fi);
            //Получаем U матрицу
            Umatrix[First][First] = cosFi;
            Umatrix[Second][Second] = cosFi;
            Umatrix[First][Second] = -sinFi;
            Umatrix[Second][First] = sinFi;
            //Транспонируем U матрицу получая UT матрицу.
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    UTmatrix[j][i]=Umatrix[i][j];
                }
            }
            double sum = 0;
            e = 0;
            //Находим точность
            for (int i = 1; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    if(i>j){
                        sum += Math.pow(Amatrix[i][j],2);
                        e = Math.pow(sum,0.5);
                    }
                }
            }
            //Получаем А(к+1)Матрицу и Итоговую U матрицу
            double[][] Ares1 = new double[Amatrix.length][Amatrix.length];
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    Ares1[i][j]=0;
                    for (int k = 0; k < Amatrix.length; k++) {
                        Ares1[i][j]+=UTmatrix[i][k]*Amatrix[k][j];
                    }
                }
            }
            double[][] Ares2 = new double[Amatrix.length][Amatrix.length];
            double[][] Ures2 = new double[Amatrix.length][Amatrix.length];
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    Ares2[i][j]=0;
                    Ures2[i][j]=0;
                    for (int k = 0; k < Amatrix.length; k++) {
                        Ares2[i][j]+=Ares1[i][k]*Umatrix[k][j];
                        if(iteration==0){
                            Ures[i][j]=Umatrix[i][j];
                        }else {
                            Ures2[i][j]+=Ures[i][k]*Umatrix[k][j];
                        }
                    }
                }
            }
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    Amatrix[i][j]=Ares2[i][j];
                }
            }
            System.out.println("Погрешность = "+e+"Итерация "+iteration);
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    Lyamda[i]=Ares2[i][i];
                    System.out.print("Lyamda"+i+"=");
                    System.out.printf("%7.4f\n",Lyamda[i]);
                }
            }
            System.out.println("Собственные вектора");
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    if (iteration==0){
                        Vector[i]=Ures[j][i];
                    }else{
                        Vector[i]=Ures2[j][i];
                    }
                }
                System.out.printf("%7.4f\n",Vector[i]);
            }
            iteration++;
        }while(e>accuracy);
    }
}
