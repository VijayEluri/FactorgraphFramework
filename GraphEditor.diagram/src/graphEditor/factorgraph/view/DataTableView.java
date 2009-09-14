package graphEditor.factorgraph.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graphEditor.Factornode;
import graphEditor.FunctionType;
import graphEditor.GraphElement;
import graphEditor.Node;
import graphEditor.Graph;
import graphEditor.VariableType;
import graphEditor.Variablenode;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.*;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellResizeListener;
import de.kupzog.ktable.KTableCellSelectionListener;
import de.kupzog.ktable.SWTX;

public class DataTableView extends ViewPart implements ISelectionListener {

	private String[] header;
	private double[][] data;
	private long nodeid;
	private TransactionalEditingDomain domain;
	private KTable table = null;
	private TableModel model;
	private ShapeNodeEditPart mySelectedElement;
	private Action loadFileAction;
	private Action initTableAction;
	private Composite parent;
	private Node node;

	public DataTableView() {
	}

	public void setFocus() {
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(parent.getShell(), "Data Table View",
				message);
	}

	private String selectFile() {
		FileDialog dialog = new FileDialog(parent.getShell());
		return dialog.open();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		mySelectedElement = null;
		nodeid = -1L;
		// table.setEnabled(false);
		loadFileAction.setEnabled(false);
		initTableAction.setEnabled(false);

		//TODO hier muﬂ noch der graph mit seinen resultsets rein!!!!
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof graphEditor.diagram.edit.parts.VariablenodeEditPart) {
				mySelectedElement = (graphEditor.diagram.edit.parts.VariablenodeEditPart) structuredSelection
						.getFirstElement();
				loadFileAction.setEnabled(true);
				initTableAction.setEnabled(true);
				domain = mySelectedElement.getEditingDomain();
				node = (Variablenode) mySelectedElement
						.resolveSemanticElement();
				if (((Variablenode) node).getType() == VariableType.BOOLEAN) {
					header = new String[] { node.getName() };
					data = new double[((Variablenode) node).getValues().size()][1];
					for (int i = 0; i < ((Variablenode) node).getValues()
							.size(); i++)
						data[i][0] = ((Variablenode) node).getValues().get(i)
								.doubleValue();
					nodeid = node.getId();
					model = new TableModel(header, node, domain);
				}
				table.setModel(model);
				// table.pack(true);
				// table.setEnabled(true);
				loadFileAction.setEnabled(false);
				initTableAction.setEnabled(true);
				table.update();
			} else if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof graphEditor.diagram.edit.parts.FactornodeEditPart) {
				mySelectedElement = (graphEditor.diagram.edit.parts.FactornodeEditPart) structuredSelection
				.getFirstElement();
		domain = mySelectedElement.getEditingDomain();
				node = (Factornode) mySelectedElement.resolveSemanticElement();
				if (((Factornode) node).getType() == FunctionType.BOOLEAN) {
					List<Variablenode> nodelist = null;
					try {
						final Resource r = domain.getResourceSet()
								.getResources().get(0);
						nodelist = (EList<Variablenode>) domain
								.runExclusive(new RunnableWithResult.Impl() {
									public void run() {
										Graph graph = (Graph) r.getContents()
												.get(0);
										setResult(graph
												.getConnectingVariablenodes((Factornode) node));
									}
								});
						if (nodelist.size() != 0) {
							header = new String[nodelist.size() + 1];
							for (int i = 0; i < nodelist.size(); i++)
								header[i] = nodelist.get(i).getName();
							header[nodelist.size()] = "P";
							initTableAction.setEnabled(true);
						} else {
							header = new String[] { "Factornode not connected" };
							initTableAction.setEnabled(false);
						}
					} catch (InterruptedException e) {
					}
					nodeid = node.getId();

					if (((Factornode) node).getValues() != null) {
						data = ((Factornode) node).getValues();
						if (data[0].length != header.length) {
							data = new double[][] {};
						}
					} else {
						data = new double[][] {};
					}
					model = new TableModel(header, data, nodeid, domain);
					table.setModel(model);
					// table.setEnabled(true);
					// table.pack(true);
					loadFileAction.setEnabled(true);
					table.update();

				}
			}else if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof graphEditor.diagram.edit.parts.GraphEditPart) {
				DiagramEditPart mySelectedElement = (graphEditor.diagram.edit.parts.GraphEditPart) structuredSelection.getFirstElement();
				loadFileAction.setEnabled(false);
				initTableAction.setEnabled(false);
				domain = mySelectedElement.getEditingDomain();
				try {
					
				
					final Resource r = domain.getResourceSet()
							.getResources().get(0);
					Graph graph = (Graph) domain
							.runExclusive(new RunnableWithResult.Impl() {
								public void run() {
									Graph graph = (Graph) r.getContents()
											.get(0);
									setResult(graph);
								}
							});
					data=graph.getResult();
					
					if( data != null){
						List<Node> nodes=graph.getNodes();
						List<Variablenode> vnodes=new ArrayList<Variablenode>();

						for(int i=0;i<nodes.size();i++){
							if(nodes.get(i) instanceof Variablenode){
								vnodes.add((Variablenode)(nodes.get(i)));
							}
						}	
						header=new String[vnodes.size()];
						for(int i=0;i<vnodes.size();i++){
							header[i]=vnodes.get(i).getName();
						}
						data=new double[][] {};
						nodeid=-1L;
						table.setModel(new TableModel(header, data, nodeid, null));
					} else{
						header=new String[]{"Graph has no calculated results"};
						data=new double[][] {};
						nodeid=-1L;
						table.setModel(new TableModel(header, data, nodeid, null));
					}
				}
				catch (InterruptedException e) {
				}
			}
			else{
				header=new String[]{"selection not supported"};
				data=new double[][] {};
				nodeid=-1L;
				table.setModel(new TableModel(header, data, nodeid, null));
			}
		}
		getViewSite().getActionBars().updateActionBars();
	}

	private double[] convertDual2Array(String dual, int spalten) {
		double[] values = null;
		if (dual.length() > spalten) {
			return values;
		} else {
			StringBuffer sb = new StringBuffer();
			if (dual.length() == spalten) {
				sb.append(dual);
			} else {
				int r = spalten - dual.length();
				for (int i = 0; i < r; i++)
					sb.append("0");
				sb.append(dual);
			}
			String newdual = sb.toString();
			values = new double[spalten];
			for (int i = 0; i < newdual.length(); i++) {
				values[i] = Double.valueOf(String.valueOf(newdual.charAt(i)))
						.doubleValue();
			}
			return values;
		}
	}

	private void makeActions() {
		
		//TODO hier muﬂ eine undo/redo command erzeugt werden!!!
		loadFileAction = new Action() {
			public void run() {
				if (nodeid != -1L) {
					String file = selectFile();
					if (file != null) {
						try {
							DataFileReader reader = new DataFileReader(file);
							data = reader.getValues();
							header = reader.getHeader();

							Command cmd = SetCommand.create(domain, node,
									graphEditor.GraphEditorPackage.eINSTANCE
											.getFactornode_Values(), data);
							model = new TableModel(header, data, nodeid, domain);
							table.setModel(model);
							table.update();
							try {
								((TransactionalCommandStack) domain
										.getCommandStack()).execute(cmd, null);
							} catch (RollbackException rbe) {
								showMessage("Transaction rolled back due to validation errors.\n"
										+ rbe.getStatus());
							} catch (InterruptedException e) {
							}
						} catch (Exception e) {
							showMessage(e.getLocalizedMessage());
						}
					}
				}
			}
		};
		loadFileAction.setText("Load File");
		loadFileAction.setToolTipText("Load File");
		loadFileAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_ELCL_SYNCED));
		// getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		loadFileAction.setEnabled(false);

		initTableAction = new Action() {
			public void run() {
				if (nodeid != -1L) {
					if (node instanceof Factornode) {
						if (((Factornode) node).getType() == FunctionType.BOOLEAN) {
							try {
								final Resource r = domain.getResourceSet()
										.getResources().get(0);
								Graph graph = (Graph) domain
										.runExclusive(new RunnableWithResult.Impl() {
											public void run() {
												setResult(((graphEditor.Graph) r
														.getContents().get(0)));
											}
										});
								List<Variablenode> list = graph
										.getConnectingVariablenodes((Factornode) node);
								if (list.size() != 0) {
									header = new String[list.size() + 1];
									for (int i = 0; i < list.size(); i++)
										header[i] = list.get(i).getName();
									header[list.size()] = "P";
									int spalten = list.size();
									int zeilen = (int) Math.pow(2, spalten);
									double p = 1.0 / zeilen;
									data = new double[zeilen][header.length];

									for (int i = 0; i < zeilen; i++) {
										String dual = Integer.toBinaryString(i);
										double[] values = convertDual2Array(
												dual, spalten);
										double[] newvalues = new double[spalten + 1];
										for (int j = 0; j < spalten; j++)
											newvalues[j] = values[j];
										newvalues[spalten] = p;
										data[i] = newvalues;
									}
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							Command cmd = SetCommand.create(domain, node,
									graphEditor.GraphEditorPackage.eINSTANCE
											.getFactornode_Values(), data);
							try {
								((TransactionalCommandStack) domain
										.getCommandStack()).execute(cmd, null);
							} catch (RollbackException rbe) {
								showMessage("Transaction rolled back due to validation errors.\n"
										+ rbe.getStatus());
							} catch (InterruptedException e) {
							}
							model = new TableModel(header, data, nodeid, domain);
							table.setModel(model);
							table.update();
						}
					}
				}
				if (node instanceof Variablenode) {
					if (((Variablenode) node).getType() == VariableType.BOOLEAN) {
						header = new String[] { node.getName() };
						if (((Variablenode) node).getValues().size() == 0) {
							data = new double[2][1];
							data[0][0] = 0;
							data[1][0] = 1;

							List<Double> values = new ArrayList<Double>();
							values.add(new Double(0));
							values.add(new Double(1));

							Command cmd = SetCommand.create(domain, node,
									graphEditor.GraphEditorPackage.eINSTANCE
											.getVariablenode_Values(), values);
							try {
								((TransactionalCommandStack) domain
										.getCommandStack()).execute(cmd, null);
							} catch (RollbackException rbe) {
								showMessage("Transaction rolled back due to validation errors.\n"
										+ rbe.getStatus());
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							data = new double[((Variablenode) node).getValues()
									.size()][1];
						}
						nodeid = node.getId();
						model = new TableModel(header, data, nodeid, domain);
						table.setModel(model);
						table.update();
					}
				}
			}

		};
		initTableAction.setText("Init Table");
		initTableAction.setToolTipText("Init Table");
		initTableAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_ELCL_SYNCED));
		// getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		initTableAction.setEnabled(false);

	}

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		// GridData gridData = new GridData();
		// gridData.horizontalAlignment = GridData.FILL;
		// gridData.grabExcessHorizontalSpace = true;
		//        
		// GridLayout gridLayout = new GridLayout();
		// gridLayout.numColumns = 1;
		//
		// parent.setLayout (gridLayout);
		//		
		//		
		// Label label = new Label(parent, SWT.LEFT);
		// label.setText("Hello World");
		// label.setLayoutData(gridData);
		//

		table = new KTable(parent, SWT.FULL_SELECTION | SWT.MULTI
				| SWT.V_SCROLL | SWT.H_SCROLL | SWTX.FILL_WITH_LASTCOL
				| SWTX.EDIT_ON_KEY);
		// table.setModel(model);
		// table.setLayoutData(gridData);

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
		getSite().getPage().addSelectionListener(this);
		makeActions();
		contributeToActionBars();

	}

	// private void hookContextMenu() {
	// MenuManager menuMgr = new MenuManager("#PopupMenu");
	// menuMgr.setRemoveAllWhenShown(true);
	// menuMgr.addMenuListener(new IMenuListener() {
	// public void menuAboutToShow(IMenuManager manager) {
	// DataTableView.this.fillContextMenu(manager);
	// }
	// });
	// table.getm
	// Menu menu = menuMgr.createContextMenu(table.getMenu().);
	// table.getMenu().setMenu(menu);
	// getSite().registerContextMenu(menuMgr, this);
	// }

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(initTableAction);
		manager.add(new Separator());
		manager.add(loadFileAction);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(initTableAction);
		manager.add(loadFileAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(initTableAction);
		manager.add(loadFileAction);
	}

	public void dispose() {
		getViewSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(this);
		super.dispose();
	}

}