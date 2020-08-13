package addressbook.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import addressbook.model.AllTitleProvider;

import org.eclipse.swt.widgets.Text;

public class CreateContact extends EditorPart {

	public static final String ID = "addressbook.editor.create";

	private AllTitleProvider titles;

	public CreateContact() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof CreateContactInput)) {
			throw new PartInitException("Invalid Input: Must be " + CreateContactInput.class.getName());
		}
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return true;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@SuppressWarnings("static-access")
	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		GridData data = new GridData(GridData.FILL, GridData.CENTER, true, false);
		data.horizontalSpan = 2;
		parent.setLayoutData(data);

		for (AllTitleProvider title : titles.values()) {
			new Label(parent, SWT.NONE).setText(title.getText());
			Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
			text.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		}
	}

	@Override
	public void setFocus() {
	}

}