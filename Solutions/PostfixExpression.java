package com.checker.questions;

import java.util.Stack;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015��5��12������8:20:11
 * ��׺���ʽ��Ϊ��׺���ʽ�����ú�׺���ʽ������ by--gt
 */
public class PostfixExpression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PostfixExpression pe = new PostfixExpression();
		
//		String expression = "9+(3-1)*3+8/2";
//		String expression = "( 3 + 4 ) * 5 - 6";
//		String expression = "9*(8+2*3)/3+4/(5-3)";
//		pe.InfixToPostfix(expression);
		
//		String postfix = "931-3*+82/+";	//19.0
		String postfix = "9823*+*3/453-/+";	//44.0
		pe.postfixCompute(postfix);
		
	}
	
	/**
	 * �������ɵĺ�׺���ʽ���м���
	 * �����ֶ�����ջ�У��ǳ�easy
	 * @param postfix
	 * @return
	 */
	public float postfixCompute(String postfix){
		char[] expres = postfix.toCharArray();
		Stack<Float> stack = new Stack<Float>();
		float result=(float) 0.0;
		for(int i=0; i<expres.length; i++){
			Character tmp = expres[i];
			if(Character.isDigit(tmp)){
				stack.push(Float.parseFloat(String.valueOf(tmp)));
			}else{
				Float pop1 = stack.pop();
				Float pop2 = stack.pop();
				switch (tmp){
					case '-':
						result = pop2-pop1;
						break;
					case '+':
						result = pop2+pop1;
						break;
					case '*':
						result = pop2*pop1;
						break;
					case '/':
						result = pop2/pop1;
						break;
					default:
						System.out.println("something wrong in your postfix!");
				}
				stack.push(result);
			}
		}
		System.out.println("result:"+result);
		return result;
	}
	
	
	/**��׺���ʽ����׺���ʽ������ʱֻ�����λ����,�ָ�����Ϊ;���ɴ����λ
	 * �������ĸ�һ���������ź������ŷֱ���
	 * @param expression
	 * @return
	 */
	public String InfixToPostfix(String expression){
		expression = expression.replaceAll(" ", "");
		char[] expres = expression.toCharArray();	//��λ�����ϵ�������δ���
		Stack<Character> stack=new Stack<Character>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<expres.length; i++){
			Character tmp = expres[i];
			if(tmp=='('){
				stack.push(tmp);
			}else if(tmp =='-'||tmp =='+'||tmp =='*'||tmp =='/'){
				if(stack.empty()){
					stack.push(tmp);
				}else{
					int  flag=0;
					while(!stack.empty()){
						if(compreTwo(tmp, stack.peek())){
							stack.push(tmp);
							flag=1;		/*//����tmpС��popֱ��sb.append����ջ��û������ӵ�ǰtmp�������Ϊ�Ǹ�whileѭ����
							�����ѭ���ڲ�push�ǻ���ѭ���� ��Ҫ�ŵ�ѭ����push,�������while��ֱ��push�ǻ���(�Ƚϴ��ڵ�push����ظ�)����ظ���
							������һ����־λ��������whileѭ����push���������жϣ���ʱ��Ϊ��ǰtmpС��pop��ʱ��û��push��������pushһ��*/
							break;		//ѭ��������tmp���ڵ���pop��ѹջ��������ѭ������Ȼһֱѭ��ѹջ����ѭ��
						}else{
							sb.append(String.valueOf(stack.pop()));
						}
					}
					if(flag==0){
						stack.push(tmp);
					}
					/*else{
						stack.push(tmp);
					}*/
				}
			}else if(tmp==')'){
				while(stack.peek()!='('){
					sb.append(String.valueOf(stack.pop()));
				}
				stack.pop();
			}else{
				sb.append(String.valueOf(tmp));
			}
		}
		while(!stack.empty()){
			sb.append(String.valueOf(stack.pop()));
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public boolean compreTwo(char tmp, char top){
		if((tmp=='-'||tmp=='+') && (top=='*'||top=='/')){
			return false;
		}else if((tmp=='*'||tmp=='/') && (top=='-'||top=='+')){
			return true;
		}else if((tmp=='-'||tmp=='+' || tmp=='*'||tmp=='/') && top=='('){
			return true;
		}else{
			return false;
		}
	}
}
