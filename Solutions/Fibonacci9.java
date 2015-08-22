package com.gt.sword;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月22日上午10:42:18
 * 斐波那契数列的一些解法
 */
public class Fibonacci9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 最普通的递归解法
	 * @param n
	 * @return
	 */
	public static long funRecu(int n){
		if(n<=0)
			return 0;
		if(n==1)
			return 1;
		return funRecu(n-1)+funRecu(n-2);
	}
	
	/**
	 * 利用了三个数互相追赶增加； tmp=A+B; B=A; A=tmp;
	 * @param n
	 * @return
	 */
	public static long funArray(int n){
		int[] res ={1,2};
		if(n<2)
			return res[n];
		long fibOne =1;
		long fibTwo = 0;
		long fibN = 0;
		for(int i=2; i<=n; i++){
			fibN = fibOne+fibTwo;
			fibTwo = fibOne;
			fibOne = fibN;
		}
		return fibN;
	}
}
