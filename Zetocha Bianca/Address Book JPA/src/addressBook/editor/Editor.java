package addressBook.editor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import Address.Book.Views.ContactView;
import addressBook.database.DataBaseCRUD;
import addressBook.tableviewer.model.Address;
import addressBook.tableviewer.model.Contact;
import addressBook.tableviewer.model.ModelProvider;

public class Editor extends EditorPart {

	public static final String ID = "RCPAddressBookApp.editor.create";
	private boolean dirty = false;
	private Contact contact;
	private Address address;
	private Text userName;
	private Text userLname;
	private Text userCountry;
	private Text userCity;
	private Text userStreet;
	private Text userPCode;
	private Text userPNumber;
	private Text userEmail;
	private static boolean isOpen = false;
	private DataBaseCRUD dataBase = new DataBaseCRUD();

	public Editor() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (isOpen) {
			contact = new Contact();
			address = new Address();
		}else {
			contact = dataBase.dataBaseUpdate(contact);
		}
		contact.setFirstName(userName.getText().toString());
		contact.setLastName(userLname.getText().toString());

		address.setCountry(userCountry.getText().toString());
		address.setCity(userCity.getText().toString());
		address.setStreet(userStreet.getText().toString());
		address.setPostalCode(userPCode.getText().toString());
		if (userPNumber.getText().toString() != "") {
			contact.setPhoneNumber(userPNumber.getText());
		} else {
			contact.setPhoneNumber("No phone number was received");
		}
		contact.setEmail(userEmail.getText().toString());
		contact.setAddress(address);

//		if (isOpen) {
//			List<Contact> contacts = ModelProvider.INSTANCE.getContacts();
//			contacts.add(contact);
//		}

		if (isOpen) {
			dataBase.beginTransaction();
			dataBase.dataBaseInsert(contact);
		} else {
			dataBase.closeTransaction();
		}
		
		
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ContactView view = (ContactView) activePage.findView("RCPAddressBookApp.view");
		view.tableViewer.setInput(dataBase.dataBaseLoad());
		view.getViewer().refresh();
		
		userName.setText("");
		userLname.setText("");
		userCountry.setText("");
		userCity.setText("");
		userStreet.setText("");
		userPCode.setText("");
		userPNumber.setText("");
		userEmail.setText("");

		setDirty(false);
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof UserCreate)) {
			throw new PartInitException("Invalid Input: Must be " + UserCreate.class.getName());
		}
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		if (dirty) {
			return true;
		} else {
			return false;
		}
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		parent.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData.horizontalSpan = 2;
		parent.setLayoutData(gridData);

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
		new Label(parent, SWT.NONE).setText("Email:");
		userEmail = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userEmail.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		userEmail.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setDirty(true);
			}
		});
	}

	private void createPhoneNumberWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText("Phone Number:");
		userPNumber = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userPNumber.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		userPNumber.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setDirty(true);
			}
		});
	}

	private void createPostalCodeWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText("Postal Code");
		userPCode = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userPCode.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		userPCode.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setDirty(true);
			}
		});
	}

	private void createStreetWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText("Street");
		userStreet = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userStreet.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		userStreet.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setDirty(true);
			}
		});
	}

	private void createCityWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText("City:");
		userCity = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userCity.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		userCity.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setDirty(true);
			}
		});
	}

	private void createLastNameWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText("Last Name:");
		userLname = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userLname.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		userLname.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setDirty(true);
			}
		});
	}

	private void createCountryWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText("Country:");
		userCountry = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userCountry.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		userCountry.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setDirty(true);
			}
		});
	}

	private void createFirstNameWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText("First Name:");
		userName = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userName.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		userName.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setDirty(true);
			}
		});
	}

	@Override
	public void setFocus() {

	}

	private void setModelAndWidgetEmpty() {
		userName.setText("");
		userLname.setText("");
		userCountry.setText("");
		userCity.setText("");
		userStreet.setText("");
		userPCode.setText("");
		userPNumber.setText("");
		userEmail.setText("");

		setDirty(false);

	}

	private void setModelAndWidget(Contact contact) throws Exception {
		this.contact = contact;
		address = contact.getAddress();
		setDirty(false);

		userName.setText(contact.getFirstName());
		userLname.setText(contact.getLastName());
		userCountry.setText(address.getCountry());
		userCity.setText(address.getCity());
		userStreet.setText(address.getStreet());
		userPCode.setText(address.getPostalCode().toString());
		userPNumber.setText(contact.getPhoneNumber().toString());
		userEmail.setText(contact.getEmail());
		contact.setAddress(address);

	}

	public static void openEditor(Contact contactsView) throws Exception {
		isOpen = false;
		UserCreate input = new UserCreate();
		DataBaseCRUD database = new DataBaseCRUD();

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		try {
			Editor addressBookEditor = (Editor) activePage.getActiveEditor();

			if (addressBookEditor != null) {
				addressBookEditor.setModelAndWidget(contactsView);
			} else {
				Editor newAddress = (Editor) activePage.openEditor(input, ID);
				newAddress.setModelAndWidget(contactsView);
			}

		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	public static void openCreateEditor(Contact contactsView) {
		isOpen = true;
		UserCreate input = new UserCreate();

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		try {
			Editor addressBookEditor = (Editor) activePage.getActiveEditor();

			if (addressBookEditor != null) {
				addressBookEditor.setModelAndWidgetEmpty();
			} else {
				@SuppressWarnings("unused")
				Editor newAddress = (Editor) activePage.openEditor(input, ID);

			}

		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
