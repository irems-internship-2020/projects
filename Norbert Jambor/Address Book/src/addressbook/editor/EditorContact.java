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

public class EditorContact extends EditorPart {

	public static final String ID = "addressbook.editor.edit";

	private AllTitleProvider titles;

	public EditorContact() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof EditorContactInput)) {
			throw new PartInitException("Invalid Input: Must be " + EditorContactInput.class.getName());
		}
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
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