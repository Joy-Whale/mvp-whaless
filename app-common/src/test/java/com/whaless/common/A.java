package com.whaless.common;

/**
 * Author: Joy
 * Date:   2018/3/20
 */

public class A {

	/**
	 * 希尔排序的一趟插入
	 * @param arr 待排数组
	 * @param d   增量
	 */
	public static void shellInsert(int[] arr, int d) {
		for (int i = d; i < arr.length; i++) {
			int j = i - d;
			int temp = arr[i];    //记录要插入的数据
			while (j >= 0 & arr[j] > temp) {  //从后向前，找到比其小的数的位置
				arr[j + d] = arr[j];    //向后挪动
				j -= d;
			}

			if (j != i - d)    //存在比其小的数
				arr[j + d] = temp;
		}
	}

	public static void shellSort(int[] arr) {
		if (arr == null || arr.length == 0)
			return;
		int d = arr.length / 2;
		while (d >= 1) {
			shellInsert(arr, d);
			d /= 2;
		}
	}
}
