package com.jisen;
/**
 * �ܽ�
 * 1�����������ñ����ݽ�ȥֵ�ᱻ�ı䣬���ǵ���ֵ����ȥ���ᱻ�ı�
 * 2��char���������ֱ�ӱ���������������������������hashֵ
 * @author Administrator
 *
 */
public class ValuePass {
	char cr ='d';
	char[] ch = {'a','b','c'};
	int a = 1;
	int[] ach = {2,3};
	public static void main(String[] args) {
		ValuePass pass = new ValuePass();
		pass.change(pass.a, pass.ach);
		pass.change(pass.cr, pass.ch);
		
		System.out.println(pass.cr);
		System.out.println(pass.ch);
		System.out.println(pass.a);
		System.out.println(pass.ach[0]);
	}
	public void change(char cr,char ch[]){
		cr = 'e';
		ch[0] = 'f';
	}
	public void change(int a,int ach[]){
		a = 4;
		ach[0] = 5;
	}
}
