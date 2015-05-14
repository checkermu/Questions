package com.checker.questions;

import java.util.Stack;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月12日下午8:20:11
 * 中缀表达式变为后缀表达式，并用后缀表达式计算结果 by--gt
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
	 * 对于生成的后缀表达式进行计算
	 * 把数字都放在栈中，非常easy
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
	
	
	/**后缀表达式变中缀表达式——临时只处理个位整数,分隔符变为;即可处理多位
	 * 操作符四个一起处理，左括号和右括号分别处理
	 * @param expression
	 * @return
	 */
	public String InfixToPostfix(String expression){
		expression = expression.replaceAll(" ", "");
		char[] expres = expression.toCharArray();	//两位数以上的整数如何处理
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
							flag=1;		/*//碰到tmp小于pop直接sb.append弹出栈，没有了添加当前tmp这个，因为是个while循环，
							如果在循环内部push那会死循环， 需要放到循环外push,但是如果while外直接push那会与(比较大于的push造成重复)造成重复，
							这样加一个标志位，还是在while循环外push不过进行判断，当时因为当前tmp小于pop的时候，没有push就在外面push一下*/
							break;		//循环，碰到tmp大于等于pop则压栈，就跳出循环，不然一直循环压栈，死循环
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
