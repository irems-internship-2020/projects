package addressBook.Views;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.addressBook.AddressBook;
import com.addressBook.model.Address;
import com.addressBook.model.Contact;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import addressBook.bean.DatabaseHandler;
import addressBook.comparator.MyViewerComparator;
import addressBook.enume.ColumTitle;
import addressBook.filter.ContactFilter;

public class ContactView extends ViewPart {

	public static final String ID = "RCPAddressBookApp.view";

	private MyViewerComparator comparator;
	public TableViewer tableViewer;
	private ContactFilter filter;
	private DatabaseHandler db = new DatabaseHandler();
	private Statement statement;
	private Contact contact;
	private Address address;
	private ColumTitle titleProvider;
	List<TableViewerColumn> columnList = new ArrayList<TableViewerColumn>();

	public void createPartControl(Composite parent) {
				
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		try {
			createViewer(parent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		comparator = new MyViewerComparator();
		tableViewer.setComparator(comparator);

		searchText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				filter.setSearchText(searchText.getText());
				tableViewer.refresh();
			}
		});
		filter = new ContactFilter();
		tableViewer.addFilter(filter);
	}

	private void createViewer(Composite parent) throws Exception {
		tableViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, tableViewer);
		final Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		
		tableViewer.setContentProvider(new ArrayContentProvider());
		getSite().setSelectionProvider(tableViewer);
		tableViewer.setInput(db.getBean().dataBaseLoad());
	

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		tableViewer.getControl().setLayoutData(gridData);

		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				Contact firstElement = (Contact) selection.getFirstElement();

				try {
					DetalisView view = (DetalisView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().showView(DetalisView.ID);
					view.setInput(firstElement);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TableViewer getViewer() {
		return tableViewer;
	}

	public Contact firstElement() {
		StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
		Contact firstElement = (Contact) selection.getFirstElement();
		return firstElement;
	}

	@SuppressWarnings("static-access")
	// This will create the columns for the table
	private void createColumns(final Composite parent, final TableViewer tableViewer) {
		int count = 0;

		for (ColumTitle title : titleProvider.values()) {
			TableViewerColumn col = createTableViewerColumn(title.getTxt(), 100, count);
			columnList.add(col);
			count++;
		}

		tableViewer.setLabelProvider(new LabelProviderContact(columnList));
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
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
				tableViewer.getTable().setSortDirection(dir);
				tableViewer.getTable().setSortColumn(column);
				tableViewer.refresh();
			}
		};
		return selectionAdapter;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}

}