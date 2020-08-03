package CalcPack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextWidget {

	private Text text;

	public void createTextWidget(Shell shell) {
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.RIGHT);
		text.setText("0");
		text.setTextLimit(30);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

	}

	public void setTextDisplay(String value) {
		text.setText(value);
		text.setTextLimit(30);
	}

}
