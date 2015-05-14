package com.checker.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月4日下午9:00:11
 * 笛卡尔积的计算，如果是两个集合乘积，那么结果的第一个对象为X成员，第二个对象是Y的成员的所有可能有序对。
 * 集合个数可以随意
 */
public class CartesianProduct {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list01 = new ArrayList<String>();
		List<String> list02 = new ArrayList<String>();
		list01.add("A");list01.add("B");list01.add("C");
		list02.add("M");list02.add("N");
		List<List<String>> dimValue = new ArrayList<List<String>>();
		dimValue.add(list01); dimValue.add(list02);
		List<String> result = new ArrayList<String>();
		productImplement(dimValue, result, 0, "");
		int i=1;
		for(String s:result){
			System.out.println(i++ +":"+s);
		}
	}
	
	/**
	 * 笛卡尔积实现的递归函数
	 * @param dimValue	多重List，几个集合再组装成List
	 * @param result	返回的结果
	 * @param layer		当前函数执行到第几层—对应集合组装成List的size(i)
	 * @param curString	当前构成结果的过渡String对象。
	 */
	public static  void productImplement(List<List<String>> dimValue, List<String> result, int layer, String curString){
		//递归要考虑终止条件，终止条件就是当前layer是否为最后一个集合
		if(layer<dimValue.size()-1){
			for(int i=0; i<dimValue.get(layer).size(); i++){
				StringBuilder sb = new StringBuilder();
				sb.append(curString);
				sb.append(dimValue.get(layer).get(i));
				//上面把当前的加进去了，要去后面的一个集合，那么就要递归了
				productImplement(dimValue, result, layer+1, sb.toString());
			}
		}else if(layer==dimValue.size()-1){
			for(int j=0; j<dimValue.get(layer).size(); j++){
				result.add(curString+dimValue.get(layer).get(j));
			}
		}
	}

}
