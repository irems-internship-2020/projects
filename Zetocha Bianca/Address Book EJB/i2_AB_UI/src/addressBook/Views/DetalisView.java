package addressBook.Views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.addressBook.model.Contact;

import addressBook.bean.DatabaseHandler;
import addressBook.enume.AddressTitle;
import addressBook.model.ModelProvider;

public class DetalisView extends ViewPart {

	public static final String ID = "RCPAddressBookApp.detalis";

	private TableViewer tableViewer;
	private AddressTitle addressProvider;
	List<TableViewerColumn> columnList = new ArrayList<TableViewerColumn>();
	private DatabaseHandler dataBase = new DatabaseHandler();
	
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		createViewer(parent);
	}

	public void setInput(Contact firstElement) {
		tableViewer.setInput(Arrays.asList(firstElement));
		tableViewer.refresh();
	}

	private void createViewer(Composite parent) {
		tableViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, tableViewer);
		final Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer.setContentProvider(new ArrayContentProvider());

		getSite().setSelectionProvider(tableViewer);

		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		tableViewer.getControl().setLayoutData(gridData);
	}

	public TableViewer getViewer() {
		return tableViewer;
	}

	@SuppressWarnings("static-access")
	// This will create the columns for the table
	private void createColumns(final Composite parent, final TableViewer tableViewer) {
		int count = 0;

		for (AddressTitle address : addressProvider.values()) {
			TableViewerColumn col = createTableViewerColumn(address.getTxt(), 100, count);
			columnList.add(col);
			count++;
		}

		tableViewer.setLabelProvider(new LabelProviderAddress(columnList));
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}

}
