package addressBook.editor;

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
import addressBook.tableviewer.model.Address;
import addressBook.tableviewer.model.Contact;
import addressBook.tableviewer.model.ModelProvider;

public class Editor extends EditorPart {

	public static final String ID = "RCPAddressBookApp.editor.create";
	private boolean dirty = false;
	private Contact contact;
	private Address address;
	private Text userId;
	private Text userName;
	private Text userLname;
	private Text userCountry;
	private Text userCity;
	private Text userStreet;
	private Text userPCode;
	private Text userPNumber;
	private Text userEmail;

	public Editor() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		setDirty(false);
		contact = new Contact();
		address = new Address();
		contact.setId(Integer.valueOf(userId.getText()));
		contact.setFirstName(userName.getText().toString());
		contact.setLastName(userLname.getText().toString());
		address.setCountry(userCountry.getText().toString());
		address.setCity(userCity.getText().toString());
		address.setStreet(userStreet.getText().toString());
		address.setPostalCode(userPCode.getText().toString());
		contact.setPhoneNumber(userPNumber.getText().toString());
		contact.setEmail(userEmail.getText().toString());
		contact.setAddress(address);
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

		createIdWidget(parent);
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

	private void createIdWidget(Composite parent) {
		new Label(parent, SWT.NONE).setText("Email:");
		userId = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userId.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		userId.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setDirty(true);
			}
		});
	}

	@Override
	public void setFocus() {

	}

	private void setModelAndWidget(Contact contact) {
		this.contact = contact;
	}

	public static void openEditor(Contact contactsView) {
		UserCreate input = new UserCreate();

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

}
