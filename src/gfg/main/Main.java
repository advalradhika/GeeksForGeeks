package gfg.main;

import gfg.week20.dynamicProgramming.Others;

public class Main {

	public static void main(String[] args) {
		Others other = new Others();
		int[] arr = { 2, 3, 5, 8,11 };
		System.out.println(other.checkSubsetForGivenSumDP(arr, 11, 4));
	}

}
