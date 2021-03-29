package gfg.week1.mathematics;

import java.util.Arrays;

public class videoSolutions {

	public int countTrailingZeroes(int number) {
		// Time Complexity : O(log n)
		int res = 0;
		for (int i = 5; i <= number; i = i * 5) {
			res = res + number / i;
		}
		return res;
	}

	public int GCD(int a, int b) {
		////// GCD(a,b) is GCD(a-b,b) if b<a
		// Time Complexity : O(log n)
		///////// Euclidean Algorithm
		while (a != b) {
			if (a < b)
				b = b - a;
			else
				a = a - b;
		}
		return a;
		///////// Extended Euclidean Algorithm
		// Time Complexity : O(log min(a,b))
		/*
		 * if (b == 0) { return a;
		 * 
		 * } else { return GCD(b, a % b); }
		 */
	}

	public int LCM(int a, int b) {
		return (a * b) / GCD(a, b);
	}

	public boolean isPrime(int num) {
		// Time Complexty : O(sqrt(n))
		//// Checking for 2 and 3 will remove many iterations
		if (num == 1)
			return false;
		if (num == 2 || num == 3)
			return true;
		if (num % 2 == 0 || num % 3 == 0)
			return false;
		for (int i = 5; i < Math.sqrt(num); i = i + 6) {
			if (num % i == 0 || num % (i + 2) == 0)
				return false;
		}
		return true;
	}

	public void printPrimeFactors(int num) {
		//// Time Complexity : O(sqrt(n))
		if (num <= 1)
			return;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			while (num % i == 0) {
				System.out.println(i);
				num /= i;
			}
		}
		if (num > 1) {
			System.out.println(num);
		}
	}

	public void printAllDivisors(int num) {
		//// Time Complexity : O(sqrt(n))
		int i;
		for (i = 0; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				System.out.println(i);
			}
		}
		for (; i >= 1; i--) {
			if (num % i == 0) {
				System.out.println(num / i);
			}
		}

	}

	public void seive(int n) {
		/// print prime number equal to / smaller than num
		//// Time Complexity : O(n log log n)
		if (n <= 1)
			return;
		boolean isPrime[] = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		for (int i = 0; i < Math.sqrt(n); i++) {
			if (isPrime(i)) {
				for (i = i; i < n; i = i + i) {
					//// Can used i*i in plac of 2*i
					isPrime[i] = false;
				}
			}
		}
		for (int i = 2; i <= n; i++) {
			if (isPrime[i])
				System.out.println(i);
		}
	}

	public int computePower(int base, int power) {
		if (power == 0)
			return 1;
		int temp = computePower(base, power / 2);
		temp = temp * temp;
		if (power % 2 == 0)
			return temp * temp;
		else
			return temp * base;

	}
}
