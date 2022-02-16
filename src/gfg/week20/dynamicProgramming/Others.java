package gfg.week20.dynamicProgramming;

import java.util.Arrays;

public class Others {

	/**
	 * 
	 * @param weight - weight of available items
	 * @param value  - value of respective item
	 * @param cap    - capacity of knapsack
	 * @param n      - number of items
	 * @return - maximum possible value in filling knapsack
	 */
	int knapsackUtil(int[] weight, int[] value, int W, int n) {
		int dp[][] = new int[101][101];
		Arrays.fill(dp, -1);
		return knapsackDP(weight, value, W, n, dp);
	}

	int knapsackRec(int[] weight, int[] value, int W, int n) {
		if (n == 0 || W == 0)
			return 0;
		else if (W < weight[n - 1])
			return knapsackRec(weight, value, W, n - 1);
		else
			return (Math.max(knapsackRec(weight, value, W, n - 1),
					value[n - 1] + knapsackRec(weight, value, W - weight[n - 1], n - 1)));
	}

	int knapsackDP(int[] weight, int[] value, int W, int n, int[][] dp) {
		if (n == 0 || W == 0)
			return 0;
		if (dp[n][W] != -1)
			return dp[n][W];
		else if (W < weight[n - 1])
			dp[n][W] = knapsackDP(weight, value, W, n - 1, dp);
		else
			dp[n][W] = (Math.max(knapsackDP(weight, value, W, n - 1, dp),
					value[n - 1] + knapsackDP(weight, value, W - weight[n - 1], n - 1, dp)));
		return dp[n][W];
	}

	int knapsackDP(int[] weight, int[] value, int W, int n) {
		int[][] dp = new int[n + 1][W + 1];
		for (int i = 0; i < n + 1; i++)
			dp[i][0] = 0;
		for (int j = 0; j < W + 1; j++)
			dp[0][j] = 0;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < W + 1; j++) {
				if (weight[i - 1] <= j) {
					dp[i][j] = Math.max(value[i - 1] + dp[i - 1][j - weight[i - 1]], dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][W];
	}

	/**
	 * 
	 * @param arr - array of elements
	 * @param sum - sum of picked elements
	 * @param n   - number of elements
	 * @return - if it's possible to pick a subset that sums up to given sum
	 */
	public boolean checkSubsetForGivenSumRec(int[] arr, int sum, int n) {
		if (sum == 0)
			return true;
		else if (n == 0)
			return false;
		else if (arr[n - 1] <= sum)
			return checkSubsetForGivenSumRec(arr, sum - arr[n - 1], n - 1)
					|| checkSubsetForGivenSumRec(arr, sum, n - 1);
		else
			return checkSubsetForGivenSumRec(arr, sum, n - 1);
	}

	public boolean checkSubsetForGivenSumDP(int[] arr, int sum, int n) {
		boolean[][] dp = new boolean[n + 1][sum + 1];
		for (int i = 0; i < n + 1; i++) {
			dp[i][0] = true;
		}
		for (int j = 1; j < sum + 1; j++) {
			dp[0][j] = false;
		}
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < sum + 1; j++) {
				if (arr[i - 1] <= j) {
					dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][sum];
	}

	/**
	 * 
	 * @param arr - array of elements
	 * @param sum - sum of picked elements
	 * @param n   - number of elements
	 * @return - if it's possible to make two subsets with equal sum
	 */
	public boolean checkEqualSumPartition(int[] arr, int sum, int n) {
		int totalSum = 0;
		for (int i = 0; i < n; i++)
			totalSum += arr[i];

		if (totalSum % 2 != 0)
			return false;
		else
			return checkSubsetForGivenSumDP(arr, totalSum / 2, n);
	}

	public int countSubsetWithGivenSum(int[] arr, int sum, int n) {
		int[][] dp = new int[n + 1][sum + 1];
		for (int i = 0; i < n + 1; i++) {
			dp[i][0] = 1;
		}
		for (int j = 1; j < sum + 1; j++) {
			dp[0][j] = 0;
		}
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < sum + 1; j++) {
				if (arr[i - 1] <= j) {
					dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][sum];
	}

}
