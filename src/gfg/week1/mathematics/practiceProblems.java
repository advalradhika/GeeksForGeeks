package gfg.week1.mathematics;

import java.math.BigInteger;
import java.util.Arrays;

public class practiceProblems {

	public int absolute(int I) {
		return I > 0 ? I : I * -1;
	}

	public double cToF(int C) {
		return (C * 9 / 5) + 32;
	}

	public void quadraticRoots(int a, int b, int c) {
		System.out.println((int) ((-b + Math.sqrt(b * b - 4 * a * c))));
		System.out.print((int) ((-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a));
		System.out.print(" ");
		System.out.print((int) ((-b - Math.sqrt(b * b - 4 * a * c)) / 2 * a));
	}

	public long factorial(int N) {
		long res = 1;
		int i = 2;
		while (i <= N) {
			res = res * i;
			i++;
		}
		return res;
	}

	/*
	 * We know, log(a*b) = log(a) + log(b)
	 * 
	 * Therefore log( n! ) = log(1*2*3....... * n) = log(1) + log(2) + ........
	 * +log(n)
	 * 
	 * Now, observe that the floor value of log base 10 increased by 1, of any
	 * number, gives the number of digits present in that number.
	 * 
	 * Hence, output would be : floor(log(n!)) + 1.
	 * 
	 */
	public int digitsInFactorial(int N) {
		if (N < 0)
			return 0;

		// base case
		if (N <= 1)
			return 1;

		double digits = 0;
		for (int i = 2; i <= N; i++)
			digits += Math.log10(i);

		return (int) (Math.floor(digits)) + 1;
	}

	public double termOfGP(int A, int B, int N) {
		if (N == 1)
			return A;
		if (N == 2)
			return B;
		else
			return A * Math.pow((B), N - 1) / Math.pow((A), N - 1);
	}

	public boolean isPrime(int N) {
		if (N == 1)
			return false;
		for (int i = 2; i <= Math.sqrt(N); i++) {
			if (N % i == 0)
				return false;
		}
		return true;

	}

	/////// Needs to be perfect square and square root should be prime
	public int exactly3Divisors(int N) {
		int counter = 0;
		N = (int) Math.sqrt(N);

		for (int i = 1; i <= N; i++) {
			if (isPrime(i))
				counter++;
		}
		return counter;
	}

	public static long sumUnderModulo(long a, long b) {
		int M = 1000000007;
		// (a+b)%m=(a%m+b%m)%m
		return (a % M + b % M) % M;
	}

	static long multiplicationUnderModulo(long a, long b) {
		// add your code here
		int M = 1000000007;
		// (a*b)%m=((a%m)*(b%m))%m
		return ((a % M) * (b % M)) % M;
	}

	public int modInverse(int a, int m) {
		a = a % m;
		for (int x = 1; x < m; x++)
			if ((a * x) % m == 1)
				return x;
		return -1;
	}
}
