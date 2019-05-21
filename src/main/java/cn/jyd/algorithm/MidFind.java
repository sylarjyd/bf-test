package cn.jyd.algorithm;

import java.util.Arrays;
/**
 * 二分查找，折半查找
 * @author Administrator
 *
 */
public class MidFind {

	public static void main(String[] args) {
		int[] array = {2,4,6,789,4,2,5,8,87,4};
		System.out.println(array);
		int biSearch = biSearch(array,789);
		System.out.println(biSearch);
	}
	
	public static int biSearch(int[] array, int a) {
		int lo = 0;
		int hi = array.length - 1;
		int mid;
		while (lo <= hi) {
			mid = (lo + hi) / 2;// 中间位置
			if (array[mid] == a) {
				return mid + 1;
			} else if (array[mid] < a) { // 向右查找
				lo = mid + 1;
			} else { // 向左查找
				hi = mid - 1;
			}
		}
		return -1;
	}
}
