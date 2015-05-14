package com.checker.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015��5��4������9:00:11
 * �ѿ������ļ��㣬������������ϳ˻�����ô����ĵ�һ������ΪX��Ա���ڶ���������Y�ĳ�Ա�����п�������ԡ�
 * ���ϸ�����������
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
	 * �ѿ�����ʵ�ֵĵݹ麯��
	 * @param dimValue	����List��������������װ��List
	 * @param result	���صĽ��
	 * @param layer		��ǰ����ִ�е��ڼ��㡪��Ӧ������װ��List��size(i)
	 * @param curString	��ǰ���ɽ���Ĺ���String����
	 */
	public static  void productImplement(List<List<String>> dimValue, List<String> result, int layer, String curString){
		//�ݹ�Ҫ������ֹ��������ֹ�������ǵ�ǰlayer�Ƿ�Ϊ���һ������
		if(layer<dimValue.size()-1){
			for(int i=0; i<dimValue.get(layer).size(); i++){
				StringBuilder sb = new StringBuilder();
				sb.append(curString);
				sb.append(dimValue.get(layer).get(i));
				//����ѵ�ǰ�ļӽ�ȥ�ˣ�Ҫȥ�����һ�����ϣ���ô��Ҫ�ݹ���
				productImplement(dimValue, result, layer+1, sb.toString());
			}
		}else if(layer==dimValue.size()-1){
			for(int j=0; j<dimValue.get(layer).size(); j++){
				result.add(curString+dimValue.get(layer).get(j));
			}
		}
	}

}
