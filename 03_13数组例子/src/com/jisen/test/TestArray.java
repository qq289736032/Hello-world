package com.jisen.test;

import java.lang.reflect.Array;


public class TestArray {

	/**
	 * Array���ṩ�˶�̬�����ͷ��� Java ����ķ���
	 */
	public static void main(String[] args) {
		
		Object arr = Array.newInstance(Integer.class, 5);
		Object arr2 = Array.newInstance(int.class, 5);
		Array.set(arr, 0, 1);
		
		
		int[] a;
		a=new int[3];
		a[0]=1;
		a[1]=2;
		a[2]=3;
		int[] b=new int[4];
		int[] c={4,5,6};
		
		System.out.println(a[2]);
		System.out.println(a);//�����ӡ�׵�ַ��ӡ��������������ĵ�ַ
		System.out.println(b.length);
		System.out.println(b[b.length-1]);
		//两种遍历方式
		System.out.println(c[0]+" "+c[1]+" "+c[2]);
		for(int val:c)
			
			System.out.print(val+"");
		
		
	}

}
