package ru.projects.methods.TASK_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RotationMethod {
    private static List<double[][]> listOfUMatrix = new ArrayList<>();

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
        double e = 0;

        double[] Lyamda = new double[Amatrix.length];
        double[] Vector = new double[Amatrix.length];
        int iteration = 0;

        double[][] Ares1 = new double[Amatrix.length][Amatrix.length];
        double[][] Ares2;

        // НАЧАЛО ЦИКЛА
        do {
            for (int i = 0; i < Umatrix.length; i++) {
                for (int j = 0; j < Umatrix.length; j++) {
                    if (i == j) {
                        Umatrix[i][j] = 1;
                    } else {
                        Umatrix[i][j] = 0;
                    }
                }
            }
            int First = 0;
            int Second = 1;
            //Находим максимальный элемент и его местоположение,(макс элемент выше главной диагонали)
            double Max = Math.abs(Amatrix[0][1]);
            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = i + 1; j < Amatrix.length; j++) {
                    if (Math.abs(Max) < Math.abs(Amatrix[i][j])) {
                        Max = Math.abs(Amatrix[i][j]);
                        First = i;
                        Second = j;
                    }
                }
            }
            //Находим угол Fi
            double Fi = 1.0 / 2 * Math.atan(2 * Max / (Amatrix[First][First] - Amatrix[Second][Second]));
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
                    UTmatrix[j][i] = Umatrix[i][j];
                }
            }
            double sum = 0;
            //Находим точность
            for (int i = 1; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    if (i > j) {
                        sum += Math.pow(Amatrix[i][j], 2);

                    }
                }
            }

            e = Math.pow(sum, 0.5);
            //Получаем А(к+1)Матрицу и Итоговую U матрицу

            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    Ares1[i][j] = 0;
                    for (int k = 0; k < Amatrix.length; k++) {
                        Ares1[i][j] += UTmatrix[i][k] * Amatrix[k][j];
                    }
                }
            }

            Ares2 = multiMatrix(Ares1, Umatrix);

            double[][] matrixToMultiply = new double[Umatrix.length][Umatrix[0].length];

            for (int i = 0; i < Umatrix.length; i++) {
                System.arraycopy(Umatrix[i], 0, matrixToMultiply[i], 0, Umatrix.length);
            }

            listOfUMatrix.add(matrixToMultiply);

            for (int i = 0; i < Amatrix.length; i++) {
                for (int j = 0; j < Amatrix.length; j++) {
                    Amatrix[i][j] = Ares2[i][j];
                }
            }
            System.out.printf("Погрешность = %.4f Итерация = %d\n",e, ++iteration);
        } while (e > accuracy);

        double[][] FinalMatrix = findFinalUMatrix(0);



        for (int i = 0; i < Amatrix.length; i++) {
            for (int j = 0; j < Amatrix.length; j++) {
                Lyamda[i] = Ares2[i][i];
            }
            System.out.printf("\nLyamda %d = %7.4f\n", i, Lyamda[i]);
            System.out.println("Собственный вектор");
            for (int m = 0; m < Amatrix.length; m++) {
                System.out.printf("%.4f\n",FinalMatrix[m][i]);
            }
        }


    }


    private static double[][] findFinalUMatrix(int start) {
        return start == listOfUMatrix.size() - 2 ? listOfUMatrix.get(start) :
                multiMatrix(listOfUMatrix.get(start), findFinalUMatrix(start + 1));
    }

    private static double[][] multiMatrix(double[][] matrix1, double[][] matrix2) {
        double[][] multi = new double[matrix1.length][matrix1.length];
        for (int m = 0; m < matrix1.length; m++) {
            for (int j = 0; j < matrix1.length; j++) {
                for (int i = 0; i < matrix1.length; i++) {
                    multi[m][j] += matrix1[m][i] * matrix2[i][j];
                }
            }
        }
        return multi;
    }
}
