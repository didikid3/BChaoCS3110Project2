
import java.util.Scanner;
import java.util.Stack;

public class expressionHandle {

	
	public double[] evalEx(String input) {
		
		double[] result = new double[2];
		result[0] = 1;//Failed or Pass
		result[1] = 0;//Value of Calculation
		int size = input.length();
		char curr;
		//My factors w/o including '('expression')'
		Stack<Double> values = new Stack<>();
		//My operators
		Stack<Character> operator = new Stack<>();
		floatPointValue machine = new floatPointValue();
		for(int i = 0; i < size; i++) {
			curr = input.charAt(i);
			
			//If its a numeric value
			//Now Handle 
			if((curr >= '0' && curr <= '9') || curr == '.') {
				
				//Run modified floatPointLiteral generator (modified)
				String temp = input.substring(i);
				double[] floatPointResults = machine.isValid(temp);
				i += floatPointResults[1] - 1;
				values.push(floatPointResults[0]);
				if(floatPointResults[0] == -1) {
					result[0] = -1;
					return result;
				}
				
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
					if(values.empty()) {
						result[0] = -1;
						return result;
					}
					else {
						double x1 = values.pop();
						if(values.empty()) {
							result[0] = -1;
							return result;
						}
						else {
							double x2 = values.pop();
							values.push(
									applyOperation( operator.pop(),
													x1,
													x2
													)
									);
						}
					}
					
				}
				//Handles Last input is in +-*/ State
				if(i == size -1) {
					result[0] = -1;
					return result;
					}
				else
					operator.push(curr);
			}
			
		}
		
		while(!operator.empty()) {
			values.push(
					applyOperation(operator.pop(),
										values.pop(),
										values.pop()
										)
					);
		}
		
		result[1] = values.pop();
		return result;
	}
	
	
	public double applyOperation(char operator, double val1, double val2) {
		switch(operator) {
		case '+':
			return val1 + val2;
		case '-':
			return val1 - val2;
		case '*':
			return val1 * val2;
		case '/':
			return val1/val2;
		}
		
		return 0;
	}
	
	public boolean hasPrecedence( char operator1, char operator2) {
		if( operator2 == '(' || operator2 == ')') {
			return false;
		}
		if((operator1 == '*' || operator1 == '/')&&
				(operator2 == '+' || operator2 == '-')) {
			return false;
		}
		else
			return true;
	}
	
	public static void main(String[] args) {
		
		expressionHandle x = new expressionHandle();
		Scanner scan = new Scanner(System.in);
		System.out.println("Brandon Chao Expression Handler");
		System.out.println("Type 'exit' to terminate");
		System.out.print(">>>");
		String input = scan.next();
		while(!input.equals("exit")) {
			double[] values = x.evalEx(input);
			if(values[0] != -1)
				System.out.println(values[1]);
			else
				System.out.println(input + " is not a valid expression");
			System.out.print(">>>");
			input = scan.next();
		}
		scan.close();
	}
}
