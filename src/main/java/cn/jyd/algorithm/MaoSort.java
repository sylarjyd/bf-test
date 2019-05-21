package cn.jyd.algorithm;

public class MaoSort {

	public static void main(String[] args) {
		int[] array = {2,4,6,789,4,2,5,8,87,4};
		System.out.println(array);
		bubbleSort1(array);
		System.out.println(array);
	}

	public static void bubbleSort1(int[] a) {
		int i, j;
		int length = a.length;
		for (i = 0; i < length; i++) {// 表示 n 次排序过程。
			for (j = 1; j < length - i; j++) {
				if (a[j - 1] > a[j]) {// 前面的数字大于后面的数字就交换
					// 交换 a[j-1]和 a[j]
					int temp;
					temp = a[j - 1];
					a[j - 1] = a[j];
					a[j] = temp;
				}
			}
		}
	}

}
