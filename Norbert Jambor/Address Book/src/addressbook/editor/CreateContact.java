package addressbook.editor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import addressbook.model.AllTitleProvider;
import addressbook.model.Contact;
import addressbook.model.ModelProvider;
import addressbook.view.ContactsView;

import org.eclipse.swt.widgets.Text;

public class CreateContact extends EditorPart {

	public static final String ID = "addressbook.editor.create";

	private AllTitleProvider title;
	private Text id;
	private Text firstName;
	private Text lastName;
	private Text country;
	private Text city;
	private Text street;
	private Text postalCode;
	private Text phoneNumber;
	private Text email;
	private Contact contact;

	public CreateContact() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		contact = new Contact();
		contact.setId(Integer.valueOf(id.getText()));
		contact.setFirstName(firstName.getText().toString());
		contact.setLastName(lastName.getText().toString());
		contact.setCountry(country.getText().toString());
		contact.setCity(city.getText().toString());
		contact.setStreet(street.getText().toString());
		contact.setPostalCode(Integer.valueOf(postalCode.getText()));
		contact.setPhoneNumber(Integer.valueOf(phoneNumber.getText()));
		contact.setFirstName(email.getText().toString());

		//setDirty(false);
		
		List<Contact> contacts = ModelProvider.INSTANCE.getContacts();
		contacts.add(contact);

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ContactsView view = (ContactsView) activePage.findView("addressbook.view.contact");
		view.getViewer().refresh();

		id.setText("");
		firstName.setText("");
		lastName.setText("");
		country.setText("");
		city.setText("");
		street.setText("");
		postalCode.setText("");
		phoneNumber.setText("");
		email.setText("");
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
//		if (dirty)
//	    return true;
//		else
//	    return false;
		return true;
	}

	public void setDirty(boolean isDirty) {
// 		if (this.isDirty() == dirty)
//		return;
//		this.dirty = dirty;
		this.setDirty(isDirty);
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

		new Label(parent, SWT.NONE).setText(title.ID.getText());
		id = new Text(parent, SWT.SINGLE | SWT.BORDER);
		id.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		new Label(parent, SWT.NONE).setText(title.FIRSTNAME.getText());
		firstName = new Text(parent, SWT.SINGLE | SWT.BORDER);
		firstName.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		new Label(parent, SWT.NONE).setText(title.LASTNAME.getText());
		lastName = new Text(parent, SWT.SINGLE | SWT.BORDER);
		lastName.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		new Label(parent, SWT.NONE).setText(title.COUNTRY.getText());
		country = new Text(parent, SWT.SINGLE | SWT.BORDER);
		country.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		new Label(parent, SWT.NONE).setText(title.CITY.getText());
		city = new Text(parent, SWT.SINGLE | SWT.BORDER);
		city.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		new Label(parent, SWT.NONE).setText(title.STREET.getText());
		street = new Text(parent, SWT.SINGLE | SWT.BORDER);
		street.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		new Label(parent, SWT.NONE).setText(title.POSTAL.getText());
		postalCode = new Text(parent, SWT.SINGLE | SWT.BORDER);
		postalCode.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		new Label(parent, SWT.NONE).setText(title.PHONENUMBER.getText());
		phoneNumber = new Text(parent, SWT.SINGLE | SWT.BORDER);
		phoneNumber.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		new Label(parent, SWT.NONE).setText(title.EMAIL.getText());
		email = new Text(parent, SWT.SINGLE | SWT.BORDER);
		email.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
	}

	private void setModel(Contact model) {
		this.contact = model;
	}

	@Override
	public void setFocus() {
	}

	public static void openEditor(Contact model) {
		CreateContactInput input = new CreateContactInput();

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		try {
			CreateContact addressBookEditor = (CreateContact) activePage.getActiveEditor();

			if (addressBookEditor != null) {
				addressBookEditor.setModel(model);
			} else {
				CreateContact newAddress = (CreateContact) activePage.openEditor(input, ID);
				newAddress.setModel(model);
			}

		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}