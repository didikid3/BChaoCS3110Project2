
import java.util.Stack;
public class expressionHandle {

	
	public int evalEx(String input) {
		
		int size = input.length();
		char curr;
		//My factors w/o including '('expression')'
		Stack<Integer> values = new Stack<Integer>();
		//My operators
		Stack<Character> operator = new Stack<Character>();
		
		for(int i = 0; i < size; i++) {
			curr = input.charAt(i);
			
			//If its a numeric value
			if(curr >= '0' && curr <= '9') {
				String x = "";
				
				//Group all the numbers into one string
				while (i < size && curr >= '0' && curr <= '9') {
					x += curr;
					i+= 1;
				}
				
				//Maybe call my floatPointFunctionHere?
				
				//Add value into stack
			}
			
			
			else if(curr == '(') {
				operator.push(curr);
			}
			//When parenthesis close, evaluate the expression
			else if(curr == ')') {
				//Continue until a open Parenthesis is seen
				while (operator.peek() != '(') {
					values.push(
							applyOperation( operator.pop(),
											values.pop(),
											values.pop()));
				}
				operator.pop();
			}
			
			else if(curr == '+' || curr == '-' || curr == '*' || curr == '/') {
				while(!operator.empty() && hasPrecedence(curr, operator.peek())) {
					values.push(
							applyOperation( operator.pop(),
											values.pop(),
											values.pop()));
				}
				
				operator.push(curr);
			}
			
		}
		
		while(!operator.empty()) {
			values.push(applyOperation(operator.pop(),
										values.pop(),
										values.pop()));
		}
		
		
		return values.pop();
	}
}
