package com.gt.newcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月18日下午3:49:41
 * 360内推题目：选镇长，
 */
public class TheMaster360 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		
		while(cin.hasNext()){
			int n = Integer.parseInt(cin.nextLine());
			List<String> resList = new ArrayList<String>();
			for(int i=0; i<n; i++){
				int humanNum = cin.nextInt();
				int reNum = cin.nextInt();
				//用一个String数组,表示关系，——先用二维数组尝试吧
//				String[] relations = new String[reNum+1];//关系是从1开始，并非0
				int[][] relaArr = new int[humanNum][humanNum];
				
				for(int j=0; j<reNum; j++){
					int index = cin.nextInt(); //关系的起始人
					int relaI = cin.nextInt();//关系认识谁
					
					relaArr[index-1][relaI-1] = 1;
					
				}
				
				//下面根据二维数组查找村长！
				findMaster(relaArr, resList);
				
			}
			for(int j=0; j<resList.size(); j++){
				System.out.println(resList.get(j));
			}
			
		}
		
		
	}

	/**
	 * 如果用二维数组来表示的话，
	 * 对i来说，这一列不管自身，其余的都是1，这一行，不管自身，其余的都是0；
	 */
	
	public static void findMaster(int[][] arr, List<String> lists){
		if(arr.length==0){
			lists.add("0");
			lists.add("");
			return;
		}
		
		int n = arr.length;
		List<String> tmpList = new ArrayList<String>();
		for(int i=0; i<n; i++){
			int product = 1;//积
			for(int j=0; j<n; j++){
				if(i!=j && product==1){
					product = product*arr[j][i];
				}else
					continue;
			}
			int add=0;//和
			if(product==1){//列满足条件
				for(int j=0; j<n; j++){
					if(i!=j && add==0){
						add += arr[i][j];
					}
				}
				if(add==0){//现在 列满足条件，行业满足条件，则加入
					tmpList.add((i+1)+"");
				}
			}
		}
		int num = tmpList.size();
		if(num==0){
			lists.add("0");
			lists.add("");
		}else{
			lists.add(num+"");
			lists.addAll(tmpList);
		}
	}
}
