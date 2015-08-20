package com.gt.question;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月19日下午10:09:36
 * 两个有序数组里面查找第k个数
 */
public class TwoArrayTheKths {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int[] A = {1, 8};  
        int[] B = {5,  9, 22, 110};
        findKth(A, B, 4);
		/*int[] A = {1, 8, 8, 10, 20};  
        int[] B = {5, 8, 8, 9, 22, 110};  */
        int k=4;
        int pa = Math.min(A.length, k-1);
       System.out.println(findKthRecu(A, B, pa,(pa+1)/2, k));
	}
	
	//复杂度为O(k)的解法，两个指针
	//复杂度是O(k)
	//idea:两个指针法，
	public static int findKth(int[] A, int[] B, int k) throws Exception{
		if(A.length+B.length <k || k<1)
			throw new Exception("k is out ");
		
		//两个 pointer
		int pa=0;
		int pb=0;
		
		int kV = 0;
		//为何是！=k呢，因为，得到一个Ai和Bi后我们认为K是哪个小的，这样每次都是到while判断的时候正好得到第i个小的数
		//如果改成大于的话会出问题！
		while(pa+pb!=k){
			int Ai = (pa==A.length)?Integer.MAX_VALUE:A[pa];
			int Bi = (pa==B.length)?Integer.MAX_VALUE:B[pb];
			
			if(Ai<Bi){
				pa++;
				kV = Ai;
			}else{
				pb++;
				kV = Bi;
			}
		}
		System.out.println("KV:"+kV);
		
		return kV;
	}
	
	
	//思想二：用到二分法的思想，
	//同时重点是pa+pb=k-1 且 B[pb]<A[pa]&&B[pb]>A[pa-1] 或者A[pa]<B[pb]&&A[pa]>B[pb-1]
	public static int findKthRecu(int[] A, int[] B,int pa, int delta, int k){
		int pb = (k-1)-pa;
		
		//得到A[pa],B[pb] 和 A[pa-1],B[pb-1]
		int Ai_1 = (pa==0)?Integer.MIN_VALUE:A[pa-1];
		int Bj_1 = (pb==0)?Integer.MIN_VALUE:B[pb-1];
		
		int Ai = (pa==A.length)?Integer.MAX_VALUE:A[pa];
		int Bj = (pb==B.length)?Integer.MAX_VALUE:B[pb];
		
		//满足其中条件之一的就返回
		if(Bj_1 <= Ai && Ai<=Bj)	return Ai;
		if(Ai_1 <= Bj && Bj<=Ai)	return Bj;
		
		
		//delta 表示pa的变化量（增加或减少）
		//如果Ai>Bj 要缩小pa的值，即pa=pa-delta
		//因为pb=(k-1)-pa，所以如果delta的值太大，那么pa会变得很小，因而导致pb>B.length
		//所以要处理下，对于pa = pa+delta也是一样
		
		if(Ai > Bj){
			pa=((k-1)-(pa-delta) >B.length)?k-1-B.length:pa-delta;
			return findKthRecu(A, B, pa, (delta+1)/2, k);
		}else{
			pa = (pa+delta > A.length)?A.length:pa+delta;
			return findKthRecu(A, B, pa, (delta+1)/2, k);
		}
		
	}
	
	/**
	 * 方法二思想：还是两个指针，pa+pb = k-1; 有点二分法的思想
	 * 这样pa或者pb就可能是第K大的数，但是这样的pa,pb很多，什么样的才符合条件呢？
	 * 根据pa和pb我们能得到 Ai,Ai_1; Bj, Bj_1;共四个数，如果这四个数满足
	 * (1)、Ai<=Bj && Ai>=Bj_1，则Ai为第k大的数
	 * (2)、Bj<=Ai && Bj >= Ai_1， 则Bj为第k大的数
	 * 
	 * 初始我们取pa 为 Min{A.length, k-1},因为有点二分法的思想，所以开始从大点的开始往下分,delta = (pa+1)/2
	 * 那么pb = (k-1)-pa；这样我们计算上面四个值，如果满足则结束，如果不满足呢？
	 * 不满足：
	 * 	1、Ai>Bj，这样也意味着 Ai_1 > Bj，因为不然就达到条件了；
	 * 		所以在这里Ai是太大了，要减小pa,减小多少呢？减小pa的目的是增大pb，但是不能让pb>B.length了，
	 * 		所以减少pa的准则是不能让pb大于B的长度，pa =( (k-1)-(pa-delta) )>B.length?B.length:pa-delta;
	 * 	2、Ai<Bj，这样也意味着 Ai<Bj_1;，因为不然就达到条件了
	 * 		所以在这里来说Ai是太小了，要增大pa，增大只要不超过A.length即可
	 * 		pa = pa+delta>A.length?A.length:pa+delta;
	 * 
	 * 注意 delta都会(delta+1)/2；
	 * 继续调用递归函数
	 * 
	 */
	
	public static int findK(int[] A, int[] B, int pa, int delta, int k){
		if(A.length + B.length <k || k<=0)
			return -1;
		
		int pb = (k-1)-pa;
		//四个值
		int Ai_1 = (pa==0)?Integer.MIN_VALUE:A[pa-1];
		int Bj_1 = (pb==0)?Integer.MIN_VALUE:B[pb-1];
		int Ai = (pa==A.length)?Integer.MAX_VALUE:A[pa];
		int Bj = (pb==B.length)?Integer.MAX_VALUE:B[pb];
		
		if(Ai<=Bj&&Ai>Bj_1) return Ai;
		if(Bj<=Ai&&Bj>Ai_1) return Bj;
		
		if(Ai<Bj){//也意味着 Ai<Bj_1，Ai应该向前，
			pa = pa+delta>A.length?A.length:pa+delta;
			return findK(A,B,pa, (delta+1)/2, k);	
		}else{//也就是Ai>Bj同时意味着Ai>Bj_1，Ai应该后退
			//后退导致pb增大，但是不能超过B.length，所以
			pa = ((k-1)-(pa-delta))>B.length?(k-1-B.length):(pa-delta);
			return findK(A, B, pa, (delta+1)/2, k);
		}
	}
	
}
