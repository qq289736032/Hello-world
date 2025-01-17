package com.jisen.test;

import java.util.Arrays;

public class 二维数组的拷贝 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 原数组声明和初始化
		 * 1、	声明并初始化一个二维数组m，现在栈区开辟一个4字节的空间，然后在堆区开辟两（数组行数为2）个4字节的空间，并将第一个空间的地址放在栈区，
		 * 		最后再从堆区开辟两组空间，
		 * 		第一组空间有3个分别存放1,2,3这三个数据，并将第一个数据的地址保存在前面堆区地址第一个位置上，
		 * 		第二组空间有3个分别存放4,5,6这3个数据，并将第一个数据的地址放在堆区空间地址的第二个位置上
		 */
		int[][] m={
				{1,2,3},
				{4,5,6}
		};
		/*
		 * 浅拷贝1
		 *1、	 声明一个二维数组n，将m中栈区保存的堆区第一个空间的地址赋给n，
		 *2、	将n中的地址加4得到到堆区第二个空间地址，访问该空间保存的地址数据，并将该地址加4，找到n[1][1]这个数据的地址并修改里面的值为100；
		 *结论：数据终究还是m中的数据，n中保存的是m中的地址。通过n取得m数据地址然后改变m中的值。所以最后遍历m，发现m中的元素已经改变；
		 */
//		int[][] n=m;
//		n[1][1]=100;
//		for(int[] v:m){
//			for(int val:v)
//				System.out.print(val+" ");
//			System.out.println();
//		}
		/*
		 * 浅拷贝2、System.arraycopy(m,0, n, 0, m.length);		特别注意该拷贝在一维数组的时候是深拷贝
		 * 2、	声明一个2行3列的二维数组（未初始化），
		 * 		用该拷贝方法，将m中存在堆区的数据m[0]，该数据是指向堆区数据的首地址（即数据m[0][0]的地址），
		 * 		拷贝给n的堆区，n的长度为m.length=2。然后将数据n[1][1](该数据的地址指向m[1][1]);
		 * 		
		 * 3、	利用for（int b:a）形式将a中的数据循环取出，使用两个for嵌套，遍历m二维数组；并得知m[1][1]=100
		 * 
		 * 注意，该拷贝在一维数组是深拷贝，因为已经将原数组的数据一一赋给了目标数组，但是二维数组，复制过去的是原数组的堆区的地址
		 * 
		 * 结论：该拷贝为浅拷贝，遍历之后，原数组改变。
		 * 		
		 */
		int[][] n=new int[2][3];
		System.arraycopy(m,0, n, 0, m.length);
		n[1][1]=100;
		for(int i=0;i<m.length;i++)
			System.out.println(Arrays.toString(m[i]));
		for(int[] v:m){
			for(int val:v)
				System.out.print(val+" ");
			System.out.println();
		}
	}

}
