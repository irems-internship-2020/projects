package CalcPack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CalculatorUI {
	protected Shell shell;
	public TextWidget newDisplay = new TextWidget();
	private String numberString = "";
	private int parant = 0;
	private boolean operand = false;

	void createNewWindow() {
		Display display = new Display();

		shell = new Shell(display);
		shell.setText("Calculator");
		shell.setSize(300, 400);
		shell.setLayout(new GridLayout(4, false));

		createContent();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		display.dispose();
	}

	private void createContent() {

		newDisplay.createTextWidget(shell);
		createButtons();

	}

	private void createButtons() {
		buttonData("(");
		buttonData(")");
		buttonData("C");
		buttonData("DEL");

		buttonData("7");
		buttonData("8");
		buttonData("9");
		buttonData("/");

		buttonData("4");
		buttonData("5");
		buttonData("6");
		buttonData("*");

		buttonData("1");
		buttonData("2");
		buttonData("3");
		buttonData("-");

		buttonData("0");
		buttonData(".");
		buttonData("=");
		buttonData("+");
	}

	private void buttonData(String strNum) {
		Button btn = new Button(shell, SWT.PUSH);
		CalculatorOperation calc = new CalculatorOperation();
		btn.setText(strNum);
		btn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		btn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				switch (btn.getText()) {

				case "+":
				case "-":
				case "*":
				case "/":
					if (!operand)
						numberString = numberString.concat(calc.buttonOP(btn));
					operand = true;
					break;

				case "(":
					parant++;
					numberString = numberString.concat(calc.buttonParant(btn));
					break;

				case ")":
					if (parant > 0) {
						numberString = numberString.concat(calc.buttonParant(btn));
						parant--;
					}

					break;

				case "=":

					if (!operand) {
						numberString = calc.buttonEqual(numberString);

					}
					calc.clearTheList();

					break;

				case "C":
					numberString = calc.clearTheList();
					break;

				case "DEL":

					numberString = calc.ClearOneElem(numberString);
					break;

				default:
					operand = false;
					if (numberString == "0")
						numberString = btn.getText();
					else {
						if (!operand) {
							numberString = numberString.concat(calc.DefaultSwitch(btn));
						}
					}
					break;
				}

				newDisplay.setTextDisplay(numberString);
				calc.clearTheList();

			}

		});
	}

}
