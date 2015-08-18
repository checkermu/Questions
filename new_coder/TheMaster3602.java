package com.gt.newcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月18日下午7:57:29
 * 挑选镇长第二中方法：两个一维数组，arr1表示得到的票数，arr2表示投出的票数
 */
public class TheMaster3602 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner cin = new Scanner(System.in);
		
		while(cin.hasNext()){
			int n = Integer.parseInt(cin.nextLine());
			List<String> resList = new ArrayList<String>();
			for(int i=0; i<n; i++){
				int humanNum = cin.nextInt();
				int reNum = cin.nextInt();
				//用一个String数组,表示关系，——先用二维数组尝试吧,二维数组内存超过限制
				int[] out = new int[humanNum];
				int[] in = new int[humanNum];
				
				
				for(int j=0; j<reNum; j++){
					int index = cin.nextInt(); //关系的起始人
					int relaI = cin.nextInt();//关系认识谁
					if(index != relaI){
						out[index-1]+=1;//投出的票数+1
						in[relaI-1]+=1;//得到的票数+1
					}
				}
				//下面根据二维数组查找村长！
				find(out, in, resList);
			}
			for(int j=0; j<resList.size(); j++){
				System.out.println(resList.get(j));
			}
		}
	}
	
	public static void find(int[] out, int[] in, List<String> lists){
		if(out.length==0){
			lists.add("0");
			lists.add("");
			return;
		}
		List<String> tmpL = new ArrayList<String>();
		for(int i=0; i<out.length; i++){
			if(in[i]==out.length-1 && out[i]==0){
				tmpL.add((i+1)+"");
			}
		}
		if(tmpL.size()==0){
			lists.add("0");
			lists.add("");
		}else{
			lists.add(tmpL.size()+"");
			lists.addAll(tmpL);
		}
	}

}
