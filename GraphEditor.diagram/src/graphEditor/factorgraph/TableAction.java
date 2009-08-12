package graphEditor.factorgraph;

import graphEditor.Factornode;
import graphEditor.Graph;
import graphEditor.Node;
import graphEditor.Variablenode;
import graphEditor.diagram.edit.parts.GraphEditPart;
import graphEditor.diagram.edit.parts.VariablenodeEditPart;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellResizeListener;
import de.kupzog.ktable.KTableCellSelectionListener;
import de.kupzog.ktable.SWTX;

public class TableAction implements IObjectActionDelegate {

	/**
	 * @generated
	 */
	private IWorkbenchPart targetPart;
	private ShapeNodeEditPart mySelectedElement;
	private Shell myShell;

	public TableAction() {
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
		myShell = targetPart.getSite().getShell();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

		mySelectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof graphEditor.diagram.edit.parts.FactornodeEditPart) {
				mySelectedElement = (graphEditor.diagram.edit.parts.FactornodeEditPart) structuredSelection
						.getFirstElement();
			}
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof graphEditor.diagram.edit.parts.VariablenodeEditPart) {
				mySelectedElement = (graphEditor.diagram.edit.parts.VariablenodeEditPart) structuredSelection
						.getFirstElement();
			}
		}
		action.setEnabled(isEnabled());
	}

	private boolean isEnabled() {
		return mySelectedElement != null;
	}
	private Shell getShell() {
		return targetPart.getSite().getShell();
	}

	@Override
	public void run(IAction action) {

		// ---------------------------
		Runnable getDataTable = new Runnable() {
			String file="C:\\Dokumente und Einstellungen\\alex\\Desktop\\workspace\\CSVReader\\fc.data";

			public void run() {
/*				TransactionalEditingDomain domain = null;
				Graph graph=null;
				if (mySelectedElement != null){
					domain = mySelectedElement.getEditingDomain();
					final Resource r = domain.getResourceSet().getResources().get(0);
					try {
						graph = (graphEditor.Graph) domain
						.runExclusive(new RunnableWithResult.Impl() {
							public void run() {
								setResult(((graphEditor.Graph) r.getContents().get(
										0)));

							}
						});
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
*/
				
				final KTable table = new KTable(myShell, SWT.FULL_SELECTION | SWT.MULTI
						| SWT.V_SCROLL | SWT.H_SCROLL | SWTX.FILL_WITH_LASTCOL
						| SWTX.EDIT_ON_KEY);
				DataFileReader reader;
				try {
					reader = new DataFileReader(file);
				
				TableModel model= new TableModel(reader.getHeader(),reader.getValues());

				table.setModel(model);
				table.addCellSelectionListener(new KTableCellSelectionListener() {
					public void cellSelected(int col, int row, int statemask) {
						System.out.println("Cell [" + col + ";" + row + "] selected.");
					}

					public void fixedCellSelected(int col, int row, int statemask) {
						System.out
								.println("Header [" + col + ";" + row + "] selected.");
					}
				});

				table.addCellResizeListener(new KTableCellResizeListener() {
					public void columnResized(int col, int newWidth) {
						System.out.println("Column " + col + " resized to " + newWidth);
					}

					public void rowResized(int row, int newHeight) {
						System.out.println("Row " + row + " resized to " + newHeight);
					}
				});

				//**
				 //* Set Excel-like table cursors
				//*

				if (SWT.getPlatform().equals("win32")) {

					// Cross

					Image crossCursor = SWTX.loadImageResource(table.getDisplay(),
							"/icons/cross_win32.gif");

					// Row Resize

					Image row_resizeCursor = SWTX.loadImageResource(table.getDisplay(),
							"/icons/row_resize_win32.gif");

					// Column Resize

					Image column_resizeCursor = SWTX.loadImageResource(table
							.getDisplay(), "/icons/column_resize_win32.gif");

					// we set the hotspot to the center, so calculate the number of
					// pixels from hotspot to lower border:

					Rectangle crossBound = crossCursor.getBounds();
					Rectangle rowresizeBound = row_resizeCursor.getBounds();
					Rectangle columnresizeBound = column_resizeCursor.getBounds();

					Point crossSize = new Point(crossBound.width / 2,
							crossBound.height / 2);
					Point rowresizeSize = new Point(rowresizeBound.width / 2,
							rowresizeBound.height / 2);
					Point columnresizeSize = new Point(columnresizeBound.width / 2,
							columnresizeBound.height / 2);

					table.setDefaultCursor(new Cursor(table.getDisplay(), crossCursor
							.getImageData(), crossSize.x, crossSize.y), crossSize);
					table.setDefaultRowResizeCursor(new Cursor(table.getDisplay(),
							row_resizeCursor.getImageData(), rowresizeSize.x,
							rowresizeSize.y));
					table.setDefaultColumnResizeCursor(new Cursor(table.getDisplay(),
							column_resizeCursor.getImageData(), columnresizeSize.x,
							columnresizeSize.y));

				} else {

					// Cross

					Image crossCursor = SWTX.loadImageResource(table.getDisplay(),
							"/icons/cross.gif");
					Image crossCursor_mask = SWTX.loadImageResource(table.getDisplay(),
							"/icons/cross_mask.gif");

					// Row Resize

					Image row_resizeCursor = SWTX.loadImageResource(table.getDisplay(),
							"/icons/row_resize.gif");
					Image row_resizeCursor_mask = SWTX.loadImageResource(table
							.getDisplay(), "/icons/row_resize_mask.gif");

					// Column Resize

					Image column_resizeCursor = SWTX.loadImageResource(table
							.getDisplay(), "/icons/column_resize.gif");
					Image column_resizeCursor_mask = SWTX.loadImageResource(table
							.getDisplay(), "/icons/column_resize_mask.gif");

					// we set the hotspot to the center, so calculate the number of
					// pixels from hotspot to lower border:

					Rectangle crossBound = crossCursor.getBounds();
					Rectangle rowresizeBound = row_resizeCursor.getBounds();
					Rectangle columnresizeBound = column_resizeCursor.getBounds();

					Point crossSize = new Point(crossBound.width / 2,
							crossBound.height / 2);
					Point rowresizeSize = new Point(rowresizeBound.width / 2,
							rowresizeBound.height / 2);
					Point columnresizeSize = new Point(columnresizeBound.width / 2,
							columnresizeBound.height / 2);

					table.setDefaultCursor(new Cursor(table.getDisplay(),
							crossCursor_mask.getImageData(),
							crossCursor.getImageData(), crossSize.x, crossSize.y),
							crossSize);
					table.setDefaultRowResizeCursor(new Cursor(table.getDisplay(),
							row_resizeCursor_mask.getImageData(), row_resizeCursor
									.getImageData(), rowresizeSize.x, rowresizeSize.y));
					table.setDefaultColumnResizeCursor(new Cursor(table.getDisplay(),
							column_resizeCursor_mask.getImageData(),
							column_resizeCursor.getImageData(), columnresizeSize.x,
							columnresizeSize.y));

				}

/*				shell.open();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				}
				display.dispose();
*/
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		};

		Thread t1 = new Thread(getDataTable);
		t1.start();
	}

}
