package gfg.week7.matrix;

public class videoSolutions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrix[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		printSpiral(matrix, 4, 4);
	}

	public static void printSnake(int mat[][]) {
		int R = mat.length;
		int C;
		for (int i = 0; i < R; i++) {
			C = mat[i].length;
			if (i % 2 == 0) {
				for (int j = 0; j < C; j++)
					System.out.print(mat[i][j] + " ");
			} else {
				for (int j = C - 1; j >= 0; j--)
					System.out.print(mat[i][j] + " ");
			}

		}
	}

	public static void bTraversal(int mat[][]) {
		int R = mat.length;
		int C = mat[0].length;
		if (R == 1) {
			for (int i = 0; i < C; i++)
				System.out.print(mat[0][i] + " ");
		} else if (C == 1) {
			for (int i = 0; i < R; i++)
				System.out.print(mat[i][0] + " ");
		} else {
			for (int i = 0; i < C; i++)
				System.out.print(mat[0][i] + " ");
			for (int i = 1; i < R; i++)
				System.out.print(mat[i][C - 1] + " ");
			for (int i = C - 2; i >= 0; i--)
				System.out.print(mat[R - 1][i] + " ");
			for (int i = R - 2; i >= 1; i--)
				System.out.print(mat[i][0] + " ");
		}
	}

	static void transposeNaive(int mat[][]) {
		int n = mat.length;
		int temp[][] = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				temp[i][j] = mat[j][i];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				mat[i][j] = temp[i][j];
	}

	static void swap(int mat[][], int i, int j) {
		int temp = mat[i][j];
		mat[i][j] = mat[j][i];
		mat[j][i] = temp;
	}

	static void transposeEfficient(int mat[][]) {
		int n = mat.length;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				swap(mat, i, j);
	}

	static void rotateAntiClockwise(int[][] mat) {
		int n = mat.length;
		int[][] temp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp[n - j - 1][i] = mat[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = temp[i][j];
			}
		}
	}

	static void rotateAntiClockwiseEfficient(int mat[][]) {
		int n = mat.length;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[j][i];
				mat[j][i] = temp;
			}
		for (int i = 0; i < n; i++) {
			int low = 0, high = n - 1;
			while (low < high) {
				int temp = mat[low][i];
				mat[low][i] = mat[high][i];
				mat[high][i] = temp;
				low++;
				high--;
			}
		}
	}

	static void printSpiral(int mat[][], int R, int C) {
		int top = 0, left = 0, bottom = R - 1, right = C - 1;
		while (top <= bottom && left <= right) {
			for (int i = left; i <= right; i++)
				System.out.print(mat[top][i] + " ");
			top++;

			for (int i = top; i <= bottom; i++)
				System.out.print(mat[i][right] + " ");
			right--;

			if (top <= bottom) {
				for (int i = right; i >= left; i--)
					System.out.print(mat[bottom][i] + " ");
				bottom--;
			}

			if (left <= right) {
				for (int i = bottom; i >= top; i--)
					System.out.print(mat[i][left] + " ");
				left++;
			}
		}
	}

	public static int searchElement(int[][] mat, int x) {
		int R = mat.length;
		int C = mat[0].length;
		int row = 0, col = C - 1;
		while (row < R && col >= 0) {
			if (mat[row][col] == x) {
				System.out.println("row : " + row + " col : " + col);
				return 0;
			} else if (mat[row][col] < x) {
				row++;
			} else {
				col--;
			}
		}
		return -1;
	}
}
