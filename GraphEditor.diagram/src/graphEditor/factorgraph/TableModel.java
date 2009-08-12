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
package graphEditor.factorgraph;

import java.util.HashMap;

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

    private double[][] data = null ;
    private String[] header=null;
   
    private final FixedCellRenderer m_fixedRenderer =
        new FixedCellRenderer(FixedCellRenderer.STYLE_FLAT  |
            TextCellRenderer.INDICATION_FOCUS_ROW);
    
    private final TextCellRenderer m_textRenderer = 
        new TextCellRenderer(TextCellRenderer.INDICATION_FOCUS_ROW);

    public TableModel(String[] header,double[][] data) {
        initialize();
        this.data=data;
        this.header=header;
    }
    public Object doGetContentAt(int col, int row) {
    	String erg;
    	if(row==0){
    		return header[col];
    	}else{
    		return Double.toString(data[row-1][col]) ;	
    	}
        }

   public KTableCellEditor doGetCellEditor(int col, int row) {
    	if (col<getFixedColumnCount() || row<getFixedRowCount())
    		return null;
         return new KTableCellEditorText2();
     }

     public void doSetContentAt(int col, int row, Object value) {
    	if(row==0){
    		header[col]=(String)value;
    	}else{
    		data[row-1][col] =Double.valueOf(data[row-1][col]).doubleValue() ;	
    	}
    }

   public int doGetRowCount() {
        return data.length+1;
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
    
    /* (non-Javadoc)
     * @see de.kupzog.ktable.KTableModel#getFixedSelectableRowCount()
     */
    public int getFixedSelectableRowCount() {
        return 0;
    }

    /* (non-Javadoc)
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

    /* (non-Javadoc)
     * @see de.kupzog.ktable.KTableModel#belongsToCell(int, int)
     */
    public Point doBelongsToCell(int col, int row) {
        // no cell spanning:
        return null;
    }

    /* (non-Javadoc)
     * @see de.kupzog.ktable.KTableDefaultModel#getInitialColumnWidth(int)
     */
    public int getInitialColumnWidth(int column) {
        return 90;
    }

    /* (non-Javadoc)
     * @see de.kupzog.ktable.KTableDefaultModel#getInitialRowHeight(int)
     */
    public int getInitialRowHeight(int row) {
    	if (row==0) return 22;
    	return 18;
    }
}
