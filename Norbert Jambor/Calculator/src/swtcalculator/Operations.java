package swtcalculator;

import static swtcalculator.EnumOperation.Operation.DIVISION;
import static swtcalculator.EnumOperation.Operation.MINUS;
import static swtcalculator.EnumOperation.Operation.MULTIPLICATION;
import static swtcalculator.EnumOperation.Operation.PLUS;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Text;

class Operations {

	private String firstOperation;
	private double firstNumberDouble;
	private double secondNumberDouble;
	private String answer;
	private List<Integer> twoOperations;
	private Double result = 0.0;
	private boolean finish = false;

	protected void calculateEquals(Text inputText) {
		answer = inputText.getText();
		finish = false;
		while (!finish) {
			getFirstNumberAndOperationAndSecondNumber();
			calculateResult();
			setInputText(inputText);
		}
	}

	private void getFirstNumberAndOperationAndSecondNumber() {
		String firstNumber;
		String secondNumber;
		takeFristAndSecondOperationsIndex();
		firstNumber = answer.substring(0, twoOperations.get(0));
		firstOperation = answer.substring(twoOperations.get(0), twoOperations.get(0) + 1);
		secondNumber = answer.substring(twoOperations.get(0) + 1, twoOperations.get(1));
		firstNumberDouble = Double.parseDouble(firstNumber);
		secondNumberDouble = Double.parseDouble(secondNumber);
	}

	private void takeFristAndSecondOperationsIndex() {
		twoOperations = new ArrayList<>();
		int i;
		char answerChar;
		int counter = 0;
		for (i = 0; i < answer.length(); i++) {
			answerChar = answer.charAt(i);
				if (answerChar == '+' || answerChar == '-' || answerChar == '*' || answerChar == '/') {
					twoOperations.add(i);
					counter++;
					if (counter == 2) {
						break;
					}
				}
			}
		if (twoOperations.size() == 1) {
			twoOperations.add(i);
		}
	}

	private void calculateResult() {
		switch (firstOperation) {
		case "+":
			result = firstNumberDouble + secondNumberDouble;
			break;
		case "-":
			result = firstNumberDouble - secondNumberDouble;
			break;
		case "*":
			result = firstNumberDouble * secondNumberDouble;
			break;
		case "/":
			result = firstNumberDouble / secondNumberDouble;
			break;
		default:
			break;
		}
	}

	private void setInputText(Text inputText) {
		answer = result + answer.substring(twoOperations.get(1), answer.length());
		if (!answer.contains("+") && !answer.contains("-") && !answer.contains("*") && !answer.contains("/")) {
			finish = true;
			inputText.setText(inputText.getText() + " = " + result);
		}
	}

	protected void createOperationButtons(Text inputText, String forOperation) {
		String firstInput;
		firstInput = inputText.getText();
		String lastOperation;
		lastOperation = firstInput.substring(firstInput.length() - 1, firstInput.length());

		if (!lastOperation.equals(PLUS.getText()) && !lastOperation.equals(MINUS.getText())
				&& !lastOperation.equals(MULTIPLICATION.getText())
				&& !lastOperation.equals(DIVISION.getText())) {
			inputText.setText(firstInput + forOperation);
		} else {
			inputText.setText(firstInput);
		}
	}

}
