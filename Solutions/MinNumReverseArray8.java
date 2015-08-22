package com.gt.sword;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月22日上午10:18:44
 * 
 * 旋转数组的最小数字，比如顺序表，1,2,3,4,5旋转后3,4,5,1,2，求出最小的
 * 
 */
public class MinNumReverseArray8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {5,6,1,2,3,4};
		int[] arr1 = {4,5,6,1,2};
		int[] arr2 = {1,0,1,1,1};
		System.out.println(findMin(arr));
		System.out.println(findMin(arr1));
		System.out.println(findMin(arr2));
	}
	
	/**
	 * 思路，当然可以顺序循环一遍得到最小的，但是时间复杂度O(n)肯定不是最优的
	 * 这种查找都可以使用二分法，想想如何利用二分法呢？
	 * 双指针法，一个指向头，一个指向尾部，一开始一般是头部大于等于尾部的(也有特例)
	 */

	public static int findMin(int[] arr){
		if(arr==null||arr.length<=0)
			return Integer.MIN_VALUE;
		int index1 = 0;
		int index2 = arr.length-1;
		int minIndex =index1;
		
		while(arr[index1]>=arr[index2]){
			if(index2-index1==1){
				minIndex = index2;
				break;
			}
			int mid = (index1+index2)/2;
			//注意数组出现重复元素的情况，如果三个下标相同，则顺序查找
			if(arr[index1]==arr[index2]&& arr[index2]==arr[minIndex]){
				return findMinOrder(arr, index1, index2);
			}
			//否则，二分下去即可
			if(arr[mid]>=arr[index1]){
				index1 = mid;
			}else if(arr[mid]<=arr[index2]){
				index2 = mid;
			}
			
		}
		return arr[minIndex];
		
	}
	
	public static int findMinOrder(int[] arr, int be, int ed){
		int result = arr[be];
		for(int i=be; i<ed; i++){
			if(result>arr[i])
				result = arr[i];
		}
		return result;
	}
	
}
