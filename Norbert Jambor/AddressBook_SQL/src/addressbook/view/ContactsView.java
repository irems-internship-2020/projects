package addressbook.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import addressbook.model.Address;
import addressbook.model.Contact;
import addressbook.comparator.ContactViewerComparator;
import addressbook.database.DataBaseOperations;
import addressbook.enumLabels.ContactColumnLabels;
import addressbook.filter.ContactFilter;

import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class ContactsView extends ViewPart {
	public static final String ID = "addressbook.view.contact";

	public TableViewer viewer;
	private ContactFilter filter;
	private ContactColumnLabels titleProvider;
    private ContactViewerComparator comparator;
	private DataBaseOperations dataBase = new DataBaseOperations();
	private Statement statement;
	private Contact contact;
	private Address address;
	List<TableViewerColumn> columnList = new ArrayList<TableViewerColumn>();

	public void createPartControl(Composite parent) {
		try {
			statement = dataBase.establishConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		try {
			createViewer(parent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        comparator = new ContactViewerComparator();
        viewer.setComparator(comparator);
        
		searchText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				filter.setSearchText(searchText.getText());
				viewer.refresh();
			}
		});
		filter = new ContactFilter();
		viewer.addFilter(filter);
	}

	private void createViewer(Composite parent) throws SQLException {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		
		getSite().setSelectionProvider(viewer);
		
		setTableInput();
		
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);

		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				Contact firstElement = (Contact) selection.getFirstElement();

				try {
					AddressView view = (AddressView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().showView(AddressView.ID);
					view.setInput(firstElement);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setTableInput() throws SQLException {
		ResultSet result = statement.executeQuery(dataBase.loadDB());
		List<Contact> contacts = new ArrayList<Contact>();
		while (result.next()) {
			contact = new Contact();
			address = new Address();
			contact.setId(result.getInt("contact_id"));
			contact.setFirstName(result.getString("first_name"));
			contact.setLastName(result.getString("last_name"));
			contact.setPhoneNumber(result.getString("phone_number"));
			contact.setEmail(result.getString("email"));
			
			address.setCountry(result.getString("country"));
			address.setCity(result.getString("city"));
			address.setStreet(result.getString("street"));
			address.setPostalCode(result.getString("postal_code"));
			contact.setAddress(address);
			
			contacts.add(contact);
		}
		viewer.setInput(contacts);
		
		viewer.refresh();
	}

	public Contact firstElement() {
		StructuredSelection selection = (StructuredSelection) viewer.getSelection();
		Contact firstElement = (Contact) selection.getFirstElement();
		return firstElement;
	}

	public TableViewer getViewer() {
		return viewer;
	}

	@SuppressWarnings("static-access")
	private void createColumns(final Composite parent, final TableViewer tableViewer) {
		int counter = 0;

		for (ContactColumnLabels title : titleProvider.values()) {
			TableViewerColumn col = createTableViewerColumn(title.getText(), 100, counter);
			columnList.add(col);
			counter++;
		}

		tableViewer.setLabelProvider(new ContactLabelProvider(columnList));
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
        column.addSelectionListener(getSelectionAdapter(column, colNumber));
		return viewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column, final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}