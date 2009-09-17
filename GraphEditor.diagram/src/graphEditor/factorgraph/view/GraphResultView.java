package graphEditor.factorgraph.view;

import java.util.Hashtable;
import java.util.Set;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class GraphResultView extends ViewPart {
	private static final int NUM = 3;
	TableTree tableTree;
	
	public GraphResultView() {
	}
	
	public void setData(Hashtable<String,Hashtable<Double,Double>> hash){
	    // Create the data
		tableTree.removeAll();
		Set<String> keys=hash.keySet();
		
		for(String s:keys){
			// Create a parent item and add data to the columns
			TableTreeItem p = new TableTreeItem(tableTree, SWT.NONE);
			p.setText(0, s);
			p.setText(1, "");
	        p.setText(2, "");
	        
			Hashtable<Double,Double> value=hash.get(s);
			Object[] dkey=value.keySet().toArray();
			
		    for (int i = 0; i < dkey.length; i++) {
			        // Create a child item and add data to the columns
			        TableTreeItem child = new TableTreeItem(p, SWT.NONE);
			        child.setText(0, "" );
			        child.setText(1, ((Double)dkey[i]).toString());
			        child.setText(2, ((Double)value.get(dkey[i])).toString());
		    }
		    // Expand the	 parent item
		    p.setExpanded(true);
		}
			
		
		
	}

	@Override
	public void createPartControl(Composite parent) {
		// Create the TableTree and set some attributes on the underlying table
	    tableTree = new TableTree(parent, SWT.NONE);
	    Table table = tableTree.getTable();
	    table.setHeaderVisible(true);
	    table.setLinesVisible(false);

	    new TableColumn(table, SWT.LEFT).setText("Variablenode");
	    new TableColumn(table, SWT.LEFT).setText("Def");
	    new TableColumn(table, SWT.LEFT).setText("Value");


	    // Pack the columns
	    TableColumn[] columns = table.getColumns();
	    for (int i = 0, n = columns.length; i < n; i++) {
	      columns[i].pack();
	    }
	}

	@Override
	public void setFocus() {

	}

}
