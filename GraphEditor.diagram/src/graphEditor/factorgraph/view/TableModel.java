/*
 * Copyright (C) 2004 by Friederich Kupzog Elektronik & Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 
 Author: Friederich Kupzog  
 fkmk@kupzog.de
 www.kupzog.de/fkmk
 */
package graphEditor.factorgraph.view;

import graphEditor.Factornode;
import graphEditor.Graph;
import graphEditor.Node;
import graphEditor.Variablenode;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableDefaultModel;
import de.kupzog.ktable.editors.KTableCellEditorCombo;
import de.kupzog.ktable.editors.KTableCellEditorComboText;
import de.kupzog.ktable.editors.KTableCellEditorText;
import de.kupzog.ktable.editors.KTableCellEditorText2;
import de.kupzog.ktable.editors.TableCellEditorDialog;
import de.kupzog.ktable.renderers.FixedCellRenderer;
import de.kupzog.ktable.renderers.TextCellRenderer;

/**
 * @author Friederich Kupzog
 */
public class TableModel extends KTableDefaultModel {

	private double[][] data = null;
	private String[] header = null;
	private long nodeid;
	private Node node;
	private TransactionalEditingDomain domain;

	private final FixedCellRenderer m_fixedRenderer = new FixedCellRenderer(
			FixedCellRenderer.STYLE_FLAT
					| TextCellRenderer.INDICATION_FOCUS_ROW);

	private final TextCellRenderer m_textRenderer = new TextCellRenderer(
			TextCellRenderer.INDICATION_FOCUS_ROW);

	public TableModel(String[] header, Node node,
			TransactionalEditingDomain domain) {
		initialize();
		if (node instanceof Factornode) {
			this.data = ((Factornode) node).getValues();
		}
		if (node instanceof Variablenode) {
			this.data = new double[((Variablenode) node).getValues().size()][1];
			for (int i = 0; i < ((Variablenode) node).getValues().size(); i++)
				this.data[i][0] = ((Variablenode) node).getValues().get(i)
						.doubleValue();
		}
		this.header = header;
		this.nodeid = node.getId();
		this.domain = domain;
		this.node = node;
	}
	public TableModel(String[] header, double[][] data, long nodeid,
			TransactionalEditingDomain domain) {
		initialize();
		this.data = data;
		this.header = header;
		this.nodeid = nodeid;
		this.domain = domain;
		if (nodeid != -1L) {
			try {
				final Resource r = domain.getResourceSet().getResources()
						.get(0);
				Graph graph = (graphEditor.Graph) domain
						.runExclusive(new RunnableWithResult.Impl() {
							public void run() {
								setResult(((graphEditor.Graph) r.getContents()
										.get(0)));

							}
						});
				this.node = (Node) graph.getGraphElement(nodeid);
			} catch (InterruptedException e) {
			}
		}
	}

	public long getNodeId() {
		return nodeid;
	}

	public void setData(double[][] data) {
		this.data = data;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public Object doGetContentAt(int col, int row) {
		if (row == 0) {
			return header[col];
		} else {
			return Double.toString(data[row - 1][col]);
		}
	}

	public KTableCellEditor doGetCellEditor(int col, int row) {
		if (col < getFixedColumnCount() || row < getFixedRowCount())
			return null;
		return new KTableCellEditorText2();
	}

	public void doSetContentAt(int col, int row, Object value) {

		if (nodeid != -1L) {
			if (row == 0) {
				header[col] = (String) value;
			} else {
				try {
					data[row - 1][col] = Double.valueOf((String) value)
							.doubleValue();
				} catch (NumberFormatException e) {
				}
				Command cmd = null;
				if (node instanceof Variablenode) {
					ArrayList<Double> values=new ArrayList<Double>();
					for(int i=0;i<data.length;i++){
						values.add(Double.valueOf(data[i][0]));
					}
					cmd = SetCommand.create(domain, node,
							graphEditor.GraphEditorPackage.eINSTANCE
									.getVariablenode_Values(), values);

				}
				if (node instanceof Factornode) {
					cmd = SetCommand.create(domain, node,
							graphEditor.GraphEditorPackage.eINSTANCE
									.getFactornode_Values(), data);

				}
				try {
					((TransactionalCommandStack) domain.getCommandStack())
							.execute(cmd, null);
				} catch (RollbackException rbe) {
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public int doGetRowCount() {
		return data.length + 1;
	}

	public int getFixedHeaderRowCount() {
		return 1;
	}

	public int doGetColumnCount() {
		return header.length;
	}

	public int getFixedHeaderColumnCount() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kupzog.ktable.KTableModel#getFixedSelectableRowCount()
	 */
	public int getFixedSelectableRowCount() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kupzog.ktable.KTableModel#getFixedSelectableColumnCount()
	 */
	public int getFixedSelectableColumnCount() {
		return 0;
	}

	public boolean isColumnResizable(int col) {
		return true;
	}

	public boolean isRowResizable(int row) {
		return true;
	}

	public int getRowHeightMinimum() {
		return 18;
	}

	// Rendering
	public KTableCellRenderer doGetCellRenderer(int col, int row) {
		if (isFixedCell(col, row))
			return m_fixedRenderer;

		return m_textRenderer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kupzog.ktable.KTableModel#belongsToCell(int, int)
	 */
	public Point doBelongsToCell(int col, int row) {
		// no cell spanning:
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kupzog.ktable.KTableDefaultModel#getInitialColumnWidth(int)
	 */
	public int getInitialColumnWidth(int column) {
		return 90;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kupzog.ktable.KTableDefaultModel#getInitialRowHeight(int)
	 */
	public int getInitialRowHeight(int row) {
		if (row == 0)
			return 22;
		return 18;
	}

	public double[][] getData() {
		return data;
	}
}
