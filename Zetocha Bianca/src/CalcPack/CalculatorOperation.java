package CalcPack;

import java.util.Stack;

import org.eclipse.swt.widgets.Button;

public class CalculatorOperation {

	private String expression = "";

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String clearTheList() {
		setExpression("");
		return getExpression();

	}

	public String deleteTheLastElem() {
		return (getExpression() == null || getExpression().length() == 0) ? null
				: (getExpression().substring(0, getExpression().length() - 1));
	}

	public String evaluate(String expression) {
		char[] tokens = expression.toCharArray();

		// Stack for numbers: 'values'
		Stack<Integer> values = new Stack<Integer>();

		// Stack for Operators: 'ops'
		Stack<Character> ops = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++) {
			// Current token is a whitespace, skip it
			if (tokens[i] == ' ')
				continue;

			// Current token is a number, push it to stack for numbers
			if (tokens[i] >= '0' && tokens[i] <= '9') {
				StringBuffer sbuf = new StringBuffer();
				// There may be more than one digits in number
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
					sbuf.append(tokens[i++]);
				values.push(Integer.parseInt(sbuf.toString()));
			}

			// Current token is an opening brace, push it to 'ops'
			else if (tokens[i] == '(')
				ops.push(tokens[i]);

			// Closing brace encountered, solve entire brace
			else if (tokens[i] == ')') {
				while (ops.peek() != '(')
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				ops.pop();
			}

			// Current token is an operator.
			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
				// While top of 'ops' has same or greater precedence to current
				// token, which is an operator. Apply operator on top of 'ops'
				// to top two elements in values stack
				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));

				// Push current token to 'ops'.
				ops.push(tokens[i]);
			}
		}

		// Entire expression has been parsed at this point, apply remaining
		// ops to remaining values
		while (!ops.empty())
			values.push(applyOp(ops.pop(), values.pop(), values.pop()));

		
		// Top of 'values' contains result, return it
		return String.valueOf(values.pop());
	}

	// Returns true if 'op2' has higher or same precedence as 'op1',
	// otherwise returns false.
	public static boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	// A utility method to apply an operator 'op' on operands 'a'
	// and 'b'. Return the result.
	public static int applyOp(char op, int b, int a) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;

		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}

	public String buttonOP(Button btn) {

		setExpression(getExpression().concat(" " + btn.getText() + " "));

		return getExpression();

	}

	public String buttonParant(Button btn) {

		if (getExpression() == "0")
			setExpression(btn.getText());
		else
			setExpression(getExpression().concat(" " + btn.getText()));
		return getExpression();
	}

	public String buttonEqual(String numberString) {
		setExpression(evaluate(numberString));
		return getExpression();
	}

	public String ClearOneElem(String numberString) {
		if (numberString.length() >= 1)
			numberString = numberString.substring(0, numberString.length() - 1);
		if (numberString.length() <= 0)
			numberString = "0";

		setExpression(numberString);
		return numberString;
	}

	public String DefaultSwitch(Button btn) {
		test(btn);
		return getExpression();

	}

	private void test(Button btn) {
		setExpression(getExpression().concat(btn.getText()));
	}

}
