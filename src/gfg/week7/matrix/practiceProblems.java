package gfg.week7.matrix;

import java.util.ArrayList;

public class practiceProblems {

	public static void main(String[] args) {
		int[][] mat = { { 6, 5, 4, 9 }, { 1, 2, 5 }, { 7, 9, 7 } };
		// System.out.println(sumTriangles(mat, 3));
		int A[][] = { { 1, 1, 1 }, { 2, 2, 2 }, { 3, 3, 3 }, { 4, 4, 4 } };
		int matrix[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		int B[][] = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 } };
		// int matrix[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13,
		// 14, 15, 16 } };
		System.out.println(spirallyTraverse(matrix, 4, 4));
		// System.out.println(mat.length);
	}

	public static int[][] sumMatrix(int A[][], int B[][]) {
		if (A.length == B.length && A[0].length == B[0].length) {
			for (int i = 0; i < A.length; i++) {
				for (int j = 0; j < A[0].length; j++) {
					A[i][j] += B[i][j];
				}
			}
			return A;
		} else {
			int[][] temp = new int[1][1];
			temp[0][0] = -1;
			return temp;
		}
	}

	static ArrayList<Integer> sumTriangles(int matrix[][], int n) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int uppersum = 0, lowersum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				uppersum += matrix[i][j];
			}
			for (int j = 0; j < i + 1; j++) {
				lowersum += matrix[i][j];
			}
		}
		res.add(uppersum);
		res.add(lowersum);
		return res;
	}

	static int[][] multiplyMatrix(int A[][], int B[][]) {
		int row1 = A.length;
		int row2 = B.length;
		int col1 = A[0].length;
		int col2 = B[0].length;
		if (col1 == row2) {
			int[][] temp = new int[row1][col2];
			for (int i = 0; i < row1; i++) {
				for (int j = 0; j < col2; j++) {
					for (int k = 0; k < row2; k++) {
						temp[i][j] += A[i][k] * B[k][j];
					}
				}
			}
			return temp;
		} else {
			int[][] temp = new int[1][1];
			temp[0][0] = -1;
			return temp;
		}
	}

	static ArrayList<Integer> snakePattern(int matrix[][]) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < matrix.length; i++) {
			if (i % 2 == 0) {
				for (int j = 0; j < matrix[i].length; j++) {
					res.add(matrix[i][j]);
				}
			} else {
				for (int j = matrix[i].length - 1; j >= 0; j--) {
					res.add(matrix[i][j]);
				}
			}
		}
		return res;
	}

	static void transpose(int matrix[][], int n) {
		int temp;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}

	static void rotateby90(int matrix[][], int n) {
		transpose(matrix, n);
		int temp;
		for (int i = 0; i < n; i++) {
			int low = 0, high = n - 1;
			while (low < high) {
				temp = matrix[low][i];
				matrix[low][i] = matrix[high][i];
				matrix[high][i] = temp;
				low++;
				high--;
			}
		}
	}

	static int determinantOfMatrix(int matrix[][], int n) {
		for (int i = 0; i < n; i++) {

		}
		return 0;
	}

	static ArrayList<Integer> boundaryTraversal(int matrix[][], int n, int m) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (n == 1) {
			for (int i = 0; i < m; i++) {
				res.add(matrix[0][i]);
			}
		} else if (m == 1) {
			for (int i = 0; i < n; i++) {
				res.add(matrix[i][0]);
			}
		} else {
			for (int i = 0; i < m; i++) {
				res.add(matrix[0][i]);
			}
			for (int i = 1; i < n; i++) {
				res.add(matrix[i][m - 1]);
			}
			for (int i = m - 2; i >= 0; i--) {
				res.add(matrix[n - 1][i]);
			}
			for (int i = n - 2; i > 0; i--) {
				res.add(matrix[i][0]);
			}
		}
		return res;
	}

	static void exchangeColumns(int matrix[][]) {
		int n = matrix.length;
		int m = matrix[0].length;
		int temp;
		for (int i = 0; i < n; i++) {
			temp = matrix[i][0];
			matrix[i][0] = matrix[i][m - 1];
			matrix[i][m - 1] = temp;
		}
	}

	static ArrayList<Integer> spirallyTraverse(int matrix[][], int r, int c) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int top = 0, bottom = r - 1, left = 0, right = c - 1;
		while (top <= bottom && left <= right) {
			for (int i = left; i <= right; i++) {
				res.add(matrix[top][i]);
			}
			top++;
			for (int i = top; i <= bottom; i++) {
				res.add(matrix[i][right]);
			}
			right--;
			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					res.add(matrix[bottom][i]);
				}
				bottom--;
			}
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					res.add(matrix[i][left]);
				}
				left++;
			}
		}
		return res;
	}

	static void reverseCol(int matrix[][]) {
		int low, high, temp;
		for (int i = 0; i < matrix.length; i++) {
			low = 0;
			high = matrix[i].length - 1;
			while (low < high) {
				temp = matrix[i][low];
				matrix[i][low] = matrix[i][high];
				matrix[i][high] = temp;
				low++;
				high--;
			}
		}
	}

	static void interchangeRows(int matrix[][]) {
		int low, high, temp;
		for (int i = 0; i < matrix[0].length; i++) {
			low = 0;
			high = matrix.length - 1;
			while (low < high) {
				temp = matrix[low][i];
				matrix[low][i] = matrix[high][i];
				matrix[high][i] = temp;
				low++;
				high--;
			}
		}
	}

	static boolean search(int matrix[][], int n, int m, int x) {
		int row = 0, col = m - 1;
		while (row < n && col >= 0) {
			if (matrix[row][col] == x) {
				return true;
			} else if (matrix[row][col] > x) {
				col--;
			} else {
				row++;
			}
		}
		return false;
	}

	void booleanMatrix(int matrix[][]) {
		int row = matrix.length;
		int col = matrix[0].length;
		int rowmat[] = new int[row];
		int colmat[] = new int[col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 1) {
					rowmat[i] = 1;
					colmat[j] = 1;
				}
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (rowmat[i] == 1 || colmat[j] == 1) {
					matrix[i][j] = 1;
				}
			}
		}
	}

	static int findMinOperation(int matrix[][], int n) {
		int res = 0;
		return res;
	}

}