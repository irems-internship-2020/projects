package addressbook.editor;

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

import addressbook.enumLabels.AllColumnsLabels;
import addressbook_server.JpaOperations;
import addressbook.model.Address;
import addressbook.model.Contact;
import addressbook.view.ContactsView;

import org.eclipse.swt.widgets.Text;

@SuppressWarnings("static-access")
public class Editor extends EditorPart {

	public static final String ID = "addressbook.editor.create";

	private AllColumnsLabels title;
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
	private JpaOperations jpaOperations = new JpaOperations();

	public Editor() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {

		if (isCreate) {
			contact = new Contact();
			address = new Address();
		}
		else {
			contact = jpaOperations.updateJpa(contact);
		}

		contact.setFirstName(firstName.getText());
		contact.setLastName(lastName.getText());
		address.setCountry(country.getText());
		address.setCity(city.getText());
		address.setStreet(street.getText());
		address.setPostalCode(postalCode.getText());
		contact.setPhoneNumber(phoneNumber.getText());
		contact.setEmail(email.getText());
		contact.setAddress(address);
		
		if (isCreate) {
			jpaOperations.beginTransaction();
			jpaOperations.insertJpa(contact);
		} else {
			jpaOperations.closeTransaction();
		}

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ContactsView view = (ContactsView) activePage.findView("addressbook.view.contact");
		view.viewer.setInput(jpaOperations.loadJpa());
		view.getViewer().refresh();

		setEmptyModel();

		setDirty(false);
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof EditorInput)) {
			throw new PartInitException("Invalid Input: Must be " + EditorInput.class.getName());
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

		createFirstNameWidget(parent);

		createLastNameWidget(parent);

		createCountryWidget(parent);

		createCityWidget(parent);

		createStreetWidget(parent);

		createPostalCodeWidget(parent);

		createPhoneNumberWidget(parent);

		createEmailWidget(parent);
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

	private void createEmailWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText(title.EMAIL.getText());
		email = createTextWidget(parent);
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

		EditorInput input = new EditorInput();

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		try {
			Editor addressBookEditor = (Editor) activePage.getActiveEditor();

			if (addressBookEditor != null) {
				addressBookEditor.setModel(model);
			} else {
				Editor newAddress = (Editor) activePage.openEditor(input, ID);
				newAddress.setModel(model);
			}

		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	public static void openCreateEditor() {
		isCreate = true;

		EditorInput input = new EditorInput();

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		try {
			Editor addressBookEditor = (Editor) activePage.getActiveEditor();

			if (addressBookEditor != null) {
				addressBookEditor.setEmptyModel();
			} else {
				@SuppressWarnings("unused")
				Editor newAddress = (Editor) activePage.openEditor(input, ID);
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}