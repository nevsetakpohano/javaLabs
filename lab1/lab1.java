import java.util.Random;

public class Lab1 {

    public static void main(String[] args) {
        int rowsA = 3, colsA = 3;
        int rowsB = 3, colsB = 3;

        float[][] matrixA = createRandomFloatMatrix(rowsA, colsA);
        float[][] matrixB = createRandomFloatMatrix(rowsB, colsB);

        System.out.println("Matrix A:");
        printMatrix(matrixA);
        System.out.println("Matrix B:");
        printMatrix(matrixB);

        float[][] matrixC = transposeMatrix(matrixB);
        System.out.println("Matrix C (transposed B):");
        printMatrix(matrixC);

        float result = calculateSum(matrixC);
        System.out.println("Result of matrix C processing: " + result);
    }

    public static float[][] createRandomFloatMatrix(int rows, int cols) {
        float[][] matrix = new float[rows][cols];
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextFloat() * 100;
            }
        }
        return matrix;
    }

    public static void printMatrix(float[][] matrix) {
        for (float[] row : matrix) {
            for (float val : row) {
                System.out.printf("%8.2f", val);
            }
            System.out.println();
        }
    }

    public static float[][] transposeMatrix(float[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        float[][] transposed = new float[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    public static float calculateSum(float[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        float sum = 0;

        for (int col = 0; col < cols; col++) {
            float colValue = matrix[0][col];
            for (int row = 1; row < rows; row++) {
                if (col % 2 == 0) {
                    if (matrix[row][col] > colValue) {
                        colValue = matrix[row][col];
                    }
                } else {
                    if (matrix[row][col] < colValue) {
                        colValue = matrix[row][col];
                    }
                }
            }
            sum += colValue;
        }
        return sum;
    }
}