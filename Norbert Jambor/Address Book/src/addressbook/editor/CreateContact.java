package addressbook.editor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

import addressbook.model.Address;
import addressbook.model.AllTitleProvider;
import addressbook.model.Contact;
import addressbook.model.ModelProvider;
import addressbook.view.ContactsView;

import org.eclipse.swt.widgets.Text;

@SuppressWarnings("static-access")
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
	private Address address;
	private boolean dirty = false;
	private static boolean isCreate = false;

	public CreateContact() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (isCreate) {
			contact = new Contact();
			address = new Address();
		}

		if (id.getText().toString() != "")
			contact.setId(Integer.valueOf(id.getText()));
		else {
			contact.setId(0);
		}
		contact.setFirstName(firstName.getText().toString());
		contact.setLastName(lastName.getText().toString());
		address.setCountry(country.getText().toString());
		address.setCity(city.getText().toString());
		address.setStreet(street.getText().toString());
		if (postalCode.getText().toString() != "")
			address.setPostalCode(Integer.valueOf(postalCode.getText()));
		else {
			address.setPostalCode(0);
		}
		if (phoneNumber.getText().toString() != "") {
			contact.setPhoneNumber(Integer.valueOf(phoneNumber.getText()));
		} else {
			contact.setPhoneNumber(0);
		}
		contact.setEmail(email.getText().toString());
		contact.setAddress(address);

		if (isCreate) {
			List<Contact> contacts = ModelProvider.INSTANCE.getContacts();
			contacts.add(contact);
		}

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ContactsView view = (ContactsView) activePage.findView("addressbook.view.contact");
		view.getViewer().refresh();

		setEmptyModel();
		
		setDirty(false);
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
		if (dirty)
			return true;
		else
			return false;
	}

	public void setDirty(boolean isDirty) {
		this.dirty = isDirty;
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

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

		createFirstNameWidget(parent);

		createLastNameWidget(parent);

		createCountryWidget(parent);

		createCityWidget(parent);

		createStreetWidget(parent);

		createPostalCodeWidget(parent);

		createPhoneNumberWidget(parent);

		createEmailWidget(parent);
	}

	private void createEmailWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText(title.EMAIL.getText());
		email = createTextWidget(parent);
	}
	
	private Text createTextWidget(Composite parent) {
		Text textWidget = new Text(parent, SWT.SINGLE | SWT.BORDER);
		textWidget.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		textWidget.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				setDirty(true);
			}
		});
		return textWidget;
	}

	private void createPhoneNumberWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText(title.PHONENUMBER.getText());
		phoneNumber = createTextWidget(parent);
	}

	private void createPostalCodeWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText(title.POSTAL.getText());
		postalCode = createTextWidget(parent);
	}

	private void createStreetWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText(title.STREET.getText());
		street = createTextWidget(parent);
	}

	private void createCityWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText(title.CITY.getText());
		city = createTextWidget(parent);
	}

	private void createCountryWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText(title.COUNTRY.getText());
		country = createTextWidget(parent);
	}

	private void createLastNameWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText(title.LASTNAME.getText());
		lastName = createTextWidget(parent);
	}

	private void createFirstNameWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText(title.FIRSTNAME.getText());
		firstName = createTextWidget(parent);
	}

	private void setModel(Contact model) {
		contact = model;
		address = model.getAddress();
		setDirty(false);

		id.setText(model.getId().toString());
		firstName.setText(model.getFirstName());
		lastName.setText(model.getLastName());
		country.setText(model.getAddress().getCountry());
		city.setText(model.getAddress().getCity());
		street.setText(model.getAddress().getStreet());
		postalCode.setText(model.getAddress().getPostalCode().toString());
		phoneNumber.setText(model.getPhoneNumber().toString());
		email.setText(model.getEmail());
	}

	private void setEmptyModel() {
		setDirty(false);
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
	public void setFocus() {
	}

	public static void openEditor(Contact model) {
		isCreate = false;

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

	public static void openCreateEditor() {
		isCreate = true;

		CreateContactInput input = new CreateContactInput();

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		try {
			CreateContact addressBookEditor = (CreateContact) activePage.getActiveEditor();

			if (addressBookEditor != null) {
				addressBookEditor.setEmptyModel();
			} else {
				@SuppressWarnings("unused")
				CreateContact newAddress = (CreateContact) activePage.openEditor(input, ID);
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}