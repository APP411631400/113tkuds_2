public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] M1 = {{1,2},{3,4}};
        int[][] M2 = {{5,6},{7,8}};
        int[][] sum = add(M1, M2);
        int[][] product = multiply(M1, M2);
        int[][] trans = transpose(M1);
        System.out.println("Sum:");
        printMatrix(sum);
        System.out.println("Product:");
        printMatrix(product);
        System.out.println("Transpose:");
        printMatrix(trans);
        System.out.println("Max: " + findMax(M1));
        System.out.println("Min: " + findMin(M1));
    }

    public static int[][] add(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] c = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        int common = a[0].length;
        int[][] c = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                c[i][j] = 0;
                for (int k = 0; k < common; k++)
                    c[i][j] += a[i][k] * b[k][j];
            }
        return c;
    }

    public static int[][] transpose(int[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] t = new int[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                t[j][i] = a[i][j];
        return t;
    }

    public static int findMax(int[][] a) {
        int max = a[0][0];
        for (int[] row : a)
            for (int val : row)
                if (val > max) max = val;
        return max;
    }

    public static int findMin(int[][] a) {
        int min = a[0][0];
        for (int[] row : a)
            for (int val : row)
                if (val < min) min = val;
        return min;
    }

    public static void printMatrix(int[][] a) {
        for (int[] row : a) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }
}

