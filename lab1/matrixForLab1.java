package lab1;

public class matrixForLab1 {

    /*В мене варіант у групі 7, тому: C5 = 2 ; C7 = 0; C11 = 7
    C = A+B
    double
    Обчислити суму найбільших елементів в стовпцях матриці 
    з непарними номерами та найменших елементів в стовпцях матриці з парними номерами
     */

    public static void main(String[] args) {
        try {
            matrixForLab1 processor = new matrixForLab1();
            processor.execute();
        } catch (Exception e) {
            System.err.println("Сталася помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void execute() {
        try {
            double[] a = {2.2, 1.2};
            double[] b = {2.23, 4.1};

            double[][] matrixC = getMatrix(a, b);
            doActionWithMatrix(matrixC, a, b);

        } catch (ArithmeticException e) {
            System.err.println("Помилка обчислень: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("передано порожнє посилання на масив.");
        } catch (Exception e) {
            System.err.println("помилка: " + e.getMessage());
        }
    }

    private double[][] getMatrix(double[] a, double[] b) {
        if (a == null || b == null) {
            throw new NullPointerException("Масиви не можуть бути null");
        }

        double[][] c = new double[a.length][b.length];

        System.out.println("Отримана матриця C:");

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                c[i][j] = a[i] + b[j];
                System.out.printf("%.2f ", c[i][j]);
            }
            System.out.println();
        }
        return c;
    }

    private void doActionWithMatrix(double[][] matrix, double[] a, double[] b) {
        double sum = 0;

        for (double[] row : matrix) {
            for (double val : row) {
                sum += val;
            }
        }

        double average = sum / (a.length * b.length);
        double sumMax = 0;
        double sumMin = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j % 2 == 1 && matrix[i][j] > average) {
                    sumMax += matrix[i][j];
                } else if (j % 2 == 0 && matrix[i][j] < average) {
                    sumMin += matrix[i][j];
                }
            }
        }

        System.out.printf("Друга дія: Сума більших за середнє = " +  sumMax + " Сума менших за середнє = " + sumMin);
    }
}
