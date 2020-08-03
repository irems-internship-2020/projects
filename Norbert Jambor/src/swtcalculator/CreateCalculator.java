package swtcalculator;

import static swtcalculator.EnumOperation.Operation.DIVISION;
import static swtcalculator.EnumOperation.Operation.MINUS;
import static swtcalculator.EnumOperation.Operation.MULTIPLICATION;
import static swtcalculator.EnumOperation.Operation.PLUS;

import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

class CreateCalculator extends Operations {

	private String number;
	double result;
	Text inputText;
	private Group numbersGroup;
	private Group inputGroup;
	private Shell shell;

	CreateCalculator() {
		createNewWindow();
	}

	private void createNewWindow() {
		final Display display = new Display();

		shell = new Shell(display);
		shell.setSize(400, 500);
		shell.setText("Calculator");
		shell.setLayout(new GridLayout(1, false));

		createCalculatorUI();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private void createCalculatorUI() {
		inputGroupUI();
		createInputTextUI();
		numbersGroupUI();
		makeCalculatorUI();
	}

	private void makeCalculatorUI() {
		
		final List<String> calculatorList = Arrays.asList(DIVISION.getText(),"DEL", "C",
				PLUS.getText(),
				MINUS.getText(),
				MULTIPLICATION.getText(),
				"7", "8", "9", "4", "5", "6", "1", "2", "3", ".", "0", "=");
		
		for (final String item : calculatorList) {
			final Button button = new Button(numbersGroup, SWT.PUSH);
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			button.setText(item);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					switch (item) {
					case "C":
						inputText.setText("");
						break;
					case ".":
						inputText.setText(inputText.getText() + ".");
						break;
					case "=":
						calculateEquals(inputText);
						break;
						
					case "+":
					case "-":
					case "*":
					case "/":
						createOperationButtons(inputText, item);
						break;
						
					default:
						number = inputText.getText() + button.getText();
						inputText.setText(number);
						
						break;
					
					}
				}
			});
		}
	}

	private void inputGroupUI() {
		inputGroup = new Group(shell, SWT.NONE);
		inputGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		inputGroup.setLayout(new GridLayout());
	}

	private void numbersGroupUI() {
		numbersGroup = new Group(shell, SWT.NONE);
		numbersGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		numbersGroup.setLayout(new GridLayout(3, true));
	}

	private void createInputTextUI() {
		inputText = new Text(inputGroup, 0 | SWT.RIGHT);
		inputText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				e.doit = false;
			}
		});
		inputText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		inputText.setFont(new Font(inputText.getDisplay(), "Arial", 16, SWT.BOLD));
	}
}